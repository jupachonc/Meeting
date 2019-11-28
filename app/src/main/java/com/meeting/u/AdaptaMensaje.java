package com.meeting.u;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

public class AdaptaMensaje extends RecyclerView.Adapter<HolderMessage> {

    private List<Mensaje> listMensaje = new ArrayList<>();
    private Context c;

    public AdaptaMensaje(Context c) {
        this.c = c;
    }

    public void addMessage(Mensaje m) {
        listMensaje.add(m);
        notifyItemInserted(listMensaje.size());
    }

    @NonNull
    @Override
    public HolderMessage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.chat_view,parent,false);
        return new HolderMessage(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMessage holder, int position) {
        holder.getHora().setText(listMensaje.get(position).getHora());
        holder.getMensaje().setText(listMensaje.get(position).getMensaje());
        holder.getNombre().setText(listMensaje.get(position).getNombre());
        if(listMensaje.get(position).getType_mensaje().equals("2")){
            holder.getFotoMensaje().setVisibility(View.VISIBLE);
            holder.getMensaje().setVisibility(View.VISIBLE);
            Glide.with(c).load(listMensaje.get(position).getUrlFoto()).into(holder.getFotoMensaje());
        } else if(listMensaje.get(position).getType_mensaje().equals("1")){
            holder.getFotoMensaje().setVisibility(View.GONE);
            holder.getMensaje().setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return listMensaje.size();
    }
}