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
    private Button join_rep;
    private Button openChat_rep;
    private static boolean verifier;
    private int counter;
    activity activity;
    final Context context = getBaseContext();




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
        join_rep = findViewById(R.id.join_rep);
        openChat_rep = findViewById(R.id.openChat_rep);
        join_rep.setOnClickListener(this);
        openChat_rep.setOnClickListener(this);

        if(validator){
            join_rep.setVisibility(View.GONE);
            openChat_rep.setVisibility(View.VISIBLE);
        }else{
            join_rep.setVisibility(View.VISIBLE);
            openChat_rep.setVisibility(View.GONE);
        }



        activityRF.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dS) {

                // El método verifica si hay un espacio disponile para la actividad y la añade
                activity = new activityjoin(keyID, dS.child("tipo").getValue().toString(),
                        dS.child("name").getValue().toString(),
                        dS.child("description").getValue().toString(),
                        dS.child("place").getValue().toString(),
                        dS.child("hora_incio").getValue().toString(),
                        dS.child("hora_fin").getValue().toString(),
                        dS.child("amount_participants").getValue(Integer.class),
                        dS.child("availables").getValue(Integer.class),
                        LogInActivity.usuario.name);


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
        if(i == R.id.join_rep){
            verifier = true;
            onActivitiesCourse();
            if(verifier){activity.toDB();}

        }else if(i == R.id.openChat_rep){
            ChatActivityView.chatidentifier = keyID;

            Intent goToChat = new Intent(this, ChatActivityView.class);
            startActivity(goToChat);
        }

    }

    public void onActivitiesCourse(){
        final String userid = LogInActivity.usuario.id;
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

    public void vMethod(boolean v){
        if (v) verifier = true;
        else verifier = false;
    }

    public void vCounter(){
        counter++;
    }

    public static void setVerifier(boolean v) {
        verifier = v;
    }
}
