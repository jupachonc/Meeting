package com.meeting.u;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class newActivityActivity extends AppCompatActivity implements View.OnClickListener{

    private String type;
    private EditText mName;
    private EditText mDescription;
    private EditText mPlace;
    private EditText mParticipantes;
    private EditText mInicio;
    private EditText mFinal;
    private ImageView mAcademia;
    private ImageView mDeportiva;
    private ImageView mOcio;
    private ImageView mOtro;
    public Button crear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_activity);

        findViewById(R.id.inicio_time).setOnClickListener(this);
        findViewById(R.id.final_time).setOnClickListener(this);
        findViewById(R.id.academia).setOnClickListener(this);
        findViewById(R.id.deportiva).setOnClickListener(this);
        findViewById(R.id.ocio).setOnClickListener(this);
        findViewById(R.id.otro).setOnClickListener(this);
        findViewById(R.id.crear).setOnClickListener(this);

        mName = findViewById(R.id.activityname);
        mDescription = findViewById(R.id.description);
        mPlace = findViewById(R.id.place);
        mParticipantes = findViewById(R.id.nparticipantes);
        mInicio = findViewById(R.id.inicio_time);
        mFinal = findViewById(R.id.final_time);
        mAcademia = findViewById(R.id.academia);
        mDeportiva = findViewById(R.id.deportiva);
        mOcio = findViewById(R.id.ocio);
        mOtro = findViewById(R.id.otro);
        type = "";

    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        int i = v.getId();
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);

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

            uploadActi();

        }else if(i == R.id.inicio_time){

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        mInicio.setText(String.format("%02d:%02d", hourOfDay, minute));

                }
            }, hour, minutes, false);
            timePickerDialog.show();

        }else if(i == R.id.final_time){

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        mFinal.setText(String.format("%02d:%02d", hourOfDay, minute));

                }
            }, hour, minutes, false);
            timePickerDialog.show();
        }
    }

    public void uploadActi() {
        if((type != "") && (toString(mName) != "") && (toString(mDescription) != "") && (toString(mPlace) != "")
                && (toString(mInicio) != "") && (toString(mFinal) != "") && validNumber(mParticipantes)
                && (mName.length()<=32) && (mPlace.length()<=22)){

            cActivity();
            Toast.makeText(getBaseContext(), "Actividad Creada", Toast.LENGTH_SHORT).show();
            finish();

        }
        else if (Objects.equals(type, ""))Toast.makeText(getBaseContext(), "Selecciona tipo de actividad", Toast.LENGTH_SHORT).show();
        else if (mName.length()>32) Toast.makeText(getBaseContext(), "Nombre demasiado largo", Toast.LENGTH_SHORT).show();
        else if (mPlace.length()>22) Toast.makeText(getBaseContext(), "Lugar demasiado largo", Toast.LENGTH_SHORT).show();
        else Toast.makeText(getBaseContext(), "Revisa tus entradas", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    protected void cActivity(){

        activity activity = new activity(type, mName.getText().toString(), mDescription.getText().toString(), mPlace.getText().toString(),
                mInicio.getText().toString(), mFinal.getText().toString(),
                Integer.parseInt(mParticipantes.getText().toString()), LogInActivity.usuario.name);
        activity.toDB();
    }

    protected String toString(EditText s){
        return s.getText().toString();
    }

    protected boolean validNumber(EditText num){
        boolean check = false;
        try {
            int number = Integer.parseInt(toString(num));
            if (1 < number && number <= 15 ){check = true;}
        } catch (NumberFormatException e) {
        }
        return check;
    }

}