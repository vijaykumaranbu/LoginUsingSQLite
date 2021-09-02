package com.example.loginsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginsqlite.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private DataBaseHelperClass db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = new DataBaseHelperClass(getApplicationContext());
        binding.btnLogin.setOnClickListener(view -> checkUser());
        binding.textRegister.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void checkUser(){
        if(binding.inputUserName.getText().toString().trim().isEmpty())
            showToast("Enter User name");
        else if(binding.inputPassword.getText().toString().trim().isEmpty())
            showToast("Enter Password");
        else {
            if(db.checkUser(binding.inputUserName.getText().toString().trim(),
                    binding.inputPassword.getText().toString().trim())) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            else
                showToast("Invalid User");
        }
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
}