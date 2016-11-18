package com.ttth.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.ttth.item.Personnel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Administrator on 24/03/2016.
 */
public class PersonnelDatabase {
    private static final String TABLE_PERSONNEL = "PERSONNEL";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_USER = "USER";
    private static final String COLUMN_PASS = "PASS";
    private static final String TAG = "persondatabase";
    private Context context;
    private SQLiteDatabase database;
    public static final String PATH = Environment.getDataDirectory().getPath() + "/data/com.ttth.test.test/data29/THANHHANG.sqlite";

    public PersonnelDatabase(Context context) {
        readDatabase(context);
        this.context = context;
    }

    private void readDatabase(Context context) {
        try {
            InputStream inputStream = context.getAssets().open("THANHHANG.sqlite");
            File file = new File(PATH);
            if (!file.exists()) {
                File parent = file.getParentFile();
                parent.mkdirs();
                file.createNewFile();
                FileOutputStream outputStream = new FileOutputStream(file);
                byte[] b = new byte[1024];
                int count = inputStream.read(b);
                while (count != -1) {
                    outputStream.write(b, 0, count);
                    count = inputStream.read(b);
                }
                outputStream.close();
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openDatabase() {
        database = context.openOrCreateDatabase(PATH, context.MODE_APPEND, null);
    }

    public void closeDatabase() {

        database.close();
    }

    public ArrayList<Personnel> getPersonnelData() {
        ArrayList<Personnel> arrPersonnel = new ArrayList<Personnel>();
        openDatabase();
        Cursor cursor = database.query(TABLE_PERSONNEL, null, null, null, null, null, null);
        cursor.moveToFirst();
        int indextID = cursor.getColumnIndex(COLUMN_ID);
        int indextUSER = cursor.getColumnIndex(COLUMN_USER);
        int indextPASS = cursor.getColumnIndex(COLUMN_PASS);
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(indextID);
            String userName = cursor.getString(indextUSER);
            String pass = cursor.getString(indextPASS);
            Personnel personnel = new Personnel(id, userName, pass);
            arrPersonnel.add(personnel);

            cursor.moveToNext();
        }
        closeDatabase();
        return arrPersonnel;
    }
}

