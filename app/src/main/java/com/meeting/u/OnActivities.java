package com.meeting.u;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

public class OnActivities extends AppCompatActivity {

    public static String keyID;
    private TextView title;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference activity = database.getReference("Activities/" + keyID );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_activities);

        title = findViewById(R.id.tittle_course);
        updateActivity();


        activity.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // El método verifica si hay un espacio disponile para la actividad y la añade
                activity activity = dataSnapshot.child(keyID).getValue(activity.class);
                //title.setText(activity.getName());


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());

            }
        });
    }

    private void updateActivity(){


        activity.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // El método verifica si hay un espacio disponile para la actividad y la añade
                long available = dataSnapshot.child("availables").getValue(Long.class);
                activity.child("availables").setValue(available-1);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());

            }
        });
    }
}
