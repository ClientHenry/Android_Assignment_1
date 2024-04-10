package com.example.assignment_1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class View_Pager_Fragment extends Fragment {

    private int imagesSource;

    public void setImage(int imageSource){
        this.imagesSource = imageSource;
    }

    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (getContext() == null) {
            return null; // 如果 Fragment 未附加到 Context,返回 null
        }
else{
        View view = inflater.inflate(R.layout.deal_detail_fragment, container, false);
        ImageView imageView = view.findViewById(R.id.deal_detail_image);

        Glide.with(this)
                .load(imagesSource)
                .into(imageView);

        return view;}
    }


}
