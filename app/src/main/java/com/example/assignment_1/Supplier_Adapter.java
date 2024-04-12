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

public class Supplier_Adapter extends RecyclerView.Adapter{

    private List<Product> x;
    private Context mContext;

    public Supplier_Adapter(List<Product> x) {
        this.x = x;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.supplier_detail, parent, false);
        return new Supplier_Adapter.Supplier_Detail_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Supplier_Detail_ViewHolder viewHolder = (Supplier_Detail_ViewHolder) holder;
        viewHolder.productName.setText("Product Name: " + x.get(position).getName());
        viewHolder.productNum.setText("Product Number: " + x.get(position).getIdNumber());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transform(new RoundedCorners(30));
        Glide.with(mContext).load(x.get(position).getImage_1()).apply(requestOptions).into(viewHolder.productImage);
    }

    @Override
    public int getItemCount() {
        return x.size();
    }

    public class Supplier_Detail_ViewHolder extends RecyclerView.ViewHolder {

        public TextView productName;
        public TextView productNum;
        public ImageView productImage;
        public Supplier_Detail_ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.supplier_lbl_product_name);
            productNum = itemView.findViewById(R.id.supplier_lbl_product_number);
            productImage = itemView.findViewById(R.id.supplier_img_product);
        }
    }
}
