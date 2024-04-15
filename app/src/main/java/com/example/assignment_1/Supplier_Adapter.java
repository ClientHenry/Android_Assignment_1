package com.example.assignment_1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

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

        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(mContext)
                        .setTitle("Delete Product")
                        .setMessage("Are you sure you want to delete this product?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .setNegativeButton("No", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .show();
            }
        });

        viewHolder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Supplier_Edit_Page.class);
                intent.putExtra("product", x.get(position).getName());
                intent.putExtra("productID", x.get(position).getIdNumber());
                intent.putExtra("productCategory", x.get(position).getCategory());
                intent.putExtra("productPrice", x.get(position).getPrice());
                intent.putExtra("productDiscount", x.get(position).getDiscount());
                intent.putExtra("productDescription", x.get(position).getDescription());
                intent.putExtra("productImage1", x.get(position).getImage_1());
                intent.putExtra("productImage2", x.get(position).getImage_2());
                intent.putExtra("productImage3", x.get(position).getImage_3());
                intent.putExtra("productImage4", x.get(position).getImage_4());
                intent.putExtra("productDate", x.get(position).getDate());
                mContext.startActivity(intent);
            }
        });

        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(mContext)
                        .setTitle("Delete Product")
                        .setMessage("Are you sure you want to delete this product?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .setNegativeButton("No", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return x.size();
    }

    public class Supplier_Detail_ViewHolder extends RecyclerView.ViewHolder {

        public TextView productName;
        public TextView productNum;
        public ImageView productImage;
        public Button deleteButton;
        public Button editButton;


        public Supplier_Detail_ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.supplier_lbl_product_name);
            productNum = itemView.findViewById(R.id.supplier_lbl_product_number);
            productImage = itemView.findViewById(R.id.supplier_img_product);
            deleteButton = itemView.findViewById(R.id.supplier_btn_delete);
            editButton = itemView.findViewById(R.id.supplier_btn_edit);
        }
    }
}
