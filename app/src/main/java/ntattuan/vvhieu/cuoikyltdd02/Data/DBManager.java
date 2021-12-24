package ntattuan.vvhieu.cuoikyltdd02.Data;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ntattuan.vvhieu.cuoikyltdd02.Model.User;
import ntattuan.vvhieu.cuoikyltdd02.Data.UserDAO;

public class DBManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="QuanLyDoanPhiHoiPhi";

    protected static final String TABLE_USER ="users";
    protected static final String USER_NAME ="username";
    protected static final String USER_PASSWORD ="password";
    protected static final String USER_SDT ="sdt";
    protected static final String USER_IS_ACTIVE ="isactive";
    protected static final String USER_ROLE ="role";

    protected static final String TABLE_CANDIDATE ="candidates";
    protected static final String CANDIDATE_ID ="id";
    protected static final String CANDIDATE_NAME ="name";
    protected static final String CANDIDATE_CMND ="cmnd";
    protected static final String CANDIDATE_SDT="sdt";
    protected static final String CANDIDATE_GENDER ="gender";
    protected static final String CANDIDATE_AVATAR ="avatar";
    protected static final String CANDIDATE_IS_ACTIVE ="is_active";

    protected static final String TABLE_MONEYROUND ="money_round";
    protected static final String MONEYROUND_ID ="id";
    protected static final String MONEYROUND_NAME ="name";
    protected static final String MONEYROUND_CREATE_TIME ="create_time";
    protected static final String MONEYROUND_PRICE ="price";
    protected static final String MONEYROUND_IS_SHOW ="is_show";
    protected static final String MONEYROUND_TYPE ="type";

    protected static final String TABLE_DONEMONEY ="done_money";
    protected static final String DONEMONEY_ID ="id";
    protected static final String DONEMONEY_CANDIDATE_ID ="candidate_id";
    protected static final String DONEMONEY_MONEYROUND_ID ="money_round_id";
    protected static final String DONEMONEY_IS_ACTIVE ="is_active";
    protected static final String DONEMONEY_CREATE_BY ="create_by";
    protected static final String DONEMONEY_CREATE_TIME ="create_time";
    protected static final String DONEMONEY_DELETE_BY ="delete_by";
    protected static final String DONEMONEY_DELETE_TIME ="delete_time";

    protected Context context;
    public DBManager(Context context) {
        super(context, DATABASE_NAME,null, 1);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table user
        String sqlQuery = "CREATE TABLE "+TABLE_USER +" (" +
                USER_NAME +" TEXT primary key, "+
                USER_PASSWORD + " TEXT, "+
                USER_SDT + " TEXT, "+
                USER_IS_ACTIVE + " INTEGER, "+
                USER_ROLE +" INTEGER)";
        db.execSQL(sqlQuery);
        //create table candidates
        sqlQuery = "CREATE TABLE "+TABLE_CANDIDATE +" (" +
                CANDIDATE_ID +" INTEGER  primary key, "+
                CANDIDATE_NAME + " TEXT, "+
                CANDIDATE_CMND + " TEXT, "+
                CANDIDATE_SDT + " TEXT, "+
                CANDIDATE_GENDER + " INTEGER, "+ //1 nam 0 nu
                CANDIDATE_AVATAR + " BLOB, "+
                CANDIDATE_IS_ACTIVE +" INTEGER)";
        db.execSQL(sqlQuery);
        //create table moneyround
        sqlQuery = "CREATE TABLE "+TABLE_MONEYROUND +" (" +
                MONEYROUND_ID +" INTEGER  primary key, "+
                MONEYROUND_NAME + " TEXT, "+
                MONEYROUND_CREATE_TIME + " TEXT, "+
                MONEYROUND_PRICE + " INTEGER, "+
                MONEYROUND_IS_SHOW + " INTEGER, "+
                MONEYROUND_TYPE +" INTEGER)";
        db.execSQL(sqlQuery);
        //create table donemoney
        sqlQuery = "CREATE TABLE "+TABLE_DONEMONEY +" (" +
                DONEMONEY_ID +" INTEGER  primary key, "+
                DONEMONEY_CANDIDATE_ID + " INTEGER, "+
                DONEMONEY_MONEYROUND_ID + " INTEGER, "+
                DONEMONEY_IS_ACTIVE + " INTEGER, "+
                DONEMONEY_CREATE_BY + "  TEXT, "+
                DONEMONEY_CREATE_TIME + "  TEXT, "+
                DONEMONEY_DELETE_BY + "  TEXT, "+
                DONEMONEY_DELETE_TIME +" TEXT)";
        db.execSQL(sqlQuery);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CANDIDATE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MONEYROUND);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DONEMONEY);
        onCreate(db);
    }
}
