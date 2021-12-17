package ntattuan.vvhieu.cuoikyltdd02.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import ntattuan.vvhieu.cuoikyltdd02.Model.Candidate;
import ntattuan.vvhieu.cuoikyltdd02.Model.DoneMoney;
import ntattuan.vvhieu.cuoikyltdd02.Model.Round;

public class DoneMoneyDAO extends DBManager{
    public DoneMoneyDAO(Context context) {
        super(context);
    }
    //select by cadidate_id & moneyround_id
    public DoneMoney getDoneMoneyByID(int Candidate_id,int MoneyRound_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DONEMONEY, new String[]{DONEMONEY_ID, DONEMONEY_CANDIDATE_ID, DONEMONEY_MONEYROUND_ID, DONEMONEY_CREATE_BY},
                DONEMONEY_CANDIDATE_ID + "=? AND "+DONEMONEY_MONEYROUND_ID +"=?",
                new String[]{String.valueOf(Candidate_id),String.valueOf(MoneyRound_id)}, null, null, null, null);
        DoneMoney doneMoney = new DoneMoney();
        if (cursor.moveToFirst()) {
            doneMoney.setCadidate_id(cursor.getInt(1));
            doneMoney.setMoneyround_id(cursor.getInt(2));
            doneMoney.setCreate_by(cursor.getString(3));
        }
        cursor.close();
        db.close();
        return doneMoney;
    }

    public void updateDoneMoney (DoneMoney doneMoney) {
        if (checkExits(doneMoney.getCadidate_id(),doneMoney.getMoneyround_id())){
            deleteDoneMoney(doneMoney);
        }else {
            addDoneMoney(doneMoney);
        }
    }
    //Add new a DoneMoney
    private void addDoneMoney(DoneMoney doneMoney) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DONEMONEY_CANDIDATE_ID, doneMoney.getCadidate_id());
        values.put(DONEMONEY_MONEYROUND_ID, doneMoney.getMoneyround_id());
        values.put(DONEMONEY_CREATE_BY, doneMoney.getCreate_by());
        //Neu de null thi khi value bang null thi loi
        db.insert(TABLE_DONEMONEY, null, values);
        db.close();
    }
    //checkExits
    private boolean checkExits(int Candidate_id,int MoneyRound_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DONEMONEY, new String[]{DONEMONEY_ID},
                DONEMONEY_CANDIDATE_ID + "=? AND "+DONEMONEY_MONEYROUND_ID +"=?",
                new String[]{String.valueOf(Candidate_id),String.valueOf(MoneyRound_id)}, null, null, null, null);
        Candidate candidate = new Candidate();
        if (cursor.moveToFirst()) {
            return true;//tồn tại
        }
        cursor.close();
        db.close();
        return false;
    }

    //Delete a DoneMoney
    private void deleteDoneMoney(DoneMoney doneMoney) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DONEMONEY, DONEMONEY_CANDIDATE_ID + " = ? AND "+ DONEMONEY_MONEYROUND_ID +"=?",
                new String[]{String.valueOf(doneMoney.getCadidate_id()),String.valueOf(doneMoney.getMoneyround_id())});
        db.close();
    }
}
