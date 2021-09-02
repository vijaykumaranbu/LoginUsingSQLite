package com.example.loginsqlite;

public class User {

    private String userName;
    private String email;
    private String password;

    User(String userName,String email,String password){
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public static final String TABLE_NAME = "UserDetails";
    public static final String KEY_USER_ID = "UserId";
    public static final String KEY_USER_NAME = "UserName";
    public static final String KEY_EMAIL = "Email";
    public static final String KEY_PASSWORD = "Password";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            "(" + KEY_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_USER_NAME + " TEXT," +
            KEY_EMAIL + " TEXT," +
            KEY_PASSWORD + " TEXT" +
            ")";

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
