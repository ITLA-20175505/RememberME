package com.example.user.remember_me.Conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.user.remember_me.ModeloDAO.RecurrenceDAO;
import com.example.user.remember_me.ModeloDAO.TaskDAO;
import com.example.user.remember_me.TableScheme.IRecurrenceSchema;
import com.example.user.remember_me.TableScheme.ITaskSchema;

public class BaseDeDatos {
    private static final String TAG = "MyDatabase";
    private static final String DATABASE_NAME = "personal.db";
    private static final int DATABASE_VERSION = 1;
    private DatabaseHelper mDbHelper;

    private final Context mContext;
    public static RecurrenceDAO mRecurrenceDAO;
    public static TaskDAO mTaskDAO;

    public BaseDeDatos open(){
        mDbHelper = new DatabaseHelper(mContext);
        SQLiteDatabase mdb = mDbHelper.getWritableDatabase();

        mRecurrenceDAO = new RecurrenceDAO(mdb);
        mTaskDAO = new TaskDAO(mdb);
        return this;
    }
    public void close(){
        mDbHelper.close();
    }
    public BaseDeDatos(Context context){
        this.mContext = context;
    }

    public class DatabaseHelper extends SQLiteOpenHelper{
        DatabaseHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(IRecurrenceSchema.createTable);
            db.execSQL(ITaskSchema.createTable);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d(TAG,"Upgrading Database from " + oldVersion + "TO "+ newVersion+ " " +
                    "which destroy all date");

            db.execSQL("DROP TABLE IF EXISTS " + IRecurrenceSchema.recurrenceTable);
            db.execSQL("DROP TABLE IF EXISTS " + ITaskSchema.taskTable);
            onCreate(db);
        }
    }
}