package com.example.jose.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manjusha on 3/13/2015.
 */
public class Database extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="Todo";
    public  static final String TABLE_NAME = "TableTodo";
    public  static  final  String KEY_ID= "id";
    public  static  final  String KEY_TITLE= "title";
    public  static  final  String KEY_DESCRIPTION= "desc";
    public  static  final  String KEY_DATE= "date";
    public  static  final  String KEY_STATUS= "status";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Contact_Table = "CREATE TABLE "+TABLE_NAME+"("+KEY_ID+"INTEGER PRIMARY KEY,"+KEY_TITLE+"TEXT,"
                +KEY_DESCRIPTION+"TEXT,"+KEY_DATE+"TEXT,"+KEY_STATUS+"INTEGER"+")";
        db.execSQL(Contact_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        onCreate(db);
    }

    void adDetails(Details dtls){
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE,dtls.getDtitle());
        values.put(KEY_DESCRIPTION,dtls.getDdesc() );
        values.put(KEY_DATE, dtls.getDdate());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public List<Details>getAllData(){
        List<Details>databasedtls = new ArrayList<Details>();
        String selectQuery = "SELECT * FROM  "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor crser = db.rawQuery(selectQuery,null);
        if(crser.moveToFirst()){
            do{
                    Details datls=new Details();
                    datls.setDtitle(crser.getString(1));
                datls.setDdesc(crser.getString(2));
                datls.setDdate(crser.getString(3));
                databasedtls.add(datls);
            }while (crser.moveToNext());
        }
        return databasedtls;
    }

}
