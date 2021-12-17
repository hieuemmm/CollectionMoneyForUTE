package ntattuan.vvhieu.cuoikyltdd02.Data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import ntattuan.vvhieu.cuoikyltdd02.Model.Candidate;
import ntattuan.vvhieu.cuoikyltdd02.Model.User;

public class UserDAO extends DBManager {

    public UserDAO(Context context) {
        super(context);
    }

    //Add new a user
    public boolean addUser(User user) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(USER_NAME, user.getUserName());
            values.put(USER_PASSWORD, user.getPass());
            values.put(USER_ROLE, user.getRole());

            db.insert(TABLE_USER, null, values);
            db.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Check a user Exits
    public boolean CheckUserExits(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[]{USER_NAME, USER_PASSWORD, USER_ROLE},
                USER_NAME + "=?",
                new String[]{String.valueOf(username)}, null, null, null, null);
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }
    //Check Login
    //nếu đăng nhập thành công thì return true
    public boolean CheckLogin(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[]{USER_NAME, USER_PASSWORD, USER_ROLE},
                USER_NAME + "=?" + " and " + USER_PASSWORD + "=?",
                new String[]{user.getUserName(), user.getPass()}, null, null, null, null);
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }
    //select by username
    public User getInforUser(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[]{USER_NAME, USER_PASSWORD, USER_ROLE},
                USER_NAME + "=?",
                new String[]{user.getUserName()}, null, null, null, null);
        if (cursor.moveToFirst()) {
            user.setRole(cursor.getInt(2));
        }
        cursor.close();
        db.close();
        return user;
    }
}

