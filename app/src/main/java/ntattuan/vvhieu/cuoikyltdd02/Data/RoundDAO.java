package ntattuan.vvhieu.cuoikyltdd02.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ntattuan.vvhieu.cuoikyltdd02.App;
import ntattuan.vvhieu.cuoikyltdd02.Model.DoneMoney;
import ntattuan.vvhieu.cuoikyltdd02.Model.Round;

public class RoundDAO extends DBManager {
    public RoundDAO(Context context) {
        super(context);
    }

    //Add new a Round
    public void addRound(Round round) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MONEYROUND_NAME, round.getName());
        values.put(MONEYROUND_CREATE_TIME, round.getCreateTime());
        values.put(MONEYROUND_IS_SHOW, round.getIsShow());
        values.put(MONEYROUND_TYPE, round.getType());
        //Neu de null thi khi value bang null thi loi
        db.insert(TABLE_MONEYROUND, null, values);
        db.close();
    }
    //Check a round Exits
    public boolean CheckRoundExits(String Name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MONEYROUND,
                new String[]{MONEYROUND_ID},
                MONEYROUND_NAME + "=?",
                new String[]{Name},
                null,
                null,
                null,
                null);
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }
    //Delete
    public void deleteRound(Round round) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MONEYROUND, MONEYROUND_ID + " = ?",
                new String[]{String.valueOf(round.getId())});
        db.close();
    }

    // Select ALL
    public List<Round> getAllRound() {
        List<Round> listRound = new ArrayList<Round>();
        String selectQuery = "SELECT  * FROM " + TABLE_MONEYROUND;
        if (App.Round_Tab_Current == App.ROUND_TAB_DOAN_PHI) {
            selectQuery = "SELECT  * FROM " + TABLE_MONEYROUND + " WHERE " + MONEYROUND_TYPE + " = " + App.TYPE_ROUND_DOAN_PHI;
        } else {
            selectQuery = "SELECT  * FROM " + TABLE_MONEYROUND + " WHERE " + MONEYROUND_TYPE + " = " + App.TYPE_ROUND_HOI_PHI;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Round round = new Round();
                round.setId(cursor.getInt(0));
                round.setName(cursor.getString(1));
                round.setCreateTime(cursor.getString(2));
                round.setPrice(cursor.getInt(3));
                round.setIsShow(cursor.getInt(4));
                round.setType(cursor.getInt(5));
                listRound.add(round);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listRound;
    }
}
