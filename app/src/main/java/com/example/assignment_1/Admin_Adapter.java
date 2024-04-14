package com.example.assignment_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Admin_Adapter extends RecyclerView.Adapter {

    private List<Deal> deal;
    private List<Product> product;
    private List<Customer> customer;
    private List<Supplier> supplier;
    private Context mContext;

    public Admin_Adapter(List<Customer> customer, List<Supplier> supplier, List<Product> product, List<Deal> deal) {
        this.deal = deal;
        this.product = product;
        this.customer = customer;
        this.supplier = supplier;
    }

    public Admin_Adapter() {
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_recycler, parent, false);
        return new Admin_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Admin_ViewHolder viewHolder = (Admin_ViewHolder) holder;

        if (deal != null) {
            viewHolder.list_name.setText(MyDataBase.getInstance(mContext).productDao().getProduct(deal.get(position).getProductId()).getName());
            viewHolder.list_id.setText(deal.get(position).getIdNumber());

        } else if (product != null) {
            viewHolder.list_name.setText(product.get(position).getName());
            viewHolder.list_id.setText(product.get(position).getIdNumber());

        } else if (customer != null) {
            viewHolder.list_name.setText(customer.get(position).getName());
            viewHolder.list_id.setText(customer.get(position).getIdNumber());
            viewHolder.delete.setOnClickListener(v -> {
                MyDataBase.getInstance(mContext).customerDao().delete(customer.get(position));
                customer.remove(position);
                notifyDataSetChanged();
            });

        } else if (supplier != null) {
            viewHolder.list_name.setText(supplier.get(position).getName());
            viewHolder.list_id.setText(supplier.get(position).getIdNumber());
            viewHolder.delete.setOnClickListener(v -> {
                MyDataBase.getInstance(mContext).supplierDao().delete(supplier.get(position));
                supplier.remove(position);
                notifyDataSetChanged();
            });
        }
    }

    @Override
    public int getItemCount() {

        int count;
        if (deal != null) {
            count = deal.size();
        } else if (product != null) {
            count = product.size();
        } else if (customer != null) {
            count = customer.size();
        } else if (supplier != null) {
            count = supplier.size();
        } else {
            count = 0;
        }

        return count;
    }

    public static class Admin_ViewHolder extends RecyclerView.ViewHolder {

        TextView list_id;
        TextView list_name;

        Button delete;

        public Admin_ViewHolder(@NonNull View itemView) {
            super(itemView);
            list_id = itemView.findViewById(R.id.admin_list_id);
            list_name = itemView.findViewById(R.id.admin_list_name);
            delete = itemView.findViewById(R.id.admin_btn_delete);
        }
    }
}
