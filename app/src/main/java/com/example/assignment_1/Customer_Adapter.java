package com.example.assignment_1;

import android.content.Context;
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

public class Customer_Adapter extends RecyclerView.Adapter{

    private List<Deal> x;
    private Context mContext;

    public Customer_Adapter(List<Deal> x) {
        this.x = x;
    }


    
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_detail, parent, false);
        return new Customer_Adapter.Customer_Detail_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        int productID = x.get(position).getProductId();
        Product product = MyDataBase.getInstance(mContext).productDao().getProduct(productID);

        Customer_Detail_ViewHolder viewHolder = (Customer_Detail_ViewHolder) holder;
        viewHolder.dealName.setText("Deal Name: " + product.getName());
        viewHolder.dealNum.setText("Order Number: " + product.getIdNumber());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transform(new RoundedCorners(30));
        Glide.with(mContext).load(product.getImage_1()).apply(requestOptions).into(viewHolder.dealImage);

    }

    @Override
    public int getItemCount() {
        return x.size();
    }

    public static class Customer_Detail_ViewHolder extends RecyclerView.ViewHolder {

        public TextView dealName;
        public TextView dealNum;
        public ImageView dealImage;

        public Customer_Detail_ViewHolder(@NonNull View itemView) {
            super(itemView);
            dealName = itemView.findViewById(R.id.customer_lbl_deal_name);
            dealNum = itemView.findViewById(R.id.customer_lbl_deal_number);
            dealImage = itemView.findViewById(R.id.customer_img_deal_image);

        }
    }
}
