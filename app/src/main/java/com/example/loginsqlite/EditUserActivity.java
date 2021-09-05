package com.example.loginsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.loginsqlite.databinding.ActivityEditUserBinding;

public class EditUserActivity extends AppCompatActivity {

    private ActivityEditUserBinding binding;
    private DataBaseHelperClass db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = new DataBaseHelperClass(getApplicationContext());
        loadUserDetails();
        binding.btnUpdate.setOnClickListener(view -> {
            updateUser();
        });
    }

    private void loadUserDetails(){
        binding.inputUserName.setText(getIntent().getStringExtra(User.KEY_USER_NAME));
        binding.inputEmail.setText(getIntent().getStringExtra(User.KEY_EMAIL));
        binding.inputPassword.setText(getIntent().getStringExtra(User.KEY_PASSWORD));
        binding.inputConfirmPassword.setText(getIntent().getStringExtra(User.KEY_PASSWORD));
    }

    private void updateUser() {
        if (binding.inputUserName.getText().toString().trim().isEmpty())
            showToast("Enter User name");
        else if (binding.inputEmail.getText().toString().trim().isEmpty())
            showToast("Enter Email");
        else if (binding.inputPassword.getText().toString().trim().isEmpty())
            showToast("Enter Password");
        else if (binding.inputConfirmPassword.getText().toString().trim().isEmpty())
            showToast("Enter Confirm password");
        else if (!binding.inputPassword.getText().toString().trim()
                .equals(binding.inputConfirmPassword.getText().toString().trim()))
            showToast("Password should be same!");
        else {
            String userName = binding.inputUserName.getText().toString().trim();
            String email = binding.inputEmail.getText().toString().trim();
            String password = binding.inputPassword.getText().toString().trim();
            User user = new User(getIntent().getIntExtra(User.KEY_USER_ID,-1),
                    userName,email,password);
            if(db.updateUser(user)){
                onBackPressed();
                showToast("updated");
            }
            else
                showToast("not updated");
        }
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
}