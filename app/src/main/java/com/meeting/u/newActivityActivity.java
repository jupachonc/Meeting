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
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

public class newActivityActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener{

    private String type;
    private EditText mDescription;
    private EditText mPlace;
    private EditText mParticipantes;
    private EditText mInicio;
    private EditText mFinal;
    private ImageView mAcademia;
    private ImageView mDeportiva;
    private ImageView mOcio;
    private ImageView mOtro;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_activity);

        findViewById(R.id.inicio_time).setOnTouchListener(this);
        findViewById(R.id.final_time).setOnTouchListener(this);
        findViewById(R.id.academia).setOnClickListener(this);
        findViewById(R.id.deportiva).setOnClickListener(this);
        findViewById(R.id.ocio).setOnClickListener(this);
        findViewById(R.id.otro).setOnClickListener(this);
        findViewById(R.id.crear).setOnClickListener(this);

        mDescription = findViewById(R.id.description);
        mPlace = findViewById(R.id.place);
        mParticipantes = findViewById(R.id.nparticipantes);
        mInicio = findViewById(R.id.inicio_time);
        mFinal = findViewById(R.id.final_time);
        mAcademia = findViewById(R.id.academia);
        mDeportiva = findViewById(R.id.deportiva);
        mOcio = findViewById(R.id.ocio);
        mOtro = findViewById(R.id.otro);




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
        int i = v.getId();
        if(i == R.id.academia){
            type = "academia";
            mAcademia.setImageResource(R.drawable.academia);
            mDeportiva.setImageResource(R.drawable.deportiva_50);
            mOcio.setImageResource(R.drawable.ocio_50);
            mOtro.setImageResource(R.drawable.otra_50);

        }else if (i == R.id.deportiva){
            type = "deportiva";
            mAcademia.setImageResource(R.drawable.academia_50);
            mDeportiva.setImageResource(R.drawable.deportiva);
            mOcio.setImageResource(R.drawable.ocio_50);
            mOtro.setImageResource(R.drawable.otra_50);

        }else if (i == R.id.ocio){
            type = "ocio";
            mAcademia.setImageResource(R.drawable.academia_50);
            mDeportiva.setImageResource(R.drawable.deportiva_50);
            mOcio.setImageResource(R.drawable.ocio);
            mOtro.setImageResource(R.drawable.otra_50);

        }else if (i == R.id.otro){
            type = "otra";
            mAcademia.setImageResource(R.drawable.academia_50);
            mDeportiva.setImageResource(R.drawable.deportiva_50);
            mOcio.setImageResource(R.drawable.ocio_50);
            mOtro.setImageResource(R.drawable.otra);
        }else if (i == R.id.crear){
            cActivity();
            Toast.makeText(getBaseContext(), "Actividad Creada", Toast.LENGTH_SHORT).show();
            finish();

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    protected void cActivity(){

        activity activity = new activity(type, mDescription.getText().toString(), mPlace.getText().toString(),
                mInicio.getText().toString(), mFinal.getText().toString(),
                Integer.parseInt(mParticipantes.getText().toString()), MainActivity.userm.name);
    }


}