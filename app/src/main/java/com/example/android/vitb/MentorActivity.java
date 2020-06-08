package com.example.android.vitb;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MentorActivity extends AppCompatActivity implements ExampleAdapter.OnItemClickListener {

    String u;
    public SharedPreferences.Editor det_editor;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    public static final String number = "17bce7128";
    public static final String message = "hii";
    ArrayList<ExampleItem> exampleList = new ArrayList<>();
    private RecyclerView.Adapter mExampleAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public ArrayList<String> detailsMes = new ArrayList<String>();
    public ArrayList<String> detailsReg = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor);

        SharedPreferences pre_details = getSharedPreferences("Details", 0);
        det_editor = pre_details.edit();
        u = pre_details.getString("user", "None");
//        Button b = (Button) findViewById(R.id.MentorLogoutButton);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("mentor_request");
        DatabaseReference newRef = myRef.child(u);
        newRef.addValueEventListener(new ValueEventListener() {

                                         @Override
                                         public void onDataChange(DataSnapshot dataSnapshot) {
                                             //DataSnapshot snapshot;
                                             for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                                 //String post = postSnapshot.getValue(String.class);
                                                 String mass = postSnapshot.child("message").getValue(String.class);
                                                 //String massa = mass.child("message").getV
                                                 //Toast.makeText(getApplicationContext(), "" + mass, Toast.LENGTH_LONG).show();
                                                 String reg = postSnapshot.child("regno").getValue(String.class);
                                                 detailsMes.add(mass);
                                                 detailsReg.add(reg);
                                                 exampleList.add(new ExampleItem(R.drawable.ic_assignment, reg, mass));
                                                 //Log.e("Get Data", post.<YourMethod>());
                                                 mRecyclerView = findViewById(R.id.recyclerView);
                                                 mRecyclerView.setHasFixedSize(true);//optional

                                                 mAdapter = new ExampleAdapter(exampleList);
                                                 mRecyclerView.setAdapter(mAdapter);
                                                 mRecyclerView.setLayoutManager(mLayoutManager);


                                                 mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
                                                     @Override
                                                     public void onItemClick(int position) {
                                                         mentormessage fragment = null;
                                                         String mentor = u;
                                                         String regno = exampleList.get(position).getText1();
                                                         String mentmess = exampleList.get(position).getText2();
                                                         fragment = new mentormessage(mentor,regno,mentmess);
                                                         mRecyclerView.setVisibility(View.GONE);
                                                         FrameLayout f = (FrameLayout) findViewById(R.id.frameMentor);
                                                         f.setVisibility(View.VISIBLE);
                                                         FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                                                         transaction.replace(R.id.frameMentor, fragment);
                                                         transaction.commit();
                                                         //Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                                                     }
                                                 });


                                             }

                                         }


                                         @Override
                                         public void onCancelled(DatabaseError error) {
                                             // Failed to read value
                                             //Log.w(TAG, "Failed to read value.", error.toException());


                                         }
                                     });


        //Toast.makeText(getApplicationContext(), "I am here", Toast.LENGTH_SHORT).show();
        mLayoutManager = new LinearLayoutManager(this);

//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences sharedPreferences = getSharedPreferences("Authentication", Context.MODE_PRIVATE);
//
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.clear();
//                editor.apply();
//                Intent i = new Intent(MentorActivity.this,MainActivity.class);
//                startActivity(i);
//            }
//        });




}

    @Override
    public void onItemClick(int position) {

    }
}