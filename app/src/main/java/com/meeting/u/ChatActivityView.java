package com.meeting.u;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ChatActivityView extends AppCompatActivity {
    private RecyclerView vistaChat;
    private EditText escribir;
    private ImageButton boton;
    private ImageButton btnFoto;

    private AdaptaMensaje adapter;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    private static final int PHOTO_SEND = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_view);

        vistaChat = (RecyclerView) findViewById(R.id.vistaChat);
        escribir = (EditText) findViewById(R.id.escribir);
        boton = (ImageButton) findViewById(R.id.boton);
        btnFoto = (ImageButton) findViewById(R.id.btnFoto);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chat");//Sala chat

        storage = FirebaseStorage.getInstance();

        adapter = new AdaptaMensaje(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        vistaChat.setLayoutManager(l);
        vistaChat.setAdapter(adapter);


        boton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //adapter.addMessage(new Mensaje("00:00","Juan Camilo",escribir.getText().toString()));
                databaseReference.push().setValue(new Mensaje("00:00",LogInActivity.usuario.name,escribir.getText().toString()));
                escribir.setText("");
            }
        });

        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(i,"Seleccionar foto"),PHOTO_SEND);
            }
        });

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollbar();
            }
        });
///*
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Mensaje m = dataSnapshot.getValue(Mensaje.class);
                adapter.addMessage(m);
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

 //*/
    }

    private void setScrollbar(){
        vistaChat.scrollToPosition(adapter.getItemCount()-1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_SEND && resultCode ==  RESULT_OK){
            Uri u = data.getData();
            storageReference = storage.getReference("imgChat");//imágenes chat
            final StorageReference fotoReferencia = storageReference.child(u.getLastPathSegment());
            fotoReferencia.putFile(u).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> u = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                    Mensaje m = new Mensaje("00:00",LogInActivity.usuario.name,"Imagen",u.toString());
                    databaseReference.push().setValue(m);
                }
            });
        }
    }
}
