package ntattuan.vvhieu.cuoikyltdd02.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ntattuan.vvhieu.cuoikyltdd02.App;
import ntattuan.vvhieu.cuoikyltdd02.Model.Candidate;
import ntattuan.vvhieu.cuoikyltdd02.Model.DoneMoney;
import ntattuan.vvhieu.cuoikyltdd02.Model.Round;

public class DoneMoneyDAO extends DBManager {
    public DoneMoneyDAO(Context context) {
        super(context);
    }

    //select by cadidate_id & moneyround_id
    public DoneMoney getDoneMoneyByID(int Candidate_id, int MoneyRound_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DONEMONEY, new String[]{DONEMONEY_ID, DONEMONEY_CANDIDATE_ID, DONEMONEY_MONEYROUND_ID, DONEMONEY_IS_ACTIVE, DONEMONEY_CREATE_BY, DONEMONEY_CREATE_TIME, DONEMONEY_DELETE_BY, DONEMONEY_DELETE_TIME},
                DONEMONEY_CANDIDATE_ID + "=? AND " + DONEMONEY_MONEYROUND_ID + "=? AND " + DONEMONEY_IS_ACTIVE + "=?",
                new String[]{String.valueOf(Candidate_id), String.valueOf(MoneyRound_id), String.valueOf(App.ACTIVE)}, null, null, null, null);
        DoneMoney doneMoney = new DoneMoney();
        if (cursor.moveToFirst()) {
            doneMoney.setId(cursor.getInt(0));
            doneMoney.setCadidate_id(cursor.getInt(1));
            doneMoney.setMoneyround_id(cursor.getInt(2));
            doneMoney.setIsActive(cursor.getInt(3));
            doneMoney.setCreate_by(cursor.getString(4));
            doneMoney.setCreate_time(cursor.getString(5));
            doneMoney.setDelete_by(cursor.getString(6));
            doneMoney.setDelete_time(cursor.getString(7));
        }
        cursor.close();
        db.close();
        return doneMoney;
    }

    //select List by cadidate_id
    public List<DoneMoney> getListDoneMoneyByID(int Candidate_id, int type) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_DONEMONEY + "INNER JOIN " + TABLE_MONEYROUND + " ON " + TABLE_MONEYROUND + "." + MONEYROUND_ID + " = " + TABLE_DONEMONEY + "." + DONEMONEY_MONEYROUND_ID
                , new String[]{TABLE_DONEMONEY + "." + DONEMONEY_ID, TABLE_DONEMONEY + "." + DONEMONEY_CANDIDATE_ID, TABLE_DONEMONEY + "." + DONEMONEY_MONEYROUND_ID, TABLE_DONEMONEY + "." + DONEMONEY_IS_ACTIVE, TABLE_DONEMONEY + "." + DONEMONEY_CREATE_BY, TABLE_DONEMONEY + "." + DONEMONEY_CREATE_TIME, TABLE_DONEMONEY + "." + DONEMONEY_DELETE_BY, TABLE_DONEMONEY + "." + DONEMONEY_DELETE_TIME},
                TABLE_DONEMONEY+"."+DONEMONEY_CANDIDATE_ID + "=? AND " + TABLE_MONEYROUND+"."+MONEYROUND_TYPE + " =?",
                new String[]{String.valueOf(Candidate_id), String.valueOf(type)}, null, null, null, null);
        List<DoneMoney> doneMoneyList = new ArrayList<DoneMoney>();
        if (cursor.moveToFirst()) {
            do {
                DoneMoney doneMoney = new DoneMoney();
                doneMoney.setId(cursor.getInt(0));
                doneMoney.setCadidate_id(cursor.getInt(1));
                doneMoney.setMoneyround_id(cursor.getInt(2));
                doneMoney.setIsActive(cursor.getInt(3));
                doneMoney.setCreate_by(cursor.getString(4));
                doneMoney.setCreate_time(cursor.getString(5));
                doneMoney.setDelete_by(cursor.getString(6));
                doneMoney.setDelete_time(cursor.getString(7));
                doneMoneyList.add(doneMoney);
            } while (cursor.moveToNext());
        } else {
            return null;
        }
        cursor.close();
        db.close();
        return doneMoneyList;
    }

    public void updateDoneMoney(DoneMoney doneMoney) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DONEMONEY_IS_ACTIVE, doneMoney.getIsActive());
        values.put(DONEMONEY_DELETE_BY, doneMoney.getCreate_by());
        values.put(DONEMONEY_DELETE_TIME, doneMoney.getDelete_time());
        db.update(TABLE_DONEMONEY, values, DONEMONEY_ID + "=?", new String[]{String.valueOf(doneMoney.getId())});
    }

    //Add new a DoneMoney
    public void addDoneMoney(DoneMoney doneMoney) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DONEMONEY_CANDIDATE_ID, doneMoney.getCadidate_id());
        values.put(DONEMONEY_MONEYROUND_ID, doneMoney.getMoneyround_id());
        values.put(DONEMONEY_IS_ACTIVE, doneMoney.getIsActive());
        values.put(DONEMONEY_CREATE_BY, doneMoney.getCreate_by());
        values.put(DONEMONEY_CREATE_TIME, doneMoney.getCreate_time());
        values.put(DONEMONEY_DELETE_BY, " ");
        values.put(DONEMONEY_DELETE_TIME, " ");
        db.insert(TABLE_DONEMONEY, null, values);
        db.close();
    }

    //checkExits
    public boolean checkExits(int Candidate_id, int MoneyRound_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DONEMONEY, new String[]{DONEMONEY_ID, DONEMONEY_CANDIDATE_ID, DONEMONEY_MONEYROUND_ID, DONEMONEY_IS_ACTIVE, DONEMONEY_CREATE_BY, DONEMONEY_CREATE_TIME, DONEMONEY_DELETE_BY, DONEMONEY_DELETE_TIME},
                DONEMONEY_CANDIDATE_ID + "=? AND " + DONEMONEY_MONEYROUND_ID + "=? AND " + DONEMONEY_IS_ACTIVE + "=?",
                new String[]{String.valueOf(Candidate_id), String.valueOf(MoneyRound_id), String.valueOf(App.ACTIVE)}, null, null, null, null);
        if (cursor.moveToFirst()) {
            return true;//tồn tại
        }
        cursor.close();
        db.close();
        return false;
    }

    public List<DoneMoney> getAllDoneMoney() {
        List<DoneMoney> doneMoneyList = new ArrayList<DoneMoney>();
        String selectQuery = "SELECT  * FROM " + TABLE_DONEMONEY;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                DoneMoney doneMoney = new DoneMoney();
                doneMoney.setId(cursor.getInt(0));
                doneMoney.setCadidate_id(cursor.getInt(1));
                doneMoney.setMoneyround_id(cursor.getInt(2));
                doneMoney.setIsActive(cursor.getInt(3));
                doneMoney.setCreate_by(cursor.getString(4));
                doneMoney.setCreate_time(cursor.getString(5));
                doneMoney.setCreate_by(cursor.getString(6));
                doneMoney.setDelete_time(cursor.getString(7));
                doneMoneyList.add(doneMoney);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return doneMoneyList;
    }

    public int CountIsActive(String UserName, boolean isme, int Type) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery;
        if (isme) {
            if (Type == App.TYPE_ROUND_DOAN_PHI)
                selectQuery = "SELECT  * FROM " + TABLE_DONEMONEY + " WHERE " + DONEMONEY_IS_ACTIVE + " = " + App.ACTIVE + " AND " + DONEMONEY_CREATE_BY + " = '" + UserName + "' AND " + DONEMONEY_MONEYROUND_ID + " = " + App.DotNopTienDoanPhi_Current;
            else
                selectQuery = "SELECT  * FROM " + TABLE_DONEMONEY + " WHERE " + DONEMONEY_IS_ACTIVE + " = " + App.ACTIVE + " AND " + DONEMONEY_CREATE_BY + " = '" + UserName + "' AND " + DONEMONEY_MONEYROUND_ID + " = " + App.DotNopTienHoiPhi_Current;
        } else {
            if (Type == App.TYPE_ROUND_DOAN_PHI)
                selectQuery = "SELECT  * FROM " + TABLE_DONEMONEY + " WHERE " + DONEMONEY_IS_ACTIVE + " = " + App.ACTIVE + " AND " + DONEMONEY_CREATE_BY + " != '" + UserName + "' AND " + DONEMONEY_MONEYROUND_ID + " = " + App.DotNopTienDoanPhi_Current;
            else
                selectQuery = "SELECT  * FROM " + TABLE_DONEMONEY + " WHERE " + DONEMONEY_IS_ACTIVE + " = " + App.ACTIVE + " AND " + DONEMONEY_CREATE_BY + " != '" + UserName + "' AND " + DONEMONEY_MONEYROUND_ID + " = " + App.DotNopTienHoiPhi_Current;
        }
        Cursor cursor = db.rawQuery(selectQuery, null);
        int count = cursor.getCount();
        db.close();
        return count;
    }
}
