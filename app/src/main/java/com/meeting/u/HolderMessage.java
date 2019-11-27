package com.meeting.u;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HolderMessage extends RecyclerView.ViewHolder {

    private TextView hora;
    private TextView nombre;
    private TextView mensaje;

    public HolderMessage(@NonNull View itemView) {
        super(itemView);

        hora = (TextView) itemView.findViewById(R.id.hora);
        nombre = (TextView) itemView.findViewById(R.id.nombre);
        mensaje = (TextView) itemView.findViewById(R.id.mensaje);
    }

    public TextView getHora() {
        return hora;
    }

    public void setHora(TextView hora) {
        this.hora = hora;
    }

    public TextView getNombre() {
        return nombre;
    }

    public void setNombre(TextView nombre) {
        this.nombre = nombre;
    }

    public TextView getMensaje() {
        return mensaje;
    }

    public void setMensaje(TextView mensaje) {
        this.mensaje = mensaje;
    }
}
