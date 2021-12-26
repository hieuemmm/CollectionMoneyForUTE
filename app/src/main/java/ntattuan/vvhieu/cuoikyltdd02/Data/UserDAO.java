package ntattuan.vvhieu.cuoikyltdd02.Data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ntattuan.vvhieu.cuoikyltdd02.App;
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
            values.put(USER_SDT, user.getSDT());
            values.put(USER_IS_ACTIVE, user.getIsActive());
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
                USER_NAME + "=?  COLLATE NOCASE",
                new String[]{String.valueOf(username)}, null, null, null, null);
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }
    //Check a SDT Exits
    public boolean CheckSDTExits(String SDT) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER,
                new String[]{USER_NAME},
                USER_SDT + "=?",
                new String[]{SDT},
                null,
                null,
                null,
                null);
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
    public boolean CheckLogin(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[]{USER_NAME, USER_PASSWORD, USER_ROLE},
                USER_NAME + "=?" + " and " + USER_IS_ACTIVE + "=?",
                new String[]{username,String.valueOf(App.ACTIVE)}, null, null, null, null);
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

    //select ALL UserName
    public String[] getAllUserName() {
        List<String> listUserName = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER + " WHERE " + USER_ROLE + " != " + App.ROLE_ADMIN;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        String[] stringArray = null;
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setUserName(cursor.getString(0));
                listUserName.add(user.getUserName());
            } while (cursor.moveToNext());
            stringArray = listUserName.toArray(new String[0]);
        }
        cursor.close();
        db.close();
        return stringArray;
    }

    //select ALL User
    public List<User> getAllUser(int IsActive) {
        List<User> listUser = new ArrayList<User>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER + " WHERE " + USER_IS_ACTIVE + " = " + IsActive + " ORDER BY " + USER_IS_ACTIVE + " DESC, " + USER_ROLE + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        String[] stringArray = null;
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setUserName(cursor.getString(0));
                user.setPass(cursor.getString(1));
                user.setSDT(cursor.getString(2));
                user.setIsActive(cursor.getInt(3));
                user.setRole(cursor.getInt(4));
                listUser.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listUser;
    }

    //select ALL User
    public List<User> searchUser(String key,int IsActive) {
        List<User> listUser = new ArrayList<User>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                TABLE_USER,
                new String[]{USER_NAME, USER_PASSWORD, USER_SDT, USER_IS_ACTIVE, USER_ROLE},
                USER_IS_ACTIVE + " = " + IsActive+" AND "+USER_NAME + " LIKE ? OR " + USER_SDT + " LIKE ? COLLATE NOCASE",
                new String[]{"%" + key + "%", "%" + key + "%"},
                null, null, null, null
        );
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setUserName(cursor.getString(0));
                user.setPass(cursor.getString(1));
                user.setSDT(cursor.getString(2));
                user.setIsActive(cursor.getInt(3));
                user.setRole(cursor.getInt(4));
                listUser.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listUser;
    }
    public void setActiveUser(User user,int ValeActive) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_IS_ACTIVE, ValeActive);
        db.update(TABLE_USER, values, USER_NAME + "=?", new String[]{user.getUserName()});
        db.close();
    }
}

