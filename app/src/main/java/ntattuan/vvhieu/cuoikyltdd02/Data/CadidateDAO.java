package ntattuan.vvhieu.cuoikyltdd02.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ntattuan.vvhieu.cuoikyltdd02.Model.Candidate;

public class CadidateDAO extends DBManager {
    public CadidateDAO(Context context) {
        super(context);
    }

    //Add new a cadidate
    public void addCandidate(Candidate candidate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CANDIDATE_NAME, candidate.getName());
        values.put(CANDIDATE_CMND, candidate.getCMND());
        values.put(CANDIDATE_GENDER, candidate.getGender());
        values.put(CANDIDATE_AVATAR, candidate.getAvatar());
        //Neu de null thi khi value bang null thi loi
        db.insert(TABLE_CANDIDATE, null, values);
        db.close();
    }

    //Delete a Candidate
    public void deleteCandidate(Candidate candidate) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CANDIDATE, CANDIDATE_ID + " = ?",
                new String[]{String.valueOf(candidate.getId())});
        db.close();
    }

    //select ALL
    public List<Candidate> getAllCandidate() {
        List<Candidate> listCandidate = new ArrayList<Candidate>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CANDIDATE;

        SQLiteDatabase db = this.getWritableDatabase();
        // nhận dữ liệu từ câu query
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Candidate candidate = new Candidate();
                candidate.setId(cursor.getInt(0));
                candidate.setName(cursor.getString(1));
                candidate.setCMND(cursor.getString(2));
                candidate.setGender(cursor.getInt(3));
                candidate.setAvatar(cursor.getBlob(4));
                listCandidate.add(candidate);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listCandidate;
    }

    //select by id
    public Candidate getCandidateByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CANDIDATE, new String[]{CANDIDATE_ID, CANDIDATE_NAME, CANDIDATE_CMND, CANDIDATE_GENDER, CANDIDATE_AVATAR},
                CANDIDATE_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        Candidate candidate = new Candidate();
        if (cursor.moveToFirst()) {
            candidate.setId(cursor.getInt(0));
            candidate.setName(cursor.getString(1));
            candidate.setCMND(cursor.getString(2));
            candidate.setGender(cursor.getInt(3));
            candidate.setAvatar(cursor.getBlob(4));
        }
        cursor.close();
        db.close();
        return candidate;
    }

    //select by name
    public List<Candidate> getCandidateByName(String name) {
        List<Candidate> listCandidate = new ArrayList<Candidate>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CANDIDATE, new String[]{CANDIDATE_ID, CANDIDATE_NAME, CANDIDATE_CMND, CANDIDATE_GENDER, CANDIDATE_AVATAR},
                CANDIDATE_NAME + "=?",
                new String[]{name}, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Candidate candidate = new Candidate();
                candidate.setId(cursor.getInt(0));
                candidate.setName(cursor.getString(1));
                candidate.setCMND(cursor.getString(2));
                candidate.setGender(cursor.getInt(3));
                candidate.setAvatar(cursor.getBlob(4));
                listCandidate.add(candidate);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listCandidate;
    }
}
