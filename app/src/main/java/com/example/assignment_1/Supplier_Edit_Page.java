package com.example.assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputLayout;

public class Supplier_Edit_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_edit_page);
        TextInputLayout supplier_edit_txt_id = findViewById(R.id.supplier_edit_txt_id);
        TextInputLayout supplier_edit_txt_name = findViewById(R.id.supplier_edit_txt_name);
        TextInputLayout supplier_edit_txt_date = findViewById(R.id.supplier_edit_txt_date);
        TextInputLayout supplier_edit_txt_price = findViewById(R.id.supplier_edit_txt_price);
        TextInputLayout supplier_edit_txt_discount = findViewById(R.id.supplier_edit_txt_discount);
        TextInputLayout supplier_edit_txt_category = findViewById(R.id.supplier_edit_txt_category);
        EditText supplier_edit_txt_description = findViewById(R.id.supplier_edit_txt_description);
        ImageView supplier_edit_img_1 = findViewById(R.id.supplier_edit_imageView_1);
        ImageView supplier_edit_img_2 = findViewById(R.id.supplier_edit_imageView_2);
        ImageView supplier_edit_img_3 = findViewById(R.id.supplier_edit_imageView_3);
        ImageView supplier_edit_img_4 = findViewById(R.id.supplier_edit_imageView_4);
        Button supplier_edit_btn_save = (Button) findViewById(R.id.supplier_edit_btn_save);
        Button supplier_edit_btn_cancel = (Button) findViewById(R.id.supplier_edit_btn_cancel);
        FloatingActionButton btnReturn = findViewById(R.id.supplier_edit_btn_return);

        String id = getIntent().getStringExtra("productID");
        String name = getIntent().getStringExtra("product");
        String date = getIntent().getStringExtra("productDate");
        int price = getIntent().getIntExtra("productPrice",0);
        int discount = getIntent().getIntExtra("productDiscount",0);
        String category = getIntent().getStringExtra("productCategory");
        String description = getIntent().getStringExtra("productDescription");
        int image1 = getIntent().getIntExtra("productImage1", 0);
        int image2 = getIntent().getIntExtra("productImage2", 0);
        int image3 = getIntent().getIntExtra("productImage3", 0);
        int image4 = getIntent().getIntExtra("productImage4", 0);

        supplier_edit_txt_id.getEditText().setText(id);
        supplier_edit_txt_name.getEditText().setText(name);
        supplier_edit_txt_category.getEditText().setText(category);
        supplier_edit_txt_date.getEditText().setText(date);
        supplier_edit_txt_price.getEditText().setText(Integer.toString(price));
            supplier_edit_txt_discount.getEditText().setText(Integer.toString(discount));
        supplier_edit_txt_description.setText(description);
        supplier_edit_img_1.setImageResource(image1);
        supplier_edit_img_2.setImageResource(image2);
        supplier_edit_img_3.setImageResource(image3);
        supplier_edit_img_4.setImageResource(image4);

        supplier_edit_btn_save.setOnClickListener(v -> {

            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
            builder.setTitle("Save Change").setMessage("Are you sure you want to save the change of product?").
                    setPositiveButton("Yes", (dialog, which) -> {
                        Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }).setNegativeButton("No", (dialog, which) -> {
                        dialog.dismiss();
                    }).show();
        });

        supplier_edit_btn_cancel.setOnClickListener(v -> {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
            builder.setTitle("Cancel Change").setMessage("Are you sure you want to cancel the change of product?").
                    setPositiveButton("Yes", (dialog, which) -> {
                      finish();
                    }).setNegativeButton("No", (dialog, which) -> {
                        dialog.dismiss();
                    }).show();
        });

        btnReturn.setOnClickListener(v -> {
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
                Intent intent = new Intent(Supplier_Edit_Page.this, Home_Page.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.item_2) {
                Intent intent = new Intent(Supplier_Edit_Page.this, Supplier_Page.class);
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
