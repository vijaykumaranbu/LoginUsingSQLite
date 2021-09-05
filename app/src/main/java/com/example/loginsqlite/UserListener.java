package com.example.loginsqlite;

public interface UserListener {
    void onClickedUser(User user);
    void deleteUser(int userId,int position);
}
