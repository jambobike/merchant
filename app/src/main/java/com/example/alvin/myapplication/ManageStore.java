package com.example.alvin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ManageStore extends AppCompatActivity implements View.OnClickListener{

    public Button mBtAddItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_store);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        mBtAddItems = (Button) findViewById(R.id.addItems);
        mBtAddItems.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View view) {
        if (view == mBtAddItems) {
            Intent additems = new Intent(ManageStore.this, AddItems.class);
            startActivity(additems);
        }
    }
}
