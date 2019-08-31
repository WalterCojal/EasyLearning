package com.walter.cojal.easylearning.presentation.main._favotire.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.walter.cojal.easylearning.R;
import com.walter.cojal.easylearning.data.entities.Assessor;

import java.util.ArrayList;

import javax.inject.Inject;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> {

    private ArrayList<Assessor> items = new ArrayList<>();
    Picasso picasso;

    @Inject
    public FavoriteAdapter(Picasso picasso) {
        this.picasso = picasso;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_asesor, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Assessor assessor = items.get(position);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    void updateItems(ArrayList<Assessor> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
