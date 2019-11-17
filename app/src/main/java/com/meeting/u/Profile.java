package com.meeting.u;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    public static String email;
    public static String name;
    public static String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user usuario = new student(name, email, id);

        TextView mUserType = findViewById(R.id.usertypw);
        mUserType.setText(usuario.name);
        TextView mId = findViewById(R.id.id);
        mId.setText("ID: "+ usuario.id);
        TextView mScore = findViewById(R.id.score);
        mScore.setText(String.valueOf(usuario.score));

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

}
