package com.example.android.vitb;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


@SuppressLint("ValidFragment")
public class Fragg1 extends Fragment {
    String user;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    ArrayList<ExampleItem> exampleList = new ArrayList<>();
    private RecyclerView.Adapter mExampleAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public Fragg1(String u) {
          user = u;
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_fragg1, container, false);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("present_list");

        //DatabaseReference newRef = myRef.child(user);
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //DataSnapshot snapshot;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                     //String in = postSnapshot.child("in_time").getValue(String.class);
                    final String out = postSnapshot.child("out_time").getValue(String.class);
                    String reg = postSnapshot.child("regno").getValue(String.class);
                   // Toast.makeText(getContext(), "" + reg, Toast.LENGTH_LONG).show();


                    exampleList.add(new ExampleItem(R.drawable.ic_assignment, reg,null));
                    //Log.e("Get Data", post.<YourMethod>());
                    mRecyclerView = view.findViewById(R.id.recyclerView);
                    mRecyclerView.setHasFixedSize(true);//optional

                    mAdapter = new ExampleAdapter(exampleList);
                    mRecyclerView.setAdapter(mAdapter);
                    mRecyclerView.setLayoutManager(mLayoutManager);


                    mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {

                            String regno = exampleList.get(position).getText1();

                            Fragment newFragment = new ExampleItem2(regno,out);
                            mRecyclerView.setVisibility(View.GONE);
                            FrameLayout f = (FrameLayout) view.findViewById(R.id.frameWarden);
                            f.setVisibility(View.VISIBLE);
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.frameWarden, newFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();

                            //Toast.makeText(getContext(),"Clicked",Toast.LENGTH_SHORT).show();
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
        mLayoutManager = new LinearLayoutManager(getContext());
        return view;
    }


}
