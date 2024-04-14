package com.example.assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class Supplier_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_page);

        Supplier supplier = MyDataBase.getInstance(getApplicationContext()).supplierDao()
                .getSupplierByName(getIntent().getStringExtra("supplier"));

        supplier.setStatus(1);
        MyDataBase.getInstance(getApplicationContext()).supplierDao().update(supplier);

        TextView txtLayoutName = findViewById(R.id.supplier_lbl_title);
        TextInputLayout txtLayoutPassword = findViewById(R.id.supplier_txt_password);
        TextInputLayout textLayoutAddress = findViewById(R.id.supplier_txt_address);
        TextInputLayout textLayoutPhone = findViewById(R.id.supplier_txt_phone_number);
        TextInputLayout textLayoutSupplierID = findViewById(R.id.supplier_txt_id);
        Button btn_password = (Button) findViewById(R.id.supplier_btn_password);
        Button btn_address = (Button) findViewById(R.id.supplier_btn_address);
        Button btn_phone_number = (Button) findViewById(R.id.supplier_btn_phone_number);

        txtLayoutName.setText("Hi " + supplier.getName());
        textLayoutSupplierID.getEditText().setText(supplier.getIdNumber());
        txtLayoutPassword.getEditText().setText(supplier.getPassword());
        textLayoutAddress.getEditText().setText(supplier.getAddress());
        textLayoutPhone.getEditText().setText(supplier.getPhoneNum());

        FloatingActionButton btn_return = findViewById(R.id.supplier_fab);
        btn_return.setOnClickListener(v -> {
            finish();
        });

        Button btn_sign_out = (Button) findViewById(R.id.supplier_btn_sign_out);
        btn_sign_out.setOnClickListener(v -> {
            Intent intent = new Intent(Supplier_Page.this, Login_Page.class);
            supplier.setStatus(0);
            MyDataBase.getInstance(getApplicationContext()).supplierDao().update(supplier);
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
                supplier.setPassword(password);
                MyDataBase.getInstance(getApplicationContext()).supplierDao().update(supplier);
            }
        });

        btn_address.setOnClickListener(v -> {
            String address = textLayoutAddress.getEditText().getText().toString().trim();
            textLayoutAddress.setError(null);
            supplier.setAddress(address);
            MyDataBase.getInstance(getApplicationContext()).supplierDao().update(supplier);
        });

        btn_phone_number.setOnClickListener(v -> {
            String phoneNum = textLayoutPhone.getEditText().getText().toString().trim();
            textLayoutPhone.setError(null);
            supplier.setPhoneNum(phoneNum);
            MyDataBase.getInstance(getApplicationContext()).supplierDao().update(supplier);
        });


        List<Product> products = MyDataBase.getInstance(getApplicationContext()).productDao().getProductsBySupplier(supplier.getSid());

        RecyclerView rc = findViewById(R.id.supplier_recyclerView);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(new Supplier_Adapter(products));

        bottomNavigation();

    }

    private void bottomNavigation() {

        NavigationBarView nav = findViewById(R.id.bottom_navigation);
        NavigationBarView.OnItemSelectedListener listener = new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.item_1) {

                    Intent intent = new Intent(Supplier_Page.this, Home_Page.class);
                    startActivity(intent);
                    return true;
                } else
                    return false;
            }
        };
        nav.setOnItemSelectedListener(listener);
    }


}
