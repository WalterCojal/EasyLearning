package com.walter.cojal.easylearning.presentation.home.fragmentHome.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.walter.cojal.easylearning.R;
import com.walter.cojal.easylearning.data.Entities.Asesor;

import java.util.ArrayList;

public class AsesorAdapter extends RecyclerView.Adapter<AsesorAdapter.MyViewHolder> {

    ArrayList<Asesor> items = new ArrayList<>();

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtProfession, txtAge;
        ImageView imgIcon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.l_as_name);
            txtAge = itemView.findViewById(R.id.l_as_age);
            txtProfession = itemView.findViewById(R.id.l_as_profession);
            imgIcon = itemView.findViewById(R.id.l_as_icon);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_asesor, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Asesor asesor = items.get(position);
        holder.txtName.setText(asesor.getName());
        holder.txtProfession.setText(asesor.getLastName());
        holder.txtAge.setText(String.valueOf(asesor.getAge()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    void setItems(ArrayList<Asesor> items) {
        this.items = items;
    }
}
