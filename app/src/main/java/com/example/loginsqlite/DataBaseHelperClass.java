package com.example.loginsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelperClass extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Login.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TAG = "Login_db";

    public DataBaseHelperClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(User.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long insertUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User.KEY_USER_NAME,user.getUserName());
        values.put(User.KEY_EMAIL,user.getEmail());
        values.put(User.KEY_PASSWORD,user.getPassword());
        long id = db.insert(User.TABLE_NAME,null,values);
        db.close();
        return id;
    }

    public boolean checkUser(String userName,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] users = {User.KEY_USER_ID};
        String selection = User.KEY_USER_NAME + " = ?" + " AND " + User.KEY_PASSWORD + " = ?";
        String[] selectionArgs = {userName,password};
        Cursor cursor = db.query(User.TABLE_NAME,
                users,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        db.close();
        cursor.close();
        return cursorCount > 0;

    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        try (SQLiteDatabase db = this.getReadableDatabase()) {
            String selection = "SELECT * FROM " + User.TABLE_NAME;
            try (Cursor cursor = db.rawQuery(selection, null)) {
                if (cursor.moveToFirst()) {
                    do {
                        User user = new User(cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3));
                        Log.d(TAG, "getAllUsers: " + cursor.getString(0) +
                                "/" + cursor.getString(1) + "/" + cursor.getString(2) + "/" + cursor.getString(3));
                        users.add(user);
                    } while (cursor.moveToNext());
                }
            } catch (Exception ignore) {
            }
        } catch (Exception ignore) {
        }
        return users;
    }
}
