package com.example.android.vitb;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


@SuppressLint("ValidFragment")
public class ExampleItem3 extends Fragment {

    String i;
    String id;
    String o;
    String od;
    @SuppressLint("ValidFragment")
    ExampleItem3(String in,String ou,String ind,String oud){
        i = in;
        id = ind;
        o = ou;
        od = oud;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_example_item3, container, false);
        final RelativeLayout r = (RelativeLayout) view.findViewById(R.id.Relative);
        TextView v1 =(TextView) view.findViewById(R.id.textIn);
        TextView v2 = (TextView)view.findViewById(R.id.textOut);
        TextView v3 = (TextView)view.findViewById(R.id.textOutDate);
        TextView v4 = (TextView)view.findViewById(R.id.textInDate);
        Button b  = (Button) view.findViewById(R.id.okButton1);
        String intime = v1.getText().toString();
        String outTime = v2.getText().toString();
        String outTimeDate = v4.getText().toString();
        String InTimeDate = v3.getText().toString();
        intime = ""+intime+i;
        outTime = ""+outTime+o;
        InTimeDate = ""+InTimeDate+id;
        outTimeDate = ""+outTimeDate+od;

        v1.setText(intime);
        v2.setText(outTime);
        v3.setText(outTimeDate);
        v4.setText(InTimeDate);
        //Button b = (Button) view.findViewById(R.id.okButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new Fragg2(null);
                r.setVisibility(View.GONE);
                FrameLayout f = (FrameLayout) view.findViewById(R.id.frameWard1);
                f.setVisibility(View.VISIBLE);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frameWard1, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
}
