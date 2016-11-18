package com.ttth.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.ttth.item.Customer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**a
 * Created by Administrator on 24/03/2016.
 */
public class CustomerDatabase {
    private static final String TABLE_NAME = "CUSTOMER";
    private static final String COLUMN_CUSTOMER_ID = "ID";
    private static final String COLUMN_NAME = "NAME";
    private static final String COLUMN_BRITHDAY = "BIRTHDAY";
    private static final String COLUMN_EMAIL = "EMAIL";
    private static final String COLUMN_PHONE = "PHONE NUMBER";
    private static final String COLUMN_POSITION ="POSITION" ;
    private static final String COLUMN_ADDRESS = "ADDRESS";
    private static final String COLUMN_TYPE = "TYPE";
    private static final String COLUMN_ORGANIZATION = "ORGANIZATION";
    private Context context;
    private SQLiteDatabase database;
    public static final String PATH = Environment.getDataDirectory().getPath()+"/data/com.ttth.test.test/database29/THANHHANG.sqlite";
    public CustomerDatabase(Context context) {
        copyDataBase(context);
        this.context = context;
    }

    private void copyDataBase(Context context) {
        try{
            InputStream inputStream = context.getAssets().open("THANHHANG.sqlite");
            File file = new File(PATH);
            if (!file.exists()){
                File parent = file.getParentFile();
                parent.mkdirs();
                file.createNewFile();
                FileOutputStream outputStream = new FileOutputStream(file);
                byte [] b = new byte[1024];
                int count  = inputStream.read(b);
                while (count != -1){
                    outputStream.write(b,0,count);
                    count = inputStream.read(b);
                }
                outputStream.close();
            }
            inputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void openDataBase(){
        database = context.openOrCreateDatabase(PATH,context.MODE_APPEND,null);
    }
    public void closeDatabase(){
        database.close();
    }
    public ArrayList<Customer> getCustomerData(){
        ArrayList<Customer> arrCustomer = new ArrayList<Customer>();
        openDataBase();
        Cursor cursor = database.query(TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        int indexID = cursor.getColumnIndex(COLUMN_CUSTOMER_ID);
        int indexNAME = cursor.getColumnIndex(COLUMN_NAME);
        int indexBIRTHDAY = cursor.getColumnIndex(COLUMN_BRITHDAY);
        int indexEMAIL = cursor.getColumnIndex(COLUMN_EMAIL);
        int indexPHONE = cursor.getColumnIndex(COLUMN_PHONE);
        int indexORGANIZATION = cursor.getColumnIndex(COLUMN_ORGANIZATION);
        int indexPOSITION = cursor.getColumnIndex(COLUMN_POSITION);
        int indexADDRESS = cursor.getColumnIndex(COLUMN_ADDRESS);
        int indexTYPE = cursor.getColumnIndex(COLUMN_TYPE);
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(indexID);
            String name = cursor.getString(indexNAME);
            String birthday = cursor.getString(indexBIRTHDAY);
            String email= cursor.getString(indexEMAIL);
            String phone = cursor.getString(indexPHONE);
            String organization = cursor.getString(indexORGANIZATION);
            String position = cursor.getString(indexPOSITION);
            String address = cursor.getString(indexADDRESS);
            String type = cursor.getString(indexTYPE);
            Customer customer = new Customer(id,name,birthday,email,phone,organization,position,address,type);
            arrCustomer.add(customer);
            cursor.moveToNext();
        }
        closeDatabase();
        return arrCustomer;
    }
}
