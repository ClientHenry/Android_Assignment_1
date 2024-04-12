package com.example.assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class SignUpOption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_option_page);

        FloatingActionButton btn_return = findViewById(R.id.sign_up_option_btn_return);
        btn_return.setOnClickListener(v -> {
           finish();
        });

        bottomNavigation();
    }

    private void bottomNavigation() {

        NavigationBarView nav = findViewById(R.id.bottom_navigation);
        NavigationBarView.OnItemSelectedListener listener = new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.item_1) {

                    Intent intent = new Intent(SignUpOption.this, Home_Page.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.item_2) {
                    Intent intent;
                    Customer customer = MyDataBase.getInstance(getApplicationContext()).customerDao().getLogin();
                    Supplier supplier = MyDataBase.getInstance(getApplicationContext()).supplierDao().getLogin();
                    Admin admin = MyDataBase.getInstance(getApplicationContext()).adminDao().getLogin();
                    if (customer != null) {
                        intent = new Intent(SignUpOption.this, Customer_Page.class);
                        intent.putExtra("customer", customer.getName());
                    } else if (supplier != null) {
                        intent = new Intent(SignUpOption.this, Supplier_Page.class);
                        intent.putExtra("supplier", supplier.getName());
                    } else if (admin != null) {
                        intent = new Intent(SignUpOption.this, Admin_Page.class);
                    } else {
                        intent = new Intent(SignUpOption.this, Login_Page.class);
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