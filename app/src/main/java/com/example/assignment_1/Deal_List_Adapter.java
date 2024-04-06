package com.example.assignment_1;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;
import java.util.Locale;


public class Deal_List_Adapter extends RecyclerView.Adapter{

    private List<Deal_List> x;
    // usage of the Glide library
    private Context mContext;

    public Deal_List_Adapter(List<Deal_List> x) {
        this.x = x;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_cardview, parent, false);
        return new Deal_List_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Deal_List_ViewHolder viewHolder = (Deal_List_ViewHolder) holder;
        viewHolder.deal_name.setText(x.get(position).getName());
        viewHolder.deal_category.setText(x.get(position).getCategory());
        viewHolder.deal_star.setText(String.format(Locale.getDefault(), "%.1f", x.get(position).getStar()));

        viewHolder.deal_date.setText(x.get(position).getDate());
        viewHolder.deal_price.setText(String.format(Locale.getDefault(), "%.0f", x.get(position).getPrice()));;
        viewHolder.deal_discount.setText(String.format(Locale.getDefault(), "%.1f", x.get(position).getDiscount()));

        // modification of the image using the Glide library
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transform(new RoundedCorners(30));
        Glide.with(mContext).load(x.get(position).getImage_src()).apply(requestOptions).into(viewHolder.deal_image);
    }

    @Override
    public int getItemCount() {
        return x.size();
    }

    public static class Deal_List_ViewHolder extends RecyclerView.ViewHolder{

        public TextView deal_name;
        public TextView deal_category;
        public ImageView deal_image;
        public TextView deal_star;
        public TextView deal_date;
        public TextView deal_price;
        public TextView deal_discount;


        public Deal_List_ViewHolder(@NonNull View itemView) {
            super(itemView);
            deal_name = itemView.findViewById(R.id.deal_txt_deal_name);
            deal_category = itemView.findViewById(R.id.deal_txt_deal_category);
            deal_star = itemView.findViewById(R.id.deal_txt_deal_grade);
            deal_date = itemView.findViewById(R.id.deal_txt_deal_date);
            deal_price = itemView.findViewById(R.id.deal_txt_deal_price);
            deal_discount = itemView.findViewById(R.id.deal_txt_deal_discount);
            deal_image = itemView.findViewById(R.id.deal_imageView);
        }
    }
}
