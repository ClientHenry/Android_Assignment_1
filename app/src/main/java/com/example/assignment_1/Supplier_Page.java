package com.example.assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
        Button btn_password_confirmed = (Button) findViewById(R.id.supplier_btn_password_confirmed);
        Button btn_address_confirmed = (Button) findViewById(R.id.supplier_btn_address_confirmed);
        Button btn_phone_number_confirmed = (Button) findViewById(R.id.supplier_btn_phone_number_confirmed);

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
            txtLayoutPassword.setEnabled(true);
            btn_password_confirmed.setEnabled(true);
            btn_password_confirmed.setVisibility(Button.VISIBLE);
            btn_password.setVisibility(Button.GONE);
            btn_password.setEnabled(false);
            btn_address.setEnabled(false);
            btn_phone_number.setEnabled(false);

        });

        btn_password_confirmed.setOnClickListener(v -> {

            txtLayoutPassword.setEnabled(false);
            btn_password_confirmed.setEnabled(false);
            btn_password_confirmed.setVisibility(Button.GONE);
            btn_password.setVisibility(Button.VISIBLE);
            btn_password.setEnabled(true);

            String password = txtLayoutPassword.getEditText().getText().toString().trim();
            if (password.isEmpty()) {
                txtLayoutPassword.setError("Field can't be empty");
            } else if (password.length() < 6) {
                txtLayoutPassword.setError("Password must be at least 6 characters long");
            } else {
                txtLayoutPassword.setError(null);
                supplier.setPassword(password);
                MyDataBase.getInstance(getApplicationContext()).supplierDao().update(supplier);
                txtLayoutPassword.setEnabled(false);
                btn_password_confirmed.setEnabled(false);
                btn_password_confirmed.setVisibility(Button.GONE);
                btn_password.setVisibility(Button.VISIBLE);
                btn_password.setEnabled(true);
                btn_address.setEnabled(true);
                btn_phone_number.setEnabled(true);
                Toast.makeText(getApplicationContext(), "Password updated", Toast.LENGTH_LONG).show();
            }

        });

        btn_address.setOnClickListener(v -> {
            textLayoutAddress.setEnabled(true);
            btn_address_confirmed.setEnabled(true);
            btn_address_confirmed.setVisibility(Button.VISIBLE);
            btn_address.setVisibility(Button.GONE);
            btn_address.setEnabled(false);
            btn_password.setEnabled(false);
            btn_phone_number.setEnabled(false);
        });

        btn_address_confirmed.setOnClickListener(v -> {
            textLayoutAddress.setEnabled(false);
            btn_address_confirmed.setEnabled(false);
            btn_address_confirmed.setVisibility(Button.GONE);
            btn_address.setVisibility(Button.VISIBLE);
            btn_address.setEnabled(true);
            btn_password.setEnabled(true);
            btn_phone_number.setEnabled(true);

            String address = textLayoutAddress.getEditText().getText().toString().trim();
            textLayoutAddress.setError(null);
            supplier.setAddress(address);
            MyDataBase.getInstance(getApplicationContext()).supplierDao().update(supplier);
            Toast.makeText(getApplicationContext(), "Address updated", Toast.LENGTH_LONG).show();
        });

        btn_phone_number.setOnClickListener(v -> {
            textLayoutPhone.setEnabled(true);
            btn_phone_number_confirmed.setEnabled(true);
            btn_phone_number_confirmed.setVisibility(Button.VISIBLE);
            btn_phone_number.setVisibility(Button.GONE);
            btn_phone_number.setEnabled(false);
            btn_address.setEnabled(false);
            btn_password.setEnabled(false);
        });

        btn_phone_number_confirmed.setOnClickListener(v -> {
            textLayoutPhone.setEnabled(false);
            btn_phone_number_confirmed.setEnabled(false);
            btn_phone_number_confirmed.setVisibility(Button.GONE);
            btn_phone_number.setVisibility(Button.VISIBLE);
            btn_phone_number.setEnabled(true);
            btn_address.setEnabled(true);
            btn_password.setEnabled(true);

            String phoneNum = textLayoutPhone.getEditText().getText().toString().trim();
            textLayoutPhone.setError(null);
            supplier.setPhoneNum(phoneNum);
            MyDataBase.getInstance(getApplicationContext()).supplierDao().update(supplier);
            Toast.makeText(getApplicationContext(), "Phone number updated", Toast.LENGTH_LONG).show();
        });


        List<Product> products = MyDataBase.getInstance(getApplicationContext()).productDao().getProductsBySupplier(supplier.getSid());

        RecyclerView rc = findViewById(R.id.supplier_recyclerView);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(new Supplier_Adapter(products));

        bottomNavigation();

    }

    private void bottomNavigation() {

        NavigationBarView nav = findViewById(R.id.bottom_navigation);
        nav.setSelectedItemId(R.id.item_2);
        NavigationBarView.OnItemSelectedListener listener = new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.item_1) {
                    ProgressBar progressBar = findViewById(R.id.supplier_progress_bar);
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                    Intent intent = new Intent(Supplier_Page.this, Home_Page.class);
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
        ProgressBar progressBar = findViewById(R.id.supplier_progress_bar);
        progressBar.setVisibility(ProgressBar.GONE);

    }


}
