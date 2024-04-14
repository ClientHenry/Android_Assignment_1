package com.example.assignment_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputLayout;

public class Sign_Up_Page extends AppCompatActivity {

    TextInputLayout regName;
    TextInputLayout regPassword;
    TextInputLayout regConfirmedPassword;
    Button regBtn;
    Button regToLoginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        String type = getIntent().getStringExtra("type");

        regName = findViewById(R.id.sigh_up_customer_txt_username);
        regPassword = findViewById(R.id.sigh_up_customer_txt_password);
        regConfirmedPassword = findViewById(R.id.sigh_up_customer_txt_confirm_password);
        regBtn = (Button) findViewById(R.id.sigh_up_customer_btn_create_account);
        regToLoginBtn = (Button) findViewById(R.id.sigh_up_customer_btn_login);
        FloatingActionButton btn_return = findViewById(R.id.sigh_up_customer_btn_return);

        btn_return.setOnClickListener(v -> {
            finish();
        });

        regBtn.setOnClickListener(v -> {

            if (validateName() && validatePassword()) {
                if (type.equals("customer")) {
                    Customer customer = new Customer(regName.getEditText().getText().toString().trim(),
                            regPassword.getEditText().getText().toString().trim(), "", "");
                    MyDataBase.getInstance(getApplicationContext()).customerDao().insert(customer);
                    Toast.makeText(Sign_Up_Page.this, "Customer created successfully", Toast.LENGTH_SHORT).show();


                } else {
                    Supplier supplier = new Supplier(regName.getEditText().getText().toString().trim(),
                            regPassword.getEditText().getText().toString().trim(), "", "");
                    MyDataBase.getInstance(getApplicationContext()).supplierDao().insert(supplier);
                    Toast.makeText(Sign_Up_Page.this, "Supplier created successfully", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(Sign_Up_Page.this, Login_Page.class);
                startActivity(intent);

            } else {
                Toast.makeText(Sign_Up_Page.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigation();

    }

    private boolean validatePassword() {
        regPassword = findViewById(R.id.sigh_up_customer_txt_password);
        regConfirmedPassword = findViewById(R.id.sigh_up_customer_txt_confirm_password);
        String password = regPassword.getEditText().getText().toString().trim();
        String confirmedPassword = regConfirmedPassword.getEditText().getText().toString().trim();
        if (password.isEmpty() || confirmedPassword.isEmpty()) {
            regPassword.setError("Field can't be empty");
            return false;
        } else if (password.length() < 6 || confirmedPassword.length() < 6) {
            regPassword.setError("Password must be at least 6 characters long");
            return false;
        } else if (!password.equals(confirmedPassword)) {
            regPassword.setError("Passwords do not match");
            return false;
        } else {
            regPassword.setError(null);
            return true;
        }
    }


    private boolean validateName() {
        regName = findViewById(R.id.sigh_up_customer_txt_username);
        String username = regName.getEditText().getText().toString().trim();
        if ((MyDataBase.getInstance(getApplicationContext()).customerDao().getCustomerByName(username) != null)
                || (MyDataBase.getInstance(getApplicationContext()).supplierDao().getSupplierByName(username) != null)
                || (MyDataBase.getInstance(getApplicationContext()).adminDao().getAdminByUsername(username) != null)) {
            regName.setError("Username already exists");
            return false;
        } else if (username.isEmpty()) {
            regName.setError("Field can't be empty");
            return false;
        } else if (username.length() < 4) {
            regName.setError("Username must be at least 4 characters long");
            return false;
        } else {
            regName.setError(null);
            return true;
        }

    }

    private void bottomNavigation() {

        NavigationBarView nav = findViewById(R.id.bottom_navigation);
        NavigationBarView.OnItemSelectedListener listener = new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.item_1) {

                    Intent intent = new Intent(Sign_Up_Page.this, Home_Page.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.item_2) {
                    Intent intent;
                    Customer customer = MyDataBase.getInstance(getApplicationContext()).customerDao().getLogin();
                    Supplier supplier = MyDataBase.getInstance(getApplicationContext()).supplierDao().getLogin();
                    Admin admin = MyDataBase.getInstance(getApplicationContext()).adminDao().getLogin();
                    if (customer != null) {
                        intent = new Intent(Sign_Up_Page.this, Customer_Page.class);
                        intent.putExtra("customer", customer.getName());
                    } else if (supplier != null) {
                        intent = new Intent(Sign_Up_Page.this, Supplier_Page.class);
                        intent.putExtra("supplier", supplier.getName());
                    } else if (admin != null) {
                        intent = new Intent(Sign_Up_Page.this, Admin_Page.class);
                    } else {
                        intent = new Intent(Sign_Up_Page.this, Login_Page.class);
                    }
                    startActivity(intent);
                    return true;
                } else
                    return false;
            }
        };
        nav.setOnItemSelectedListener(listener);
    }
}