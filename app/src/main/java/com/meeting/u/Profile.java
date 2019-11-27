package com.meeting.u;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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
        TextView mScore = findViewById(R.id.score);
        mScore.setText(String.valueOf(LogInActivity.usuario.score));

    }

}
