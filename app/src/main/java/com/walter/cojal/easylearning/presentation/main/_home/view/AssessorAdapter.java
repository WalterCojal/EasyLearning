package com.walter.cojal.easylearning.presentation.main._home.view;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.walter.cojal.easylearning.R;
import com.walter.cojal.easylearning.data.entities.Assessor;

import java.util.ArrayList;

public class AssessorAdapter extends RecyclerView.Adapter<AssessorAdapter.MyViewHolder> {

    private ArrayList<Assessor> items = new ArrayList<>();
    private OnAssessorListener assessorListener;
    Picasso picasso;

    public AssessorAdapter(Picasso picasso) {
        this.picasso = picasso;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtName, txtProfession, txtAge, txtRating;
        RoundedImageView imgIcon;
        Button btnFav;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.l_as_name);
            txtAge = itemView.findViewById(R.id.l_as_age);
            txtProfession = itemView.findViewById(R.id.l_as_profession);
            imgIcon = itemView.findViewById(R.id.l_as_icon);
            txtRating = itemView.findViewById(R.id.l_as_rating);
            btnFav = itemView.findViewById(R.id.l_as_fav);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            assessorListener.itemViewClick(items.get(getAdapterPosition()));
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_asesor, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Assessor assessor = items.get(position);
        holder.txtName.setText(assessor.getName() + " " + assessor.getLastName());
        holder.txtProfession.setText(assessor.getAcademic());
        holder.txtAge.setText(assessor.getAge() + " años");
        picasso.load(assessor.getImage()).into(holder.imgIcon);
        if (assessor.getFavorite() == 0) {
            holder.btnFav.setBackgroundResource(R.drawable.ic_heart);
        } else {
            holder.btnFav.setBackgroundResource(R.drawable.ic_favorite);
        }
        holder.btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assessorListener.itemFavClick(items.get(holder.getAdapterPosition()).getId(), holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (items == null) return 0;
        else return items.size();
    }

    public void setItems(ArrayList<Assessor> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void setOnAssessorClickListener(OnAssessorListener assessorListener) {
        this.assessorListener = assessorListener;
    }

    public void updateItemFavorite(int position) {
        int fav = items.get(position).getFavorite();
        if (fav == 1) items.get(position).setFavorite(0);
        else items.get(position).setFavorite(1);
        notifyItemChanged(position);
    }

    public void removeItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }
}
