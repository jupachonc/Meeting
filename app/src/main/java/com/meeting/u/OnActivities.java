package com.meeting.u;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

public class OnActivities extends AppCompatActivity implements View.OnClickListener {

    public static String keyID;
    public static boolean validator;
    private TextView title;
    private TextView name_rep;
    private TextView hour_rep;
    private ImageView image_rep;
    private TextView place_rep;
    private TextView description_rep;
    private TextView aviable_rep;
    private Button delete_rep;
    private Button openChat_rep;
    private static boolean verifier;
    private int counter;
    activity activity;




    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference activityRF = database.getReference("Activities/" + keyID );



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_activities);

        title = findViewById(R.id.tittle_course);
        name_rep = findViewById(R.id.name_rep);
        image_rep = findViewById(R.id.image_rep);
        hour_rep = findViewById(R.id.hour_rep);
        place_rep = findViewById(R.id.place_rep);
        description_rep = findViewById(R.id.description_rep);
        aviable_rep = findViewById(R.id.aviable_rep);
        delete_rep = findViewById(R.id.delete_rep);
        openChat_rep = findViewById(R.id.openChat_rep);
        delete_rep.setOnClickListener(this);
        openChat_rep.setOnClickListener(this);





        activityRF.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dS) {

                if(dS.child("participants/0").getValue(String.class).equals(MainActivity.userm.name)){
                    delete_rep.setVisibility(View.VISIBLE);
                }

                // El método verifica si hay un espacio disponile para la actividad y la añade
                activity = new activityjoin(keyID, dS.child("tipo").getValue().toString(),
                        dS.child("name").getValue().toString(),
                        dS.child("description").getValue().toString(),
                        dS.child("place").getValue().toString(),
                        dS.child("hora_incio").getValue().toString(),
                        dS.child("hora_fin").getValue().toString(),
                        dS.child("amount_participants").getValue(Integer.class),
                        dS.child("availables").getValue(Integer.class),
                        MainActivity.userm.name);


                title.setText(activity.getName());
                name_rep.setText(activity.getName());
                hour_rep.setText(activity.getHora_incio() + " - " + activity.getHora_fin());
                String activityTypeA = activity.getTipo();
                if (Objects.equals(activityTypeA, "otra")) image_rep.setImageResource(R.drawable.otra);
                else if (Objects.equals(activityTypeA, "ocio")) image_rep.setImageResource(R.drawable.ocio);
                else if (Objects.equals(activityTypeA, "deportiva")) image_rep.setImageResource(R.drawable.deportiva);
                else if (Objects.equals(activityTypeA, "academia")) image_rep.setImageResource(R.drawable.academia);
                place_rep.setText(activity.getPlace());
                description_rep.setText(activity.getDescripción());
                aviable_rep.setText(String.valueOf(activity.getDisponibles() + 1));


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());

            }
        });
    }



    @Override
    public void onClick(View v) {
        int i = v.getId();
        if(i == R.id.delete_rep){
            activityRF.removeValue();
            Intent goToMain = new Intent(this, MainActivity.class);
            startActivity(goToMain);
            final DatabaseReference activities = database.getReference("Users/" +
                    MainActivity.userm.id + "/Actvities");
            activities.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                   for(int i = 0; i < 5; i++){
                       DataSnapshot dataSnapshot1 = dataSnapshot.child(String.valueOf(i));
                       if(dataSnapshot1.exists() && dataSnapshot1.getValue(String.class).equals(keyID)) {
                           OnActivities.vMethod(i);

                       }
                   }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });
            finish();

        }else if(i == R.id.openChat_rep){
            ChatActivityView.chatidentifier = keyID;
            ChatActivityView.titulo = activity.getName();

            Intent goToChat = new Intent(this, ChatActivityView.class);
            startActivity(goToChat);
        }

    }

    public void onActivitiesCourse(){
        final String userid = MainActivity.userm.id;
        DatabaseReference databaseReference = database.getReference();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean v = true;

                for(int i = 0; i < 5; i++){
                    if( dataSnapshot.child("Users/" + userid + "/Activities/" + i).getValue(String.class) == keyID){
                        v = false;
                        toast(1);

                    }

                    if(dataSnapshot.child("Users/" + userid + "/Activities/" + i).exists()){
                        vCounter();}
                }


                if(dataSnapshot.child("Activities/" + keyID + "/availables").getValue(Integer.class) < 1){
                    v =false;
                    toast(2);
                }

                if(counter >= 5){
                    v = false;
                    toast(3);
                }

                OnActivities.setVerifier(v);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());

            }
        });

    }

    public void toast(int i){
        if(i == 1){Toast.makeText(getBaseContext(), "Ya estás en esta actividad", Toast.LENGTH_SHORT).show();}
        else if(i == 2){Toast.makeText(getBaseContext(), "No hay cupos en esta actividad", Toast.LENGTH_SHORT).show();}
        else if(i == 3){Toast.makeText(getBaseContext(), "Ya estás en 5 actividades", Toast.LENGTH_SHORT).show();}

    }

    public static void vMethod(int i){
        FirebaseDatabase dt = FirebaseDatabase.getInstance();
        DatabaseReference activities = dt.getReference("Users/" + MainActivity.userm.id + "/Activities");
        activities.child(String.valueOf(i)).removeValue();
    }

    public void vCounter(){
        counter++;
    }

    public static void setVerifier(boolean v) {
        verifier = v;
    }
}
