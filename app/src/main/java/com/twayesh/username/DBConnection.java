package com.twayesh.username;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;
// outer class
public class DBConnection{
    // define for inner class
    DB dbInfo;
    // constructor
    public DBConnection(Context context){
        dbInfo = new DB(context);
    }
    // here insert 3 parameters into database
    public long dataInsert(String fullname,String username, String password){
        // can write on database
        SQLiteDatabase sqLitedatabase = dbInfo.getWritableDatabase();
        // Contentvalues used for input the value the user has inputed to database
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(DB.names,fullname);
        contentvalues.put(DB.username, username);
        contentvalues.put(DB.password, password);
        // here can insert data
        long id = sqLitedatabase.insert(DB.tableName, null,contentvalues);
        return id;
    }

    public String viewData(){
        SQLiteDatabase sqLiteDatabase = dbInfo.getWritableDatabase();
        // select the columns in database which is names, usernames and password
        String [] columns = {DB.UID, DB.names, DB.username, DB.password};
        // cursor used to point from which table will will get the columns
        Cursor cursor = sqLiteDatabase.query(DB.tableName,columns, null, null, null, null, null);
        StringBuffer stringBuffer = new StringBuffer();
        while(cursor.moveToNext()){
            int uid = cursor.getInt(0);
            String fullname = cursor.getString(1);
            String username = cursor.getString(2);
            String password = cursor.getString(3);
            stringBuffer.append(uid+ " "+fullname+" "+username+ " "+ password+ "\n");
        }
        return stringBuffer.toString();
    }
    public String searchData(String name){
        SQLiteDatabase sqLiteDatabase = dbInfo.getWritableDatabase();
        // select the columns in database which is names, usernames and password
        String [] columns = {DB.names, DB.username, DB.password};
        // cursor used to point from which table will will get the columns
        Cursor cursor = sqLiteDatabase.query(DB.tableName,columns, DB.username+ " = '"+name+"'", null, null, null, null);
        StringBuffer stringBuffer = new StringBuffer();
        while(cursor.moveToNext()){

            int index1= cursor.getColumnIndex(DB.names);
            int index2= cursor.getColumnIndex(DB.username);
            int index3 = cursor.getColumnIndex(DB.password);
            String fullname = cursor.getString(index1);
            String username = cursor.getString(index2);
            String password = cursor.getString(index3);
            stringBuffer.append(fullname+" "+username+ " "+ password+ "\n");
        }
        return stringBuffer.toString();
    }

    public int UpdateData(String oldName, String newName){
        SQLiteDatabase sqLiteDatabase = dbInfo.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB.username,newName);
        // select the columns in database which is names, usernames and password
        String [] whereArgs = {oldName};
        int counter = sqLiteDatabase.update(DB.tableName,contentValues,DB.username+" LIKE ? ",whereArgs);
        return counter;
    }

    public int deleteData(String Name){
        SQLiteDatabase sqLiteDatabase = dbInfo.getWritableDatabase();

        // select the columns in database which is names, usernames and password
        String [] whereArgs = {Name};
        int counter = sqLiteDatabase.delete(DB.tableName,DB.username+" LIKE ? ",whereArgs);
        return counter;
    }
    // inner class
       static class DB extends SQLiteOpenHelper {

        private static final String dataBase_Name = "signupUser";
        private static final String tableName = "SIGNUP";
        private static final int dataBase_Version = 15;
        private static final String UID = "id";
        private static final String names = "Name";
        private static final String username = "Username";
        private static final String password = "Password";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+tableName;
        private static final String CREATE_TABLE = "CREATE TABLE "+tableName+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +names+" VARCHAR(255), "+username+" VARCHAR(255), "+password+" VARCHAR(255));";


        private final Context context;

        public DB(@Nullable Context context) {
            super(context, dataBase_Name, null, dataBase_Version);
            this.context = context;
            Toast.makeText(context, "this is constructor ", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                Toast.makeText(context, "onCreate method", Toast.LENGTH_LONG).show();
                db.execSQL(CREATE_TABLE);
            }catch (SQLException e){
                Toast.makeText(context, "due to: "+e, Toast.LENGTH_LONG).show();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Toast.makeText(context, "onUpgrade method", Toast.LENGTH_LONG).show();
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (SQLException e){
                Toast.makeText(context, "due to: "+e, Toast.LENGTH_LONG).show();
            }

        }
    }


}


