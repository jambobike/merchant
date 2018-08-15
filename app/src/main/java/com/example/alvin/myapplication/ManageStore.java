package com.example.alvin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ManageStore extends AppCompatActivity{
    private RecyclerView myRecyclerView;
    MyAdapter adapter;
    List<MyDataSetGet> listData;
    FirebaseDatabase FDB;
    DatabaseReference DBR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_store);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent additem = new Intent(ManageStore.this, AddItems.class);
                startActivity(additem);
            }
        });

        myRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);

        myRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager LM = new LinearLayoutManager(getApplicationContext());
        myRecyclerView.setLayoutManager(LM);
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));

        listData = new ArrayList<>();
        adapter = new MyAdapter(listData);

        FDB = FirebaseDatabase.getInstance();
        GetDataFirebase();

    }

    void GetDataFirebase() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DBR = FDB.getReference("Stores").child(uid).child("Items");
        DBR.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                MyDataSetGet data = dataSnapshot.getValue(MyDataSetGet.class);
                listData.add(data);
                myRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
        List<MyDataSetGet> listArray;

        public MyAdapter(List<MyDataSetGet> List){
            this.listArray = List;
        }

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            return null;
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            MyDataSetGet data = listArray.get(position);
            holder.iname.setText(data.getIname());

        }

        public class MyViewHolder extends RecyclerView.ViewHolder{
            TextView iname;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                iname = (TextView) itemView.findViewById(R.id.bname);
            }
        }

        @Override
        public int getItemCount() {
            return listArray.size();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}