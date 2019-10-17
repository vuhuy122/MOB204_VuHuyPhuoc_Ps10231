package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.SimpleAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import DatabaseHelper.DatabaseHelper;
import model.HoaDon;

public class HoaDonDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_NAME = "HoaDon";
    public static final String SQL_HOA_DON = "CREATE TABLE HoaDon(maHoaDon text primary key," +
            "ngayMua date);";
    public static final String TAG = "HoaDonDAO";

    //    Định dạnh kiểu ngày
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public HoaDonDAO(Context context) {
        dbHelper = new DatabaseHelper(context);

    }

    //    insert HoaDon
    public int insertHoaDon(HoaDon hd) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maHoaDon", hd.getMaHoaDon());
        values.put("ngayMua", sdf.format(hd.getNgayMua()));
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    //    getAll
    public List<HoaDon> getAllHoaDon() throws ParseException {
        db = dbHelper.getWritableDatabase();
        List<HoaDon> dsHoaDon = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            HoaDon ee = new HoaDon();
            ee.setMaHoaDon(c.getString(0));
            ee.setNgayMua((sdf.parse(c.getString(1))));
            dsHoaDon.add(ee);
            Log.d("//====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsHoaDon;
    }

    //    update
    public int updateHoaDon(HoaDon hd) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maHoaDon", hd.getMaHoaDon());
        values.put("ngayMua", sdf.format(hd.getNgayMua()));
        int result = db.update(TABLE_NAME, values, "maHoaDon=?", new String[]{hd.getMaHoaDon()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }
    public int deleteHoaDon(String maHoaHon){
        db = dbHelper.getWritableDatabase();
        int result = db.delete(TABLE_NAME,"maHoaDon=?",new String[]{maHoaHon});
        if (result == 0){
            return -1;
        }
        return 1;
    }
}
