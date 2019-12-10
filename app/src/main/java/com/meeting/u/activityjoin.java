package com.meeting.u;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

public class activityjoin extends activity {

    private String id;
    public FirebaseDatabase database = FirebaseDatabase.getInstance();
    public DatabaseReference activity;
    String nombre;


    activityjoin(String id, String tipo, String name, String descripción, String place, String hora_incio, String hora_fin, int participantes, int available, String nombre) {
        super(tipo, name, descripción, place, hora_incio, hora_fin, participantes, nombre);

        this.id = id;
        disponibles = available - 1;
        activity = database.getReference("Activities/" + id);
        this.nombre = nombre;
    }

    @Override
    protected void toDB(){

        final DatabaseReference actparticipantes = activity.child("participants");



        DatabaseReference usuarios = database.getReference("Users");
        DatabaseReference user = usuarios.child(LogInActivity.usuario.id);
        final DatabaseReference activities = user.child("Activities");

        activity.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = (dataSnapshot.child("amount_participants").getValue(Integer.class) - dataSnapshot.child("availables").getValue(Integer.class));
                actparticipantes.child(String.valueOf(i)).setValue(nombre);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());

            }
        });


        activities.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(int i = 0; i < 5; i++) {
                    if(!dataSnapshot.child(String.valueOf(i)).exists()){

                        activities.child(String.valueOf(i)).setValue(id);
                        break;
                    }


                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());

            }
        });
        activity.child("availables").setValue(disponibles);

    }

}
