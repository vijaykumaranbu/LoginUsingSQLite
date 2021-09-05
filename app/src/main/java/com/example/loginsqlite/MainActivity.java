package com.example.loginsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginsqlite.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UserListener{

    private ActivityMainBinding binding;
    private DataBaseHelperClass db;
    private UserAdapter adapter;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = new DataBaseHelperClass(getApplicationContext());
        users = new ArrayList<>();
    }

    @Override
    protected void onResume() {
        super.onResume();
        users = db.getAllUsers();
        adapter = new UserAdapter(users, this);
        binding.userRecyclerview.setAdapter(adapter);
    }

    @Override
    public void onClickedUser(User user) {
        Intent intent = new Intent(MainActivity.this,EditUserActivity.class);
        intent.putExtra(User.KEY_USER_ID,user.getUserId());
        intent.putExtra(User.KEY_USER_NAME,user.getUserName());
        intent.putExtra(User.KEY_EMAIL,user.getEmail());
        intent.putExtra(User.KEY_PASSWORD,user.getPassword());
        startActivity(intent);
    }

    @Override
    public void deleteUser(int userId,int position) {
        if(db.deleteUser(userId)){
            users.remove(position);
            adapter.notifyItemRemoved(position);
            binding.userRecyclerview.smoothScrollToPosition(position);
            showToast("deleted");
        }
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message,Toast.LENGTH_SHORT).show();
    }
}