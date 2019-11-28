package com.meeting.u;

public class activity {

    String tipo;
    String descripción;
    String place;
    String hora_incio;
    String hora_fin;
    int nparticipantes;
    int disponibles;
    String[] participantes;

    activity(String tipo, String descripción, String place, String hora_incio, String hora_fin, int participantes, String nombre){

        this.tipo = tipo;
        this.descripción = descripción;
        this.place = place;
        this.hora_incio = hora_incio;
        this.hora_fin = hora_fin;
        nparticipantes = participantes;
        disponibles = participantes - 1;
        this.participantes = new String[participantes];
        this.participantes[0] = nombre;

    }


}
