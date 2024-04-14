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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class Deal_Detail_Page extends AppCompatActivity {
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_detail_page);

        int id = getIntent().getIntExtra("product_id", 0);
        Product product = MyDataBase.getInstance(getApplicationContext()).productDao().getProduct(id);
        String category = product.getCategory();

        TextView deal_name = findViewById(R.id.deal_detail_lbl_Name);
        TextView deal_price = findViewById(R.id.deal_detail_lbl_price);
        TextView deal_discount = findViewById(R.id.deal_detail_lbl_discount);
        TextView deal_description = findViewById(R.id.deal_detail_lbl_description);
        TextView deal_mark = findViewById(R.id.deal_detail_lbl_mark);
        TextView deal_date = findViewById(R.id.deal_detail_lbl_date);
        FloatingActionButton btn_return = findViewById(R.id.deal_detail_btn_return);
        Button btn_more = findViewById(R.id.deal_detail_btn_more);
        Button btn_deal_detail = findViewById(R.id.deal_detail_btn);

        deal_name.setText(product.getName());
        deal_price.setText("$ " + Integer.toString(product.getPrice()));
        deal_discount.setText(Integer.toString(product.getDiscount()) + "% Off");
        deal_description.setText(product.getDescription());
        deal_mark.setText(product.getMark());
        deal_date.setText(product.getDate());
        btn_more.setText("More from " + category + " >>");

        View_Pager_Adapter adapter = new View_Pager_Adapter(getSupportFragmentManager(), id, getApplicationContext());
        mViewPager = findViewById(R.id.deal_detail_view_pager);
        mViewPager.setAdapter(adapter);

        btn_return.setOnClickListener(v -> {
            finish();
        });

        btn_more.setOnClickListener(v -> {
            ProgressBar progressBar = findViewById(R.id.deal_detail_progress_bar);
            progressBar.setVisibility(ProgressBar.VISIBLE);
            Intent intent = new Intent(Deal_Detail_Page.this, Home_Page.class);
            intent.putExtra("category", category);
            startActivity(intent);
        });

        btn_deal_detail.setOnClickListener(v -> {

            Customer customer = MyDataBase.getInstance(getApplicationContext()).customerDao().getLogin();
            if(customer != null) {

                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(Deal_Detail_Page.this);
                builder.setTitle("Confirm Order").setMessage("Are you sure you want to order this deal?")
                       .setPositiveButton("Confirm", (dialog, which) -> {

                            ProgressBar progressBar = findViewById(R.id.deal_detail_progress_bar);
                            progressBar.setVisibility(ProgressBar.VISIBLE);
                            Deal deal = new Deal(id, customer.getCid());
                            MyDataBase.getInstance(getApplicationContext()).dealDao().insert(deal);
                            Toast.makeText(getApplicationContext(), "Ordered successfully", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Deal_Detail_Page.this, Customer_Page.class);
                            intent.putExtra("customer", customer.getName());
                            startActivity(intent);
                }).setNegativeButton("Cancel", (dialog, which) -> {
                }).show();
            }
            else {
                Intent intent = new Intent(Deal_Detail_Page.this, Login_Page.class);
                startActivity(intent);
            }

        });

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
                    ProgressBar progressBar = findViewById(R.id.deal_detail_progress_bar);
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                    Intent intent = new Intent(Deal_Detail_Page.this, Home_Page.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.item_2) {
                    ProgressBar progressBar = findViewById(R.id.deal_detail_progress_bar);
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                    Intent intent;
                    Customer customer = MyDataBase.getInstance(getApplicationContext()).customerDao().getLogin();
                    Supplier supplier = MyDataBase.getInstance(getApplicationContext()).supplierDao().getLogin();
                    Admin admin = MyDataBase.getInstance(getApplicationContext()).adminDao().getLogin();
                    if (customer != null) {
                        intent = new Intent(Deal_Detail_Page.this, Customer_Page.class);
                        intent.putExtra("customer", customer.getName());
                    } else if (supplier != null) {
                        intent = new Intent(Deal_Detail_Page.this, Supplier_Page.class);
                        intent.putExtra("supplier", supplier.getName());
                    } else if (admin != null) {
                        intent = new Intent(Deal_Detail_Page.this, Admin_Page.class);
                    } else {
                        intent = new Intent(Deal_Detail_Page.this, Login_Page.class);
                    }
                    startActivity(intent);
                    return true;
                } else
                    return false;
            }
        };
        nav.setOnItemSelectedListener(listener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ProgressBar progressBar = findViewById(R.id.deal_detail_progress_bar);
        progressBar.setVisibility(ProgressBar.GONE);
    }

}