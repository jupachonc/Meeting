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

public class newActivityActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener{

    private String type;
    private EditText mInicio;
    private EditText mFinal;
    private ImageView mAcademia;
    private ImageView mDeportiva;
    private ImageView mOcio;
    private ImageView mOtro;
    private ImageView cAcademia;
    private ImageView cDeportiva;
    private ImageView cOcio;
    private ImageView cOtro;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_activity);

        findViewById(R.id.inicio_time).setOnTouchListener(this);
        findViewById(R.id.final_time).setOnTouchListener(this);

        mInicio = findViewById(R.id.inicio_time);
        mFinal = findViewById(R.id.final_time);
        mAcademia = findViewById(R.id.academia);
        mDeportiva = findViewById(R.id.deportiva);
        mOcio = findViewById(R.id.ocio);
        mOtro = findViewById(R.id.otro);
        cAcademia = findViewById(R.id.check_academia);
        cDeportiva = findViewById(R.id.check_deportiva);
        cOcio = findViewById(R.id.check_ocio);
        cOtro = findViewById(R.id.check_otro);





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
            mDeportiva.setImageResource(R.drawable.deportiva_50);
            mOcio.setImageDrawable(getDrawable(R.drawable.ocio_50));
            mOtro.setImageResource(R.drawable.otra_50);
            cDeportiva.setVisibility(View.VISIBLE);
            //cAcademia
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }


}