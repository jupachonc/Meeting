package com.meeting.u;


import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

public class student extends user {

    public String custom_name;



    public student(String name, String email, String id){
        this.id = id;
        this.email = email;
        this.name = name;
        score = 5.0f;
        user_type = "Estudiante";

    }

    @Override
    public float getScore() {
        super.getScore();
        final float[] f = new float[1];
        // Read from the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Users/" + id);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.child("Score").exists()){
                    f[0] = dataSnapshot.child("Score").getValue(Float.class);
                }else{
                    myRef.child("Score").setValue(5.0f);
                    f[0] = 5.0f;
                }
                //String value = dataSnapshot.getValue(String.class);
                //Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        score = f[0];
        return score;
    }

    /*@Override
    public void getScore(String id){

        final float[] f = new float[1];
        // Read from the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Users/" + id);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.child("Score").exists()){
                    f[0] = dataSnapshot.child("Score").getValue(Float.class);
                }else{
                    myRef.child("Score").setValue(5.0f);
                    f[0] = 5.0f;
                }
                //String value = dataSnapshot.getValue(String.class);
                //Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        score = f[0];
    }*/

}
