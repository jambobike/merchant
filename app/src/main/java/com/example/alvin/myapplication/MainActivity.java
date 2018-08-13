package com.example.alvin.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText txt_email;
    private EditText txt_pass;
    private EditText txt_phone;
    private EditText txt_fnames;
    private  Button reg_btn;
    //private  EditText txt_confirm_pass;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    DatabaseReference rootRef, childRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
        reg_btn=(Button)findViewById(R.id.signup_activity);
        txt_email=(EditText)findViewById(R.id.email);
        txt_pass=(EditText)findViewById(R.id.password);
        txt_phone=findViewById(R.id.phone);
        txt_fnames=findViewById(R.id.fnames);

        rootRef = FirebaseDatabase.getInstance().getReference();
        childRef =rootRef.child("Users");

        //txt_confirm_pass=(EditText)findViewById(R.id.confirm_pass);
        reg_btn.setOnClickListener(this);

    }
    public void registerUser(){
        String email=txt_email.getText().toString().trim();
        String password=txt_pass.getText().toString().trim();
        String phone=txt_phone.getText().toString().trim();
        String fnames=txt_fnames.getText().toString().trim();
        if (TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this,"Enter your Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this,"Enter A Valid Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(phone)){
            //email is empty
            Toast.makeText(this,"Enter your phone number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (phone.length() !=10){
            Toast.makeText(this,"Enter A Valid Phone Number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this,"Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() !=10) {
            Toast.makeText(this,"Password should be at least 6 characters long", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(fnames)){
            //password is empty
            Toast.makeText(this,"Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering User...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            FirebaseUser user=firebaseAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent mainActivity=new Intent(MainActivity.this, Home.class);
                            startActivity(mainActivity);
                        }else {
                            Toast.makeText(MainActivity.this, "Could not register... please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        Users users = new Users(email, fnames, phone);
        String key = childRef.push().getKey();
        childRef.push().setValue(users);
    }

    @Override
    public void onClick(View v) {

        registerUser();

    }
}