package com.example.alvin.myapplication;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.IOException;
import java.util.UUID;


public class AddItems extends AppCompatActivity {
    //Declaring views
    private Button buttonChoose;
    private Button buttonUpload;
    private Button buttonsubmit;
    private ImageView imageView;
    private EditText editText;
    private EditText editText2;
    private EditText editText3;
    private FirebaseAuth firebaseAuth;
    private Uri filePath;

    DatabaseReference rootRef, childRef;

    //Image request code
    private int PICK_IMAGE_REQUEST = 1;

    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;

    //Bitmap to get image from gallery
    private Bitmap bitmap;

    //firebase variables
    FirebaseStorage storage;
    StorageReference storageReference;

    //Uri to store the image uri
    //private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);

        //Firebase
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference().child(uid).child("Items");

        //Initializing views
        buttonChoose = (Button) findViewById(R.id.buttonChoose);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        imageView = (ImageView) findViewById(R.id.imageView);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);

        buttonChoose = (Button)findViewById(R.id.buttonChoose);
        buttonUpload = (Button)findViewById(R.id.buttonUpload);

        //Setting clicklistener
        buttonChoose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                chooseImage();
            }
        });

        buttonUpload.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                upload();
            }
        });

    }

    private void upload() {
        if (filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+UUID.randomUUID().toString());
            ref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Toast.makeText(AddItems.this,"uploaded", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(AddItems.this,"failed"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getBytesTransferred());
                    progressDialog.setMessage("uploaded"+(int)progress+"%");
                }
            });
        }
        String name = editText.getText().toString().trim();
        String item_name = editText2.getText().toString().trim();
        String item_desc = editText3.getText().toString().trim();

        //Uploading code

        String key = rootRef.push().getKey();
        Items items = new Items(key, name, item_name, item_desc);
//            ClipData.Item item = new ClipData.Item(key, editText, editText2, editText3);
        // Database
        // Stores
        // ID -> Item
        //
        childRef.push().setValue(items);
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select picture"),PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                imageView.setImageBitmap(bitmap);
            }

            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


}
