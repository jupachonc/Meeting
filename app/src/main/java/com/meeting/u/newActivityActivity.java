package com.meeting.u;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

public class newActivityActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener{

    private String type;
    private EditText mInicio;
    private EditText mFinal;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_activity);

        findViewById(R.id.inicio_time).setOnTouchListener(this);
        findViewById(R.id.final_time).setOnTouchListener(this);

        mInicio = findViewById(R.id.inicio_time);
        mFinal = findViewById(R.id.final_time);




    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int i = v.getId();
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);

        if(i == R.id.inicio_time){

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    if(minute < 10){

                        mInicio.setText(hourOfDay + ":0" + minute);

                    }else{

                        mInicio.setText(hourOfDay + ":" + minute);
                    }


                }
            }, hour, minutes, false);
            timePickerDialog.show();

        }else if(i == R.id.final_time){


            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    if(minute < 10){

                        mFinal.setText(hourOfDay + ":0" + minute);

                    }else{

                        mFinal.setText(hourOfDay + ":" + minute);
                    }


                }
            }, hour, minutes, false);
            timePickerDialog.show();
        }
        return true;
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onStart() {
        super.onStart();
    }


}