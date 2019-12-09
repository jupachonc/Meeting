package com.meeting.u;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.print.PrinterId;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static java.lang.annotation.ElementType.CONSTRUCTOR;

public class Course extends AppCompatActivity {

    private RecyclerView vista;

    private AdapterActivity adapter;

    private FirebaseDatabase databaseA;
    private DatabaseReference databaseReferenceA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        vista = findViewById(R.id.vista);

        databaseA = FirebaseDatabase.getInstance();
        databaseReferenceA = databaseA.getReference("Activities");//Activity name HAY QUE RECIBIR ESTO DE LOS DATOS QUE HAYA INGRESADO EL USUARIO


        adapter = new AdapterActivity(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        vista.setLayoutManager(l);
        vista.setAdapter(adapter);


        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = AdapterActivity.listActivitykeys.get(vista.getChildAdapterPosition(view));
                OnActivities.keyID = id;
                Intent goToOnActivity = new Intent(Course.this, OnActivities.class);
                startActivity(goToOnActivity);
            }
        });



        //BOTON ACTUALIZAR Y ENVÍO LOS DATOS
        //DONT TOUCH
        /*final newActivityActivity newActivityActivity = new newActivityActivity();
        newActivityActivity.crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //databaseReferenceA.push().setValue(new DatosCourse(DATOS A ENVIAR AL CONSTRUCTOR DE DATOSCOURSE));
                newActivityActivity.uploadActi();
            }
        });

         */

        databaseReferenceA.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //Cuando añade datos a la base de datos, los actualiza en Recycler View (la vista del cliente)
                activity m = dataSnapshot.getValue(activity.class);
                adapter.addActivity(m, dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
