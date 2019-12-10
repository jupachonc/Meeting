package com.meeting.u;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

public class activity {

    private String tipo;
    private String name;
    private String descripción;
    private String place;
    private String hora_incio;
    private String hora_fin;
    int nparticipantes;
    protected int disponibles;
    String[] participantes;


    public FirebaseDatabase database = FirebaseDatabase.getInstance();
    public DatabaseReference myDB = database.getReference("Activities");

    //Dont Touch
    public activity() {
    }

    public activity(String tipo, String name, String descripción, String place, String hora_incio, String hora_fin, int participantes, String nombre){

        this.tipo = tipo;
        this.name = name;
        this.descripción = descripción;
        this.place = place;
        this.hora_incio = hora_incio;
        this.hora_fin = hora_fin;
        nparticipantes = participantes;
        disponibles = participantes - 1;
        this.participantes = new String[participantes];
        this.participantes[nparticipantes - (disponibles + 1)] = nombre;

    }

    protected void toDB(){
        // Registrar actividad en la Base de Datos

        final DatabaseReference activity = myDB.push();

        //myDB.push().setValue(new activity(tipo, name, descripción, place, hora_incio, hora_fin, nparticipantes, disponibles));

        //DEBARÍAN CONSERVAR EL MISMO NOMBRE EN LA DB<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        activity.child("tipo").setValue(tipo);
        activity.child("name").setValue(name);
        activity.child("description").setValue(descripción);
        activity.child("place").setValue(place);
        activity.child("hora_incio").setValue(hora_incio);
        activity.child("hora_fin").setValue(hora_fin);
        activity.child("amount_participants").setValue(nparticipantes);
        activity.child("availables").setValue(disponibles);

        DatabaseReference actparticipantes = activity.child("participants");

        for (int i = 0; i < participantes.length; i++){

            actparticipantes.child(String.valueOf(i)).setValue(participantes[i]);
        }

        DatabaseReference usuarios = database.getReference("Users");
        DatabaseReference user = usuarios.child(LogInActivity.usuario.id);
        final DatabaseReference activities = user.child("Activities");



            activities.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // El método verifica si hay un espacio disponile para la actividad y la añade

                    for(int i = 0; i < 5; i++) {
                        if(!dataSnapshot.child(String.valueOf(i)).exists()){

                            activities.child(String.valueOf(i)).setValue(activity.getKey());
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


        }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getHora_incio() {
        return hora_incio;
    }

    public void setHora_incio(String hora_incio) {
        this.hora_incio = hora_incio;
    }

    public String getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }

    public int getNparticipantes() {
        return nparticipantes;
    }

    public void setNparticipantes(int nparticipantes) {
        this.nparticipantes = nparticipantes;
    }

    public int getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }

    public String[] getParticipantes() {
        return participantes;
    }

    public void setParticipantes(String[] participantes) {
        this.participantes = participantes;
    }

    public FirebaseDatabase getDatabase() {
        return database;
    }

    public void setDatabase(FirebaseDatabase database) {
        this.database = database;
    }

    public DatabaseReference getMyDB() {
        return myDB;
    }

    public void setMyDB(DatabaseReference myDB) {
        this.myDB = myDB;
    }
}

