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
    private float progress_bar_1_alpha;
    private float progress_bar_2_alpha;
    private float progress_bar_3_alpha;
    private float progress_bar_4_alpha;

    public void setImage(int imageSource) {
        this.imagesSource = imageSource;
    }

    public void setProgress_bar_alpha(float progress_bar_1_alpha, float progress_bar_2_alpha, float progress_bar_3_alpha, float progress_bar_4_alpha) {
        this.progress_bar_1_alpha = progress_bar_1_alpha;
        this.progress_bar_2_alpha = progress_bar_2_alpha;
        this.progress_bar_3_alpha = progress_bar_3_alpha;
        this.progress_bar_4_alpha = progress_bar_4_alpha;
    }

    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (getContext() == null) {
            return null;
        } else {
            View view = inflater.inflate(R.layout.deal_detail_fragment, container, false);
            ImageView imageView = view.findViewById(R.id.deal_detail_image);
            View progressDot1 = view.findViewById(R.id.progress_dot_1);
            View progressDot2 = view.findViewById(R.id.progress_dot_2);
            View progressDot3 = view.findViewById(R.id.progress_dot_3);
            View progressDot4 = view.findViewById(R.id.progress_dot_4);

            progressDot1.setAlpha(progress_bar_1_alpha);
            progressDot2.setAlpha(progress_bar_2_alpha);
            progressDot3.setAlpha(progress_bar_3_alpha);
            progressDot4.setAlpha(progress_bar_4_alpha);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transform(new RoundedCorners(30));
            Glide.with(this).load(imagesSource).apply(requestOptions).into(imageView);

            return view;
        }
    }


}
