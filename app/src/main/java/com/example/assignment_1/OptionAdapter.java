package com.example.assignment_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class OptionAdapter extends RecyclerView.Adapter{

    private List<Option_Item> optionItems = new ArrayList<>();

    public OptionAdapter(List<Option_Item> optionItems) {
        this.optionItems = optionItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.option_cardview, parent, false);
        return new OptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        OptionViewHolder viewHolder = (OptionViewHolder) holder;
        Option_Item optionItem = optionItems.get(position);
        viewHolder.optionBtn.setText(optionItem.getOptionName());
        viewHolder.optionBtn.setIconResource(optionItem.getOptionImage());
        viewHolder.optionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String category = optionItem.getOptionName();
                List<Product> x;

                if(category.equals("All")){

                    x = MyDataBase.getInstance(v.getContext()).productDao().getAllProducts();
                }

                else{
                    x = MyDataBase.getInstance(v.getContext()).productDao().getProductsByCategory(category);
                }


                RecyclerView rc = v.getRootView().findViewById(R.id.recycler_list);
                rc.setLayoutManager(new LinearLayoutManager(v.getContext()));
                rc.setAdapter(new Deal_List_Adapter(x));


            }
        });


    }

    @Override
    public int getItemCount() {
        return optionItems.size();
    }

    public static class OptionViewHolder extends RecyclerView.ViewHolder{

       public MaterialButton optionBtn;

        public OptionViewHolder(@NonNull View itemView) {
            super(itemView);
            optionBtn = itemView.findViewById(R.id.deal_option_btn);

        }
    }
}
