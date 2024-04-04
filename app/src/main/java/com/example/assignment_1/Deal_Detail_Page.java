package com.example.assignment_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class Deal_Detail_Page extends AppCompatActivity {
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_detail_page);

        mViewPager = findViewById(R.id.view_pager);
        View_Pager_Adapter adapter = new View_Pager_Adapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);

        RecyclerView rc_service = findViewById(R.id.recycler_serivce);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(RecyclerView.HORIZONTAL);
        rc_service.setLayoutManager(lm);
        rc_service.setAdapter(new Deal_Detail_Service_Recycler_Adapter());



    }
}