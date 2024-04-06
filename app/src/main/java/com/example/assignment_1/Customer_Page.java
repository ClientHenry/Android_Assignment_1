package com.example.assignment_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class Customer_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_page);

        RecyclerView rc = findViewById(R.id.customer_recyclerView);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(new Customer_Adapter());



    }
}