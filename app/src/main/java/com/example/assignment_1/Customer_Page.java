package com.example.assignment_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
        Button btn_password_confirmed = (Button) findViewById(R.id.customer_btn_password_confirmed);
        Button btn_address_confirmed = (Button) findViewById(R.id.customer_btn_address_confirmed);
        Button btn_phone_number_confirmed = (Button) findViewById(R.id.customer_btn_phone_number_confirmed);

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

            txtLayoutPassword.setEnabled(true);
            btn_password_confirmed.setEnabled(true);
            btn_password_confirmed.setVisibility(Button.VISIBLE);
            btn_password.setEnabled(false);
            btn_password.setVisibility(Button.GONE);
            btn_address.setEnabled(false);
            btn_phone_number.setEnabled(false);
        });

        btn_password_confirmed.setOnClickListener(v -> {

            String password = txtLayoutPassword.getEditText().getText().toString().trim();
            if (password.isEmpty()) {
                txtLayoutPassword.setError("Field can't be empty");
            } else if (password.length() < 6) {
                txtLayoutPassword.setError("Password must be at least 6 characters long");
            } else {
                txtLayoutPassword.setError(null);
                customer.setPassword(password);
                MyDataBase.getInstance(getApplicationContext()).customerDao().update(customer);
                txtLayoutPassword.setEnabled(false);
                btn_password_confirmed.setEnabled(false);
                btn_password_confirmed.setVisibility(Button.GONE);
                btn_password.setEnabled(true);
                btn_password.setVisibility(Button.VISIBLE);
                btn_address.setEnabled(true);
                btn_phone_number.setEnabled(true);
               Toast.makeText(getApplicationContext(), "Password updated", Toast.LENGTH_LONG).show();
            }
        });

        btn_address.setOnClickListener(v -> {

                textLayoutAddress.setEnabled(true);
                btn_address_confirmed.setEnabled(true);
                btn_address_confirmed.setVisibility(Button.VISIBLE);
                btn_address.setEnabled(false);
                btn_address.setVisibility(Button.GONE);
                btn_phone_number.setEnabled(false);
                btn_password.setEnabled(false);

        });

        btn_address_confirmed.setOnClickListener(v -> {

            String address = textLayoutAddress.getEditText().getText().toString().trim();

            textLayoutAddress.setError(null);
            customer.setAddress(address);
            MyDataBase.getInstance(getApplicationContext()).customerDao().update(customer);

            textLayoutAddress.setEnabled(false);
            btn_address_confirmed.setEnabled(false);
            btn_address_confirmed.setVisibility(Button.GONE);
            btn_address.setEnabled(true);
            btn_address.setVisibility(Button.VISIBLE);
            btn_phone_number.setEnabled(true);
            btn_password.setEnabled(true);
            Toast.makeText(getApplicationContext(), "Address updated", Toast.LENGTH_LONG).show();

        });

        btn_phone_number.setOnClickListener(v -> {

            textLayoutPhone.setEnabled(true);
            btn_phone_number_confirmed.setEnabled(true);
            btn_phone_number_confirmed.setVisibility(Button.VISIBLE);
            btn_phone_number.setEnabled(false);
            btn_phone_number.setVisibility(Button.GONE);
            btn_address.setEnabled(false);
            btn_password.setEnabled(false);
        });

        btn_phone_number_confirmed.setOnClickListener(v -> {

            String phone = textLayoutPhone.getEditText().getText().toString().trim();
            textLayoutPhone.setError(null);
            customer.setPhoneNum(phone);
            MyDataBase.getInstance(getApplicationContext()).customerDao().update(customer);

            textLayoutPhone.setEnabled(false);
            btn_phone_number_confirmed.setEnabled(false);
            btn_phone_number_confirmed.setVisibility(Button.GONE);
            btn_phone_number.setEnabled(true);
            btn_phone_number.setVisibility(Button.VISIBLE);
            btn_address.setEnabled(true);
            btn_password.setEnabled(true);
            Toast.makeText(getApplicationContext(), "Phone number updated", Toast.LENGTH_LONG).show();

        });


        bottomNavigation();
    }

    private void bottomNavigation() {

        NavigationBarView nav = findViewById(R.id.bottom_navigation);
        nav.setSelectedItemId(R.id.item_2);
        NavigationBarView.OnItemSelectedListener listener = new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.item_1) {

                    ProgressBar progressBar = findViewById(R.id.customer_progress_bar);
                    progressBar.setVisibility(ProgressBar.VISIBLE);

                    Intent intent = new Intent(Customer_Page.this, Home_Page.class);
                    startActivity(intent);
                    return true;
                } else
                    return false;
            }
        };
        nav.setOnItemSelectedListener(listener);
    }

    @Override
    protected void onPause(){
        super.onPause();
        ProgressBar progressBar = findViewById(R.id.customer_progress_bar);
        progressBar.setVisibility(ProgressBar.GONE);
    }
}