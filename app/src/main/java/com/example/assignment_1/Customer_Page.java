package com.example.assignment_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
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

        customer.setStatus(1);
        MyDataBase.getInstance(getApplicationContext()).customerDao().update(customer);

        int customerID = customer.getCid();
        TextView txtLayoutName = findViewById(R.id.customer_lbl_title);
        TextInputLayout txtLayoutPassword = findViewById(R.id.customer_txt_password);
        TextInputLayout textLayoutAddress = findViewById(R.id.customer_txt_address);
        TextInputLayout textLayoutPhone = findViewById(R.id.customer_txt_phone_number);
        TextInputLayout textLayoutCustomerID = findViewById(R.id.customer_txt_id);
        FloatingActionButton btn_return = findViewById(R.id.customer_btn_return);
        Button btn_sign_out = (Button) findViewById(R.id.customer_btn_sign_out);
        Button btn_password = (Button) findViewById(R.id.customer_btn_password);
        Button btn_address = (Button) findViewById(R.id.customer_btn_address);
        Button btn_phone_number = (Button) findViewById(R.id.customer_btn_phone_number);

        txtLayoutName.setText("Hi " + customer.getName());
        textLayoutCustomerID.getEditText().setText(customer.getIdNumber());
        txtLayoutPassword.getEditText().setText(customer.getPassword());
        textLayoutAddress.getEditText().setText(customer.getAddress());
        textLayoutPhone.getEditText().setText(customer.getPhoneNum());

        List<Deal> deals = MyDataBase.getInstance(getApplicationContext()).dealDao().getDealsByCustomerId(customerID);

        RecyclerView rc = findViewById(R.id.customer_recyclerView);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(new Customer_Adapter(deals));

        btn_return.setOnClickListener(v -> {
            finish();
        });

        btn_sign_out.setOnClickListener(v -> {
            Intent intent = new Intent(Customer_Page.this, Login_Page.class);
            customer.setStatus(0);
            MyDataBase.getInstance(getApplicationContext()).customerDao().update(customer);
            startActivity(intent);
        });

        btn_password.setOnClickListener(v -> {

            String password = txtLayoutPassword.getEditText().getText().toString().trim();
            if (password.isEmpty()) {
                txtLayoutPassword.setError("Field can't be empty");
            } else if (password.length() < 6) {
                txtLayoutPassword.setError("Password must be at least 6 characters long");
            } else {
                txtLayoutPassword.setError(null);
                customer.setPassword(password);
                MyDataBase.getInstance(getApplicationContext()).customerDao().update(customer);
            }

        });

        btn_address.setOnClickListener(v -> {

            String address = textLayoutAddress.getEditText().getText().toString().trim();

            textLayoutAddress.setError(null);
            customer.setAddress(address);
            MyDataBase.getInstance(getApplicationContext()).customerDao().update(customer);
        });

        btn_phone_number.setOnClickListener(v -> {

            String phone = textLayoutPhone.getEditText().getText().toString().trim();
            textLayoutPhone.setError(null);
            customer.setPhoneNum(phone);
            MyDataBase.getInstance(getApplicationContext()).customerDao().update(customer);
        });

        bottomNavigation();
    }

    private void bottomNavigation() {

        NavigationBarView nav = findViewById(R.id.bottom_navigation);
        NavigationBarView.OnItemSelectedListener listener = new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.item_1) {

                    Intent intent = new Intent(Customer_Page.this, Home_Page.class);
                    startActivity(intent);
                    return true;
                } else
                    return false;
            }
        };
        nav.setOnItemSelectedListener(listener);
    }
}