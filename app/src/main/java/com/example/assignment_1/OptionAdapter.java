package com.example.assignment_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        viewHolder.optionImage.setImageResource(optionItems.get(position).getOptionImage());
        viewHolder.optionName.setText(optionItems.get(position).getOptionName());

    }

    @Override
    public int getItemCount() {
        return optionItems.size();
    }

    public static class OptionViewHolder extends RecyclerView.ViewHolder{

        public ImageView optionImage;
        public TextView optionName;

        public OptionViewHolder(@NonNull View itemView) {
            super(itemView);
            optionImage = itemView.findViewById(R.id.imageView);
            optionName = itemView.findViewById(R.id.textView2);

        }
    }
}
