package com.example.android.vitb;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


@SuppressLint("ValidFragment")
public class mentormessage extends android.support.v4.app.Fragment {

    String mentorid="";
    String regno="";
    String message="";
     //@SuppressLint("ValidFragment")
     @SuppressLint("ValidFragment")
     mentormessage(String ment, String reg,String messa){
       mentorid = ""+ment;
       regno = ""+reg;
       message = ""+messa;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mentormessage, container, false);
        //Toast.makeText(getContext(),""+mentorid+regno,Toast.LENGTH_SHORT).show();
        Button b1 = (Button) view.findViewById(R.id.yesbutton);
        Button b2 = (Button) view.findViewById(R.id.nobutton);
        Button b3 = (Button) view.findViewById(R.id.MentorLogoutButton);
        TextView t = (TextView) view.findViewById(R.id.studentmessage);
        String mess = t.getText().toString();
        mess =""+ mess + message;
        t.setText(mess);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myref = database.getReference("mentor_request");
                DatabaseReference newRef = myref.child(mentorid);
                newRef.child(regno).removeValue();
                Intent myIntent = new Intent(getActivity(), MentorActivity.class);
                getActivity().startActivity(myIntent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myref = database.getReference("mentor_request");
                DatabaseReference newRef = myref.child(mentorid);
                newRef.child(regno).removeValue();
                //DatabaseReference newRef1 = myref.child("mentor_status");
                //newRef1.child(regno).setValue(1);
                Intent myIntent = new Intent(getActivity(), MentorActivity.class);
                getActivity().startActivity(myIntent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Authentication", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent i = new Intent(getContext(),MainActivity.class);
                startActivity(i);
            }
        });

        return view;
    }
}
