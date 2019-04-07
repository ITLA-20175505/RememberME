package com.example.sottox19.remembermeapp.Conexion;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sottox19.remembermeapp.Interfaces.IRecurrenceSchema;
import com.example.sottox19.remembermeapp.ModeloDAO.RecurrenceDAO;

public class Database {
    private static final String TAG = "MyDatabase";
    private static final String DATABASE_NAME = "personal.db";
    private static final int DATABASE_VERSION = 1;
    private DatabaseHelper mDbHelper;

    private final Context mContext;
    public static RecurrenceDAO mRecurrenceDAO;


    public Database open(){
        mDbHelper = new DatabaseHelper(mContext);
        SQLiteDatabase mdb = mDbHelper.getWritableDatabase();

        mRecurrenceDAO = new RecurrenceDAO(mdb);
        return this;
    }
    public void close(){
        mDbHelper.close();
    }
    public Database(Context context){
        this.mContext = context;
    }

    public static class DatabaseHelper extends SQLiteOpenHelper{
        DatabaseHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(IRecurrenceSchema.createTable);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d(TAG,"Upgrading Database from " + oldVersion + "TO "+ newVersion+ " " +
                    "which destroy all date");

            db.execSQL("DROP TABLE IF EXISTS " + IRecurrenceSchema.recurrenceTable);
            onCreate(db);
        }
    }
}
