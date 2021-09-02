package com.example.loginsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginsqlite.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private DataBaseHelperClass db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = new DataBaseHelperClass(getApplicationContext());
        binding.btnRegister.setOnClickListener(view -> registerUser());
        binding.textLogin.setOnClickListener(view -> onBackPressed());
    }

    private void registerUser(){
        if(binding.inputUserName.getText().toString().trim().isEmpty())
            showToast("Enter User name");
        else if(binding.inputEmail.getText().toString().trim().isEmpty())
            showToast("Enter Email");
        else if(binding.inputPassword.getText().toString().trim().isEmpty())
            showToast("Enter Password");
        else if(binding.inputConfirmPassword.getText().toString().trim().isEmpty())
            showToast("Enter Confirm password");
        else if(!binding.inputPassword.getText().toString().trim()
                .equals(binding.inputConfirmPassword.getText().toString().trim()))
            showToast("Password should be same!");
        else {
            String userName = binding.inputUserName.getText().toString().trim();
            String email = binding.inputEmail.getText().toString().trim();
            String password = binding.inputPassword.getText().toString().trim();
            long id = db.insertUser(new User(userName,email,password));
            if(id != -1){
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                showToast("User Registered");
            }
        }
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
}