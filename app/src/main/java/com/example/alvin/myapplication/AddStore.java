package com.example.alvin.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddStore extends AppCompatActivity {

    private EditText Bsname;
    private EditText email;
    private EditText location;
    private Spinner spinner1, spinner2;
    private Button button;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_store);

        addItemsOnSpinner2();
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();

        Bsname=(EditText)findViewById(R.id.Bsname);
        email=(EditText)findViewById(R.id.email);
        location=(EditText)findViewById(R.id.location);
    }

    public void registerStore(){
        FirebaseDatabase database =  FirebaseDatabase.getInstance();
        FirebaseUser user =  firebaseAuth.getCurrentUser();
        String userId = user.getUid();
        DatabaseReference mRef =  database.getReference().child("Stores").child(userId);
        mRef.child("Bsname").setValue(Bsname);
        mRef.child("Email").setValue(email);
        mRef.child("location").setValue(location);
        mRef.child("Category").setValue(spinner1);
    }

    public void addItemsOnSpinner2() {

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        List<String> list = new ArrayList<String>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        button = (Button) findViewById(R.id.button);
        Bsname=(EditText)findViewById(R.id.Bsname);
        email=(EditText)findViewById(R.id.email);
        location=(EditText)findViewById(R.id.location);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(AddStore.this,
                        "OnClickListener : " +
                                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) +
                                "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
                registerStore();
            }

        });
    }
}

