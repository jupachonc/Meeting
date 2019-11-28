package com.meeting.u;

public class activity {

    String tipo;
    String descripción;
    String hora_incio;
    String hora_fin;
    int nparticipantes;
    int disponibles;
    String[] participantes = new String[nparticipantes];

    activity(String tipo, String descripción, String hora_incio, String hora_fin, int participantes, String nombre){

        this.tipo = tipo;
        this.descripción = descripción;
        this.hora_incio = hora_incio;
        this.hora_fin = hora_fin;
        nparticipantes = participantes;
        disponibles = participantes - 1;
        this.participantes[0] = nombre;

    }


}
