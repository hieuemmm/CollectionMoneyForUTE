package ntattuan.vvhieu.cuoikyltdd02.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ntattuan.vvhieu.cuoikyltdd02.Model.DoneMoney;
import ntattuan.vvhieu.cuoikyltdd02.Model.Round;

public class RoundDAO extends DBManager{
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
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MONEYROUND;

        SQLiteDatabase db = this.getWritableDatabase();
        // nhận dữ liệu từ câu query
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Round round = new Round();
                round.setId(cursor.getInt(0));
                round.setName(cursor.getString(1));
                round.setCreateTime(cursor.getString(2));
                round.setIsShow(cursor.getInt(3));
                round.setType(cursor.getInt(4));
                listRound.add(round);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listRound;
    }
}
