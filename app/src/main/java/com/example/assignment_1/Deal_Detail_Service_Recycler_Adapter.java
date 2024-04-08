package com.example.assignment_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Deal_Detail_Service_Recycler_Adapter extends RecyclerView.Adapter{


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_cardview, parent, false);
         return new Deal_Detail_Service_Recycler_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class Deal_Detail_Service_Recycler_ViewHolder extends RecyclerView.ViewHolder{

       public Deal_Detail_Service_Recycler_ViewHolder(View itemView){
            super(itemView);
        }
    }

}
