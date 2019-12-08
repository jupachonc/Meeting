package com.meeting.u;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HolderActivity extends RecyclerView.ViewHolder {
//PERTENECE A COURSE.JAVA
    private TextView titulo;
    private TextView hour;
    private TextView free;
    private ImageView icono;

    public HolderActivity(@NonNull View itemView) {
        super(itemView);

        titulo = itemView.findViewById(R.id.titleList);
        hour = itemView.findViewById(R.id.characList);
        free = itemView.findViewById(R.id.free);
        icono = itemView.findViewById(R.id.iconoActivity);
    }

    public TextView getTitulo() {
        return titulo;
    }

    public void setTitulo(TextView titulo) {
        this.titulo = titulo;
    }

    public TextView getHour() {
        return hour;
    }

    public void setHour(TextView hour) {
        this.hour = hour;
    }

    public ImageView getIcono() {
        return icono;
    }

    public void setIcono(ImageView icono) {
        this.icono = icono;
    }

    public TextView getFree() {
        return free;
    }

    public void setFree(TextView free) {
        this.free = free;
    }
}
