package com.walter.cojal.easylearning.presentation.main._home.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.walter.cojal.easylearning.R;
import com.walter.cojal.easylearning.data.entities.Assessor;

import java.util.ArrayList;

public class AssessorAdapter extends RecyclerView.Adapter<AssessorAdapter.MyViewHolder> {

    ArrayList<Assessor> items = new ArrayList<>();
    Picasso picasso;

    public AssessorAdapter(Picasso picasso) {
        this.picasso = picasso;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtProfession, txtAge;
        ImageView imgIcon;

        MyViewHolder(@NonNull View itemView) {
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
        Assessor assessor = items.get(position);
        holder.txtName.setText(assessor.getName());
        holder.txtProfession.setText(assessor.getLastName());
        holder.txtAge.setText(String.valueOf(assessor.getAge()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    void setItems(ArrayList<Assessor> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
