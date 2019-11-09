package com.meeting.u;

public class student extends user {

    String custom_name;
    int score;

    public student(String name, String email, String id){
        this.id = id;
        this.email = email;
        this.name = name;
        score = 5;
        user_type = "Estudiante";
    }

}
