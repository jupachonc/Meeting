package com.meeting.u;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    public static String email;
    public static String name;
    public static String id;
    private TextView mUserType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user user = new student(name, email, id);

        mUserType = findViewById(R.id.usertypw);
        mUserType.setText(user.name);

    }
}
