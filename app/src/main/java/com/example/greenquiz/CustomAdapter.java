package com.example.greenquiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    ArrayList user_classement, user_id, user_name, user_score;

    CustomAdapter(Context context, ArrayList user_classement, ArrayList user_id, ArrayList user_name, ArrayList user_score) {
        this.context = context;
        this.user_classement = user_classement;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_score = user_score;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {

        holder.user_classement_text.setText(String.valueOf(user_classement.get(position)));
        holder.user_id_text.setText(String.valueOf(user_id.get(position)));
        holder.user_name_text.setText(String.valueOf(user_name.get(position)));
        holder.user_score_text.setText(String.valueOf(user_score.get(position)));
    }

    @Override
    public int getItemCount() {
        return user_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView user_classement_text, user_id_text, user_name_text, user_score_text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            user_classement_text = itemView.findViewById(R.id.user_classement_text);
            user_id_text = itemView.findViewById(R.id.user_id_text);
            user_name_text = itemView.findViewById(R.id.user_name_text);
            user_score_text = itemView.findViewById(R.id.user_score_text);
        }
    }
}
