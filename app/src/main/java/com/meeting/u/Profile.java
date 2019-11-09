package com.meeting.u;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    public static String email;
    public static String name;
    public static String id;
    private TextView mUserType;
    private TextView mId;
    private TextView mScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user usuario = new student(name, email, id);

        mUserType = findViewById(R.id.usertypw);
        mUserType.setText(usuario.name);
        mId = findViewById(R.id.id);
        mId.setText("ID: "+ usuario.id);
        mScore = findViewById(R.id.score);
        mScore.setText(String.valueOf(usuario.score));

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
