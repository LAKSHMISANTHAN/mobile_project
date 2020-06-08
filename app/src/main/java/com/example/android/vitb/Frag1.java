package com.example.android.vitb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.Context.MODE_PRIVATE;

@SuppressLint("ValidFragment")
public class Frag1 extends android.support.v4.app.Fragment {
    String user;
    String name;
    Long mentorid;

    public SharedPreferences sp;
    public SharedPreferences.Editor speditor;
    public SharedPreferences sp_details;
    public SharedPreferences.Editor speditor_details;


    Frag1(String user1){
        user = user1;

    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag1, container, false);

        //sp = getSharedPreferences("Authentication",MODE_PRIVATE);
        //sp_details = getSharedPreferences("Details",0);
        //speditor =sp.edit();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference("Student");
        DatabaseReference newRef = myref.child(user);
        newRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                name = dataSnapshot.child("name").getValue(String.class);
                mentorid = dataSnapshot.child("Mentor_id").getValue(Long.class);
                //sp_details.edit().putLong("Mentor",mentorid).apply();
                //Log.d(TAG, "Value is: " + value);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());


            }
        });

        TextView e1 =  view.findViewById(R.id.regno);
        TextView e2 =  view.findViewById(R.id.name);
        TextView e3 =  view.findViewById(R.id.mentor);

        String s1 = e1.getText().toString();
        String s2 = e2.getText().toString();
        String s3 = e3.getText().toString();

        String s4 = s1+user;
        String s5 = s2 + name;
        String s6 = s3+mentorid;


        e1.setText(s4);
        e2.setText(s5);
        e3.setText(s6);

        return view;
    }


}
