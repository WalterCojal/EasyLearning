package com.walter.cojal.easylearning.presentation.assessor.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.walter.cojal.easylearning.R;

import java.util.ArrayList;

public class StringAdapter extends RecyclerView.Adapter<StringAdapter.MyViewHolder> {

    private ArrayList<String> items = new ArrayList<>();
    private OnItemClick itemClick;

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView label;

        MyViewHolder(@NonNull View itemView){
            super(itemView);
            label = itemView.findViewById(R.id.string_value);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClick.onClick(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_string, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.label.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    void updateItems(ArrayList<String> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    void setOnClickListener(OnItemClick itemClick) {
        this.itemClick = itemClick;
    }
}
