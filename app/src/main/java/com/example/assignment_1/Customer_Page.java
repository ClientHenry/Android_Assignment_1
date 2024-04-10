package com.example.assignment_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class Customer_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_page);

        Customer customer = MyDataBase.getInstance(getApplicationContext()).customerDao()
                .getCustomerByName(getIntent().getStringExtra("customer"));
        int customerID = customer.getCid();
        TextView txtLayoutName = findViewById(R.id.customer_lbl_title);
        TextInputLayout txtLayoutPassword = findViewById(R.id.customer_txt_password);
        TextInputLayout textLayoutAddress = findViewById(R.id.customer_txt_address);
        TextInputLayout textLayoutPhone = findViewById(R.id.customer_txt_phone_number);
        txtLayoutName.setText("Hi " + customer.getName());
        txtLayoutPassword.getEditText().setText(customer.getPassword());
        textLayoutAddress.getEditText().setText(customer.getAddress());
        textLayoutPhone.getEditText().setText(customer.getPhoneNum());

        List<Deal> deals = MyDataBase.getInstance(getApplicationContext()).dealDao().getDealsByCustomerId(customerID);

        RecyclerView rc = findViewById(R.id.customer_recyclerView);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(new Customer_Adapter(deals));





    }
}