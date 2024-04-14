package com.example.assignment_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputLayout;

public class Login_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        TextInputLayout txtLayoutUserName = findViewById(R.id.login_txt_username);
        TextInputLayout txtLayoutPassword = findViewById(R.id.login_txt_password);
        EditText txtUserName = txtLayoutUserName.getEditText();
        EditText txtPassword = txtLayoutPassword.getEditText();
        Button btnLogin = (Button) findViewById(R.id.login_btn_login);
        Button btnSignUp = (Button) findViewById(R.id.login_btn_signup);
        FloatingActionButton btnReturn = findViewById(R.id.login_btn_return);

        btnLogin.setOnClickListener(v -> {
            String userName = txtUserName.getText().toString();
            String password = txtPassword.getText().toString();

            Customer customer = MyDataBase.getInstance(getApplicationContext()).customerDao().getCustomerByNameAndPassword(userName, password);
            Supplier supplier = MyDataBase.getInstance(getApplicationContext()).supplierDao().getSupplierByNameAndPassword(userName, password);
            Admin admin = MyDataBase.getInstance(getApplicationContext()).adminDao().getAdminByUsernameAndPassword(userName, password);
            Intent intent;

            if ((MyDataBase.getInstance(getApplicationContext()).customerDao().getLogin() != null)
                    || (MyDataBase.getInstance(getApplicationContext()).supplierDao().getLogin() != null)
                    || (MyDataBase.getInstance(getApplicationContext()).adminDao().getLogin() != null)) {
                Toast.makeText(Login_Page.this, "Please logout first", Toast.LENGTH_LONG).show();
            } else {

                if (customer != null) {
                    intent = new Intent(Login_Page.this, Customer_Page.class);
                    intent.putExtra("customer", customer.getName());
                    Toast.makeText(Login_Page.this, "Login in successfully", Toast.LENGTH_LONG).show();
                    startActivity(intent);

                } else if (supplier != null) {
                    intent = new Intent(Login_Page.this, Supplier_Page.class);
                    intent.putExtra("supplier", supplier.getName());
                    Toast.makeText(Login_Page.this, "Login in successfully", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                } else if (admin != null) {
                    intent = new Intent(Login_Page.this, Admin_Page.class);
                    Toast.makeText(Login_Page.this, "Login in successfully", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                } else {
                    txtLayoutUserName.setError("Invalid Username or Password");
                    txtLayoutPassword.setError("Invalid Username or Password");
                }
            }
        });

        btnReturn.setOnClickListener(v -> {
            finish();
        });

        btnSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(Login_Page.this, SignUpOption.class);
            startActivity(intent);
        });

        bottomNavigation();
    }

    private void bottomNavigation() {

        NavigationBarView nav = findViewById(R.id.bottom_navigation);
        NavigationBarView.OnItemSelectedListener listener = new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.item_1) {

                    Intent intent = new Intent(Login_Page.this, Home_Page.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.item_2) {
                    Intent intent;
                    Customer customer = MyDataBase.getInstance(getApplicationContext()).customerDao().getLogin();
                    Supplier supplier = MyDataBase.getInstance(getApplicationContext()).supplierDao().getLogin();
                    Admin admin = MyDataBase.getInstance(getApplicationContext()).adminDao().getLogin();
                    if (customer != null) {
                        intent = new Intent(Login_Page.this, Customer_Page.class);
                        intent.putExtra("customer", customer.getName());
                        startActivity(intent);
                        return true;
                    } else if (supplier != null) {
                        intent = new Intent(Login_Page.this, Supplier_Page.class);
                        intent.putExtra("supplier", supplier.getName());
                        startActivity(intent);
                        return true;
                    } else if (admin != null) {
                        intent = new Intent(Login_Page.this, Admin_Page.class);
                        startActivity(intent);
                        return true;

                    } else {
                        return false;
                    }
                } else
                    return false;
            }
        };
        nav.setOnItemSelectedListener(listener);
    }
}