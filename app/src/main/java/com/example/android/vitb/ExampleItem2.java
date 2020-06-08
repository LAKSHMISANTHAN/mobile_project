package com.example.android.vitb;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


@SuppressLint("ValidFragment")
public class ExampleItem2 extends Fragment {

    String registrationNumber;
    String OutTime;
    public static final int pick_image_request = 234;
    @SuppressLint("ValidFragment")
    ExampleItem2(String r,String o){
        registrationNumber = r;
        OutTime = o;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_example_item2, container, false);
        final RelativeLayout r = (RelativeLayout) view.findViewById(R.id.Relative);
        final StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
        TextView v1 =(TextView) view.findViewById(R.id.textreg);
        TextView v2 = (TextView)view.findViewById(R.id.textOut);
        final ImageView im = (ImageView)view.findViewById(R.id.imageView);
        Button download = (Button)view.findViewById(R.id.download);
        String rrr = v1.getText().toString();
        String ooo = v2.getText().toString();
        rrr = ""+rrr+registrationNumber;
        ooo = ""+ooo+OutTime;
        v1.setText(rrr);
        v2.setText(ooo);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final long ONE_MEGABYTE = 2048 * 2048;
                StorageReference riversReff = mStorageRef.child(registrationNumber);
//        Query q = riversReff.
                riversReff.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        // Data for "images/island.jpg" is returns, use this as needed
                        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                        im.setImageBitmap(bmp);


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                        //Toast.makeText(this,registrationNumber,Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        Button b = (Button) view.findViewById(R.id.okButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new Fragg1(registrationNumber);
                r.setVisibility(View.GONE);
                FrameLayout f = (FrameLayout) view.findViewById(R.id.frameWard);
                f.setVisibility(View.VISIBLE);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frameWard, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
}
