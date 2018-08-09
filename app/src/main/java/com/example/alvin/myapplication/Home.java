package com.example.alvin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "content_login";
    public Button mBtaddStore;
    public Button mBtmanageStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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

        mBtaddStore = (Button) findViewById(R.id.addStore);
        mBtmanageStore = (Button) findViewById(R.id.manageStore);
        mBtaddStore.setOnClickListener((View.OnClickListener) this);
        mBtmanageStore.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {
        if (v == mBtaddStore) {
            Intent addStore = new Intent(Home.this, AddStore.class);
            startActivity(addStore);
        }
        /*if (v == mBtmanageStore) {
            Intent manageStore = new Intent(Home.this, ManageStore.class);
            startActivity(manageStore);
        }*/
    }
}
