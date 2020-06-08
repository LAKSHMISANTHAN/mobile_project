package com.example.android.vitb;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

@SuppressLint("ValidFragment")
public class Frag2 extends android.support.v4.app.Fragment {
    public EditText e;
    public Button b;
    String user;
    String name;
    Long mentorid;
    Button send;
    public String message;
    Frag2(String user1){
        user = user1;

    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag2, container, false);
        e = (EditText) view.findViewById(R.id.messageArea);
        b = (Button) view.findViewById(R.id.messageButton);


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Student");
        final DatabaseReference newRef = myRef.child(user);
        newRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mentorid=dataSnapshot.child("Mentor_id").getValue(Long.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = e.getText().toString();
                e.setText("");
                DatabaseReference ref2 = database.getReference("mentor_request");
                final DatabaseReference ref3 = ref2.child(mentorid.toString());
                ref3.child(user).child("message").setValue(message);
                ref3.child(user).child("regno").setValue(user);
            }
        });






//        newRef.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                //Log.w(TAG, "Failed to read value.", error.toException());
//
//
//            }
//        });

        return view;
    }
}
