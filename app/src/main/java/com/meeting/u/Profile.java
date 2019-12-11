package com.meeting.u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        findViewById(R.id.Profile).setOnClickListener(this);
        updateU();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        updateU();
    }

    @Override
    protected void onStart() {
        super.onStart();

        updateU();
    }

    @Override
    protected void onPause() {
        super.onPause();
        updateU();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    protected void updateU(){

        TextView mUserType = findViewById(R.id.usertypw);
        mUserType.setText(LogInActivity.usuario.name);
        TextView mId = findViewById(R.id.id);
        mId.setText("ID: "+ LogInActivity.usuario.id);
        final TextView mScore = findViewById(R.id.score);
        //mScore.setText(String.valueOf(LogInActivity.usuario.getScore()));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users/" + MainActivity.userm.id + "/Score");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mScore.setText(String.valueOf(dataSnapshot.getValue(Float.class)));

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if(i == R.id.Profile){

            Intent goToMain = new Intent(this, LogInActivity.class);
            startActivity(goToMain);
        }

    }
}
