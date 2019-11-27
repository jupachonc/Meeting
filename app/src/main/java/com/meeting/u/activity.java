package com.meeting.u;

public class activity {

    String tipo;
    String descripción;
    String hora_incio;
    String hora_fin;
    String[] participantes;

    activity(String tipo, String descripción, String hora_incio, String hora_fin, int participantes){

        this.tipo = tipo;
        this.descripción = descripción;
        this.hora_incio = hora_incio;
        this.hora_fin = hora_fin;
        this.participantes = new String[participantes];

    }


}
