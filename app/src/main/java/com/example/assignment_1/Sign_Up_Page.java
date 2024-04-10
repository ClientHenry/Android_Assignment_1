package com.example.assignment_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

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

        regName = findViewById(R.id.sigh_up_customer_txt_username);
        regPassword = findViewById(R.id.sigh_up_customer_txt_password);
        regConfirmedPassword = findViewById(R.id.sigh_up_customer_txt_confirm_password);
        regBtn = (Button)findViewById(R.id.sigh_up_customer_btn_create_account);
        regToLoginBtn = (Button) findViewById(R.id.sigh_up_customer_btn_login);

        regBtn.setOnClickListener(v -> {
           if(validateName()&&validatePassword()){
               Toast.makeText(getApplicationContext(), regName.getEditText().getText().toString().trim(), Toast.LENGTH_SHORT).show();
           }
        });

    }

    private boolean validatePassword() {
        regPassword = findViewById(R.id.sigh_up_customer_txt_password);
        regConfirmedPassword = findViewById(R.id.sigh_up_customer_txt_confirm_password);
    String password = regPassword.getEditText().getText().toString().trim();
    String confirmedPassword = regConfirmedPassword.getEditText().getText().toString().trim();
    if(password.isEmpty() || confirmedPassword.isEmpty()){
        regPassword.setError("Field can't be empty");
        return false;
    }else if(password.length() < 6 || confirmedPassword.length() < 6){
        regPassword.setError("Password must be at least 6 characters long");
        return false;
    }else if(!password.equals(confirmedPassword)){
        regPassword.setError("Passwords do not match");
        return false;
    }else{
        regPassword.setError(null);
        return true;
    }


    }


    private boolean validateName() {
        regName = findViewById(R.id.sigh_up_customer_txt_username);
        String username = regName.getEditText().getText().toString().trim();
        if(MyDataBase.getInstance(getApplicationContext()).customerDao().getCustomerByName(username) != null){
            regName.setError("Username already exists");
            return false;
        }
        else if(username.isEmpty()){
            regName.setError("Field can't be empty");
            return false;
        }else if(username.length() < 4){
            regName.setError("Username must be at least 4 characters long");
            return false;
        }
        else{
            regName.setError(null);
            return true;
        }

    }
}