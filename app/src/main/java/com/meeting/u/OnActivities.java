package com.meeting.u;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

public class OnActivities extends AppCompatActivity {

    public static String keyID;
    private TextView title;
    private TextView name_rep;
    private ImageView image_rep;
    private TextView place_rep;
    private TextView description_rep;
    private TextView aviable_rep;
    private Button join_rep;



    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference activity = database.getReference("Activities/" + keyID );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_activities);

        title = findViewById(R.id.tittle_course);
        name_rep = findViewById(R.id.name_rep);
        image_rep = findViewById(R.id.image_rep);
        place_rep = findViewById(R.id.place_rep);
        description_rep = findViewById(R.id.description_rep);
        aviable_rep = findViewById(R.id.aviable_rep);
        join_rep = findViewById(R.id.join_rep);
        updateActivity();


        activity.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // El método verifica si hay un espacio disponile para la actividad y la añade
                activity activity = dataSnapshot.child(keyID).getValue(activity.class);

                /*
                title.setText(activity.getName());
                name_rep.setText(activity.getName());
                String activityTypeA = activity.getTipo();
                if (Objects.equals(activityTypeA, "otra")) image_rep.setImageResource(R.drawable.otra);
                else if (Objects.equals(activityTypeA, "ocio")) image_rep.setImageResource(R.drawable.ocio);
                else if (Objects.equals(activityTypeA, "deportiva")) image_rep.setImageResource(R.drawable.deportiva);
                else if (Objects.equals(activityTypeA, "academia")) image_rep.setImageResource(R.drawable.academia);
                place_rep.setText(activity.getPlace());
                description_rep.setText(activity.getDescripción());
                aviable_rep.setText(activity.getDisponibles());

                 */
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
