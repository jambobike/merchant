package com.example.alvin.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStore extends AppCompatActivity {

    private EditText bsname, email, location;
    private Spinner spinner1;
    public Button submitstore;
    private FirebaseAuth firebaseAuth;

    DatabaseReference rootRef, childRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_store);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        bsname = (EditText) findViewById(R.id.bsname);
        email = (EditText) findViewById(R.id.email);
        location = (EditText) findViewById(R.id.location);
        submitstore = (Button) findViewById(R.id.submitstore);
        spinner1 = (Spinner) findViewById(R.id.spinner1);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        rootRef = FirebaseDatabase.getInstance().getReference("Stores");
        childRef =rootRef.child(uid);

        submitstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStore();
            }
        });
    }

    public void addStore(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();


        String Bsname = bsname.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String Location = location.getText().toString().trim();
        String Spinner1 = spinner1.getSelectedItem().toString();
        String key = childRef.push().getKey();

        Stores stores = new Stores(key, Bsname, Email, Location,Spinner1);
        // Database
        // Stores
        // ID -> Item
        //

        childRef.push().setValue(stores);
        Toast.makeText(this,"Saved Succesfuly",Toast.LENGTH_SHORT).show();

    }

}
