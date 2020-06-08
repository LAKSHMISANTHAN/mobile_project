package com.example.android.vitb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    String p;
    int varStor;
    private EditText name;
    private EditText password;
    private Button login;
    private TextView info;
    public int count = 5;
    private String text;
    public SharedPreferences sp;
    public SharedPreferences.Editor speditor;
    public SharedPreferences sp_details;
    public SharedPreferences.Editor speditor_details;
    String user;
    String username;
    String pass;
    String checkuser="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.button);
        info = (TextView) findViewById(R.id.text);
        sp = getSharedPreferences("Authentication",MODE_PRIVATE);
        sp_details = getSharedPreferences("Details",0);
        speditor =sp.edit();





        info.setText("No. of attempts remaining : " + count);

        //boolean savelogin = sp.getBoolean("logged", true);

        if(sp.getBoolean("logged", false) == true){
            SharedPreferences pre_details =getSharedPreferences("Details", 0);
            SharedPreferences.Editor det_editor = pre_details.edit();
            String u = pre_details.getString("user","None");
            u=""+u.charAt(0)+u.charAt(1);
            if(u.equals("70")){
                Intent i = new Intent(MainActivity.this,MentorActivity.class);
                startActivity(i);
            }
            else if(u.equals("71")){
                Intent i = new Intent(MainActivity.this,ManagerActivity.class);
                startActivity(i);
            }
            else {
                Intent i = new Intent(MainActivity.this, AfterLogin.class);
                startActivity(i);
            }
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = name.getText().toString();
                pass = password.getText().toString();

                checkuser = ""+username.charAt(0)+username.charAt(1);

                if(checkuser.equals("70")){
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myref = database.getReference("login_details");
                    DatabaseReference newRef = myref.child(username);
                    newRef.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                            p = dataSnapshot.getValue(String.class);
                            //Log.d(TAG, "Value is: " + value);
                            Toast.makeText(getApplicationContext(),p,Toast.LENGTH_SHORT).show();
                            if(pass.equals(p)){


                                sp.edit().putBoolean("logged",true).apply();
                                sp_details.edit().putString("user",username).apply();
                                Intent i = new Intent(MainActivity.this,MentorActivity.class);
                                startActivity(i);

                            }else{
                                Toast.makeText(getApplicationContext(),"wrong password",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            //Log.w(TAG, "Failed to read value.", error.toException());


                        }
                    });

                }
                else if(checkuser.equals("71")){
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myref = database.getReference("login_details");
                    DatabaseReference newRef = myref.child(username);
                    newRef.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                            p = dataSnapshot.getValue(String.class);
                            //Log.d(TAG, "Value is: " + value);
                            Toast.makeText(getApplicationContext(),p,Toast.LENGTH_SHORT).show();
                            if(pass.equals(p)){


                                sp.edit().putBoolean("logged",true).apply();
                                sp_details.edit().putString("user",username).apply();
                                Intent i = new Intent(MainActivity.this,ManagerActivity.class);
                                startActivity(i);

                            }else{
                                Toast.makeText(getApplicationContext(),"wrong password",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            //Log.w(TAG, "Failed to read value.", error.toException());


                        }
                    });

                }
                else {
                    Toast.makeText(getApplicationContext(), "Checking", Toast.LENGTH_SHORT).show();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myref = database.getReference("login_details");
                    DatabaseReference newRef = myref.child(username);
                    newRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                            p = dataSnapshot.getValue(String.class);
                            //Log.d(TAG, "Value is: " + value);
                            Toast.makeText(getApplicationContext(), p, Toast.LENGTH_SHORT).show();
                            if (pass.equals(p)) {


                                sp.edit().putBoolean("logged", true).apply();
                                sp_details.edit().putString("user", username).apply();
                                Intent i = new Intent(MainActivity.this,AfterLogin.class);
                                startActivity(i);

                            } else {
                                Toast.makeText(getApplicationContext(), "wrong password", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            //Log.w(TAG, "Failed to read value.", error.toException());


                        }
                    });

                }



            }
        });
    }
//    public void change(){
//        sp.edit().putBoolean("logged",false).apply();
//    }
}
