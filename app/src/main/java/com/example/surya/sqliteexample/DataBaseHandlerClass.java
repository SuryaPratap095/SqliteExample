package com.example.surya.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by surya on 11/11/16.
 */
public class DataBaseHandlerClass extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="ContactManager";
    private static final String TABLE_CONTACTS="contacts";
    private static final int DATABASE_VERSION= 1;

    private static final String KEY_ID="id";
    private static final String KEY_NAME="name";
    private static final String KEY_PH_NO="phone_number";

    public DataBaseHandlerClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ");";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP IF ALREADY EXISTS "+TABLE_CONTACTS);
        onCreate(sqLiteDatabase);
    }

    public void addContacts(Contacts contacts){
       SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_ID,contacts.getId());
        contentValues.put(KEY_NAME,contacts.getName());
        contentValues.put(KEY_PH_NO,contacts.getPhoneNumber());

        sqLiteDatabase.insert(TABLE_CONTACTS,null,contentValues);
    }

    public ArrayList<Contacts> getAllContacts(){

        ArrayList<Contacts> list=new ArrayList<Contacts>();

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        String query="SELECT * FROM "+TABLE_CONTACTS;
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                Contacts contacts=new Contacts();
                contacts.setName(cursor.getString(1));
                contacts.setPhoneNumber(cursor.getString(2));
                list.add(contacts);
            }while(cursor.moveToNext());

        }


        return list;
    }

    public int updateContact(Contacts contacts){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(KEY_NAME,contacts.getName());
        values.put(KEY_PH_NO,contacts.getPhoneNumber());


        return sqLiteDatabase.update(TABLE_CONTACTS,values,KEY_ID+"=?",new String[]{String.valueOf(contacts.getId())});

    }



    public void deleteContact(Contacts contacts){
        long id=contacts.getId();
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        System.out.println("Comment deleted with id: " + id);
        sqLiteDatabase.delete(TABLE_CONTACTS,KEY_ID+" ="+id,null);
        //Log.d("InDeleteaAccount: ",contacts.getName());
        sqLiteDatabase.close();
    }

    public void deleteContactTry(){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.execSQL("DROP IF ALREADY EXISTS "+TABLE_CONTACTS);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.close();
    }

    public int getContactsCount(){
        String countQuery="Select * FROM "+TABLE_CONTACTS;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor =sqLiteDatabase.rawQuery(countQuery,null);
        cursor.close();
        return cursor.getCount();
    }
}
