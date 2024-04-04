package com.example.assignment_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

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

        Button btn_deal_detail = findViewById(R.id.btn_deal_detail);
        btn_deal_detail.setOnClickListener(v -> {

            Intent intent = new Intent(Deal_Detail_Page.this, Login_Page.class);
            startActivity(intent);
        });





    }
}