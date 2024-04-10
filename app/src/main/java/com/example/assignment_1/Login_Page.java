package com.example.assignment_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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

        btnLogin.setOnClickListener(v -> {
            String userName = txtUserName.getText().toString();
            String password = txtPassword.getText().toString();

            Customer customer = MyDataBase.getInstance(getApplicationContext()).customerDao().getCustomerByNameAndPassword(userName, password);
            Supplier supplier = MyDataBase.getInstance(getApplicationContext()).supplierDao().getSupplierByNameAndPassword(userName, password);
            Intent intent;

            if (customer != null) {
                intent = new Intent(Login_Page.this, Customer_Page.class);
                intent.putExtra("customer", customer.getName());
                startActivity(intent);

            } else if (supplier != null){
                intent = new Intent(Login_Page.this, Supplier.class);
                startActivity(intent);
            }else{
                txtLayoutUserName.setError("Invalid Username or Password");
                txtLayoutPassword.setError("Invalid Username or Password");
            }
        });










    }
}