package com.example.assignment_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class Deal_Detail_Page extends AppCompatActivity {
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_detail_page);

        int id = getIntent().getIntExtra("product_id", 0);
        Product product = MyDataBase.getInstance(getApplicationContext()).productDao().getProduct(id);

        TextView deal_name = findViewById(R.id.deal_detail_lbl_Name);
        TextView deal_category = findViewById(R.id.deal_detail_lbl_category);
        TextView deal_price = findViewById(R.id.deal_detail_lbl_price);
        TextView deal_discount = findViewById(R.id.deal_detail_lbl_discount);
        TextView deal_description = findViewById(R.id.deal_detail_lbl_description);
        TextView deal_mark = findViewById(R.id.deal_detail_lbl_mark);
        TextView deal_date = findViewById(R.id.deal_detail_lbl_date);

        deal_name.setText(product.getName());
        deal_category.setText(product.getCategory());
        deal_price.setText(Integer.toString(product.getPrice()));
        deal_discount.setText(Integer.toString(product.getDiscount()));
        deal_description.setText(product.getDescription());
        deal_mark.setText(product.getMark());
        deal_date.setText(product.getDate());

        View_Pager_Adapter adapter = new View_Pager_Adapter(getSupportFragmentManager(), id, getApplicationContext());
        mViewPager = findViewById(R.id.deal_detail_view_pager);
        mViewPager.setAdapter(adapter);


        bottomNavigation();


/*
        Button btn_deal_detail = findViewById(R.id.deal_detail_btn);
        btn_deal_detail.setOnClickListener(v -> {

            Intent intent = new Intent(Deal_Detail_Page.this, Login_Page.class);
            startActivity(intent);
        });*/

    }

    private void bottomNavigation() {

        NavigationBarView nav = findViewById(R.id.bottom_navigation);
        NavigationBarView.OnItemSelectedListener listener = new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.item_1) {

                    Intent intent = new Intent(Deal_Detail_Page.this, Customer_Page.class);
                    startActivity(intent);


                    return true;
                } else if (item.getItemId() == R.id.item_2) {

                    return true;
                } else
                    return false;
            }
        };
        nav.setOnItemSelectedListener(listener);
    }

}