package com.example.loginsqlite;

import android.os.Bundle;
import android.provider.ContactsContract;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginsqlite.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DataBaseHelperClass db = new DataBaseHelperClass(getApplicationContext());
        UserAdapter adapter = new UserAdapter(db.getAllUsers());
        binding.userRecyclerview.setAdapter(adapter);
    }
}