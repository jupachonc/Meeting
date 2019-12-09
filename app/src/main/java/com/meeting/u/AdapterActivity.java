package com.meeting.u;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterActivity extends RecyclerView.Adapter<HolderActivity> implements View.OnClickListener{
    @NonNull
    //>private List<DatosCourse> listActivity = new ArrayList<>();
    private List<activity> listActivity = new ArrayList<>();//new
    private Context c;
    private View.OnClickListener listener;

    public AdapterActivity(@NonNull Context c) {
        this.c = c;
    }

    //>public void addActivity(DatosCourse m){
    public void addActivity(activity m){//new
        listActivity.add(m);
        notifyItemInserted(listActivity.size());
    }

    @Override
    public HolderActivity onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.course_view,parent,false);

        v.setOnClickListener(this);//<<View.OnClickListener

        return new HolderActivity(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderActivity holder, int position) {
        holder.getTitulo().setText(listActivity.get(position).getName());//<Se añaden los datos que se añadieron a la DB
        holder.getHour().setText(listActivity.get(position).getHora_incio() + " - " + listActivity.get(position).getHora_fin());
        holder.getFree().setText(listActivity.get(position).getPlace());
        String activityType = listActivity.get(position).getTipo();
        if (Objects.equals(activityType, "otra")) holder.getIcono().setImageResource(R.drawable.otra);
        else if (Objects.equals(activityType, "ocio")) holder.getIcono().setImageResource(R.drawable.ocio);
        else if (Objects.equals(activityType, "deportiva")) holder.getIcono().setImageResource(R.drawable.deportiva);
        else if (Objects.equals(activityType, "academia")) holder.getIcono().setImageResource(R.drawable.academia);
    }

    @Override
    public int getItemCount() {
        return listActivity.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }
}