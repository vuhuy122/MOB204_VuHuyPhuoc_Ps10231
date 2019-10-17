package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import DatabaseHelper.DatabaseHelper;
import model.Sach;

public class SachDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String TABLE_NAME = "Sach";
    public static final String SQL_SACH = "CREATE TABLE Sach (maSach text primary key," +
            " maTheLoai text, tenSach text" +
            "tacGia text, NXB text, giaBia double, soLuong number);";
    public static final String TAG = "SachDAO";

    public SachDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //    insert
    public int insertSach(Sach s) {
        ContentValues values = new ContentValues();
        values.put("maSach", s.getMaSach());
        values.put("maTheLoai", s.getMaTheLoai());
        values.put("tenSach", s.getTenSach());
        values.put("tacGia", s.getTacGia());
        values.put("NXB", s.getNXB());
        values.put("giaBia", s.getGiaBia());
        values.put("soLuong", s.getSoLuong());
        if (checkPrimaykey(s.getMaSach())) {
            int result = db.update(TABLE_NAME, values, "maSach=?", new String[]{s.getMaSach()});
            if (result == 0) {
                return -1;
            }
        } else {
            try {
                if (db.insert(TABLE_NAME, null, values) == -1) {
                    return -1;
                }
            } catch (Exception ex) {
                Log.e(TAG, ex.toString());
            }
        }
        return 1;
    }


    //    getAll
    public List<Sach> getAllSach() {
        List<Sach> dsSach = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            Sach s = new Sach();
            s.setMaSach(c.getString(0));
            s.setMaTheLoai(c.getString(1));
            s.setTenSach(c.getString(2));
            s.setTacGia(c.getString(3));
            s.setNXB(c.getString(4));
            s.setGiaBia(c.getDouble(5));
            s.setSoLuong(c.getInt(6));
            dsSach.add(s);
            Log.d("//====", s.toString());
            c.moveToNext();
        }
        c.close();
        return dsSach;
    }

    public int updateSach(Sach s) {
        ContentValues values = new ContentValues();
        values.put("maSach", s.getMaSach());
        values.put("maTheLoai", s.getMaTheLoai());
        values.put("tenSach", s.getTenSach());
        values.put("tacGia", s.getTacGia());
        values.put("NXB", s.getNXB());
        values.put("giaBia", s.getGiaBia());
        values.put("soLuong", s.getSoLuong());
        int result = db.update(TABLE_NAME, values, "maSach=?", new String[]{s.getMaSach()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public int deleteSachBuyID(String maSach) {
        int result = db.delete(TABLE_NAME, "maSach=?", new String[]{maSach});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    //    check
    public boolean checkPrimaykey(String strPrimaryKey) {
//        SELECT
        String[] columns = {"maSach"};
//        WHERE clause
        String selection = "maSach=?";
//        WHERE clause arguments
        String[] selectionAgrs = {strPrimaryKey};
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_NAME, columns, selection, selectionAgrs, null, null, null);
            cursor.moveToFirst();
            int i = cursor.getCount();
            cursor.close();
            if (i <= 0) {
                return false;
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    //    check book
    public Sach checkBook(String strPrimaryKey) {
        Sach s = new Sach();
        //        SELECT
        String[] columns = {"maSach"};
//        WHERE clause
        String selection = "maSach=?";
//        WHERE clause arguments
        String[] selectionAgrs = {strPrimaryKey};
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_NAME, columns, selection, selectionAgrs, null, null, null);
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
                s.setMaSach(cursor.getString(0));
                s.setMaTheLoai(cursor.getString(1));
                s.setTenSach(cursor.getString(2));
                s.setTacGia(cursor.getString(3));
                s.setNXB(cursor.getString(4));
                s.setGiaBia(cursor.getDouble(5));
                s.setSoLuong(cursor.getInt(6));
                Log.d("//====", s.toString());
                break;
            }
            cursor.close();
            return s;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //    lấy ra 10 cuốn sách bán chạy nhất tháng
    public List<Sach> getSachTop10(String month) {
        List<Sach> dsSach = new ArrayList<>();
        if (Integer.parseInt(month) < 10) {
//            nếu tháng nhỏ hơn tháng 10 ==> thêm sô 0 trước
            month = "0" + month;
        }
        String sSQL = "SELECT maSach, SUM(soLuong) as soluong FROM HoaDonChiTiet" +
                " INNER JOIN HoaDon " +
                "ON HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon" +
                " WHERE strftime('%m',HoaDon.ngayMua) = '" + month + "' " +
                "GROUP By maSach ORDER BY soluong DESC LIMIT 10";

        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            Log.d("//=====", c.getString(0));
            Sach s = new Sach();
            s.setMaSach(c.getString(0));
            s.setSoLuong(c.getInt(1));
            s.setGiaBia(0);
            s.setMaTheLoai("");
            s.setTenSach("");
            s.setTacGia("");
            s.setNXB("");
            dsSach.add(s);
            c.moveToNext();
        }
        c.close();
        return dsSach;
    }
}
