package com.example.assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class Supplier_Add_Page extends AppCompatActivity {

    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_add_page);
        bottomNavigation();

        FloatingActionButton btnReturn = findViewById(R.id.supplier_add_btn_return);
        btnReturn.setOnClickListener(v -> {
           finish();
        });

        Button supplier_add_btn_save = (Button) findViewById(R.id.supplier_add_btn_save);
        supplier_add_btn_save.setOnClickListener(v -> {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
            builder.setTitle("Save Change").setMessage("Are you sure you want to save the change of product?").
                    setPositiveButton("Yes", (dialog, which) -> {
                        Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }).setNegativeButton("No", (dialog, which) -> {
                        dialog.dismiss();
                    }).show();
        });

        Button supplier_add_btn_cancel = (Button) findViewById(R.id.supplier_add_btn_cancel);
        supplier_add_btn_cancel.setOnClickListener(v -> {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
            builder.setTitle("Cancel").setMessage("Are you sure you want to cancel the change of product?").
                    setPositiveButton("Yes", (dialog, which) -> {
                        finish();
                    }).setNegativeButton("No", (dialog, which) -> {
                        dialog.dismiss();
                    }).show();
        });


    }

    private void bottomNavigation() {
        NavigationBarView nav = findViewById(R.id.bottom_navigation);
        NavigationBarView.OnItemSelectedListener listener = new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.item_1) {
                    Intent intent = new Intent(Supplier_Add_Page.this, Home_Page.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.item_2) {
                    Intent intent = new Intent(Supplier_Add_Page.this, Supplier_Page.class);
                    Supplier supplier = MyDataBase.getInstance(getApplicationContext()).supplierDao().getLogin();
                    intent.putExtra("supplier", supplier.getName());
                    startActivity(intent);
                    return true;
                } else
                    return false;
            }
        };
        nav.setOnItemSelectedListener(listener);
    }
}
