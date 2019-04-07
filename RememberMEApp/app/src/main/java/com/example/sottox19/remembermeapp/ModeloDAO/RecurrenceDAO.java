package com.example.sottox19.remembermeapp.ModeloDAO;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import com.example.sottox19.remembermeapp.CRUD.ABCRUD;
import com.example.sottox19.remembermeapp.Interfaces.IRecurrenceSchema;
import com.example.sottox19.remembermeapp.ModeloDAO.Interface.IRecurrenceDAO;
import com.example.sottox19.remembermeapp.ModeloVO.RecurrenceVO;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

public class RecurrenceDAO extends ABCRUD implements IRecurrenceDAO,IRecurrenceSchema {
    private Cursor mFila;
    private ContentValues mRegistro = new ContentValues();
    private RecurrenceVO mrecurrence;
    public RecurrenceDAO(SQLiteDatabase db){
        super(db);
    }


    @Override
    public RecurrenceVO fetchById(int id) {
        // final para que no sea sobreescrito y selectionArgs es para poder ser reemplazado en el query
        final String selectionArgs[] = {String.valueOf(id)};
        final String selection = id + "= ?";
        mrecurrence = new RecurrenceVO();
         mFila = super.consulta(recurrenceTable,recurrenceColumn,selection,selectionArgs,COLUMN_IDRECURRENCE);
            if(mFila!=null){
                mFila.moveToFirst();
                while (!mFila.isAfterLast()) {
                    mrecurrence = cursorToEntity(mFila);
                    Log.d("Recurrence","Se encontro la recurrencia por id la recurrencia por id");
                    mFila.moveToNext();
                }mFila.close();
            }
        return mrecurrence;
    }

    @Override
    public ArrayList<RecurrenceVO> fetchAllRecurrence() {
        ArrayList<RecurrenceVO> listaRecurrence = new ArrayList<RecurrenceVO>();
        mFila = super.consulta(recurrenceTable,recurrenceColumn,null,null,COLUMN_IDRECURRENCE);

        if (mFila != null){
            mFila.moveToFirst();
            while(!mFila.isAfterLast()) {
                mrecurrence = cursorToEntity(mFila);
                Log.d("Recurrencia","Recurrencia agregada a la lista");
                listaRecurrence.add(mrecurrence);
                mFila.moveToNext();
            }mFila.close();
        }
        return listaRecurrence;
    }

    @Override
    public boolean addRecurrence(RecurrenceVO recurrence) {
        setValues();

        Log.d("nada","nada");
      try{
         return super.insert(recurrenceTable,getContentValues())>0;
      }catch (SQLiteConstraintException e){
          Log.d("Database",e.getMessage());
          return false;
      }
    }
    @Override
    protected RecurrenceVO cursorToEntity(Cursor consulta) {
       mrecurrence = new RecurrenceVO();

       // variables de indices de cursor
        int idIndex= 0;
        int nameIndex = 0;
        int descriptionIndex = 0;
        int intervaltypeIndex = 0;
        int intervalIndex = 0;
       if (mFila.getColumnIndex(COLUMN_IDRECURRENCE )!=-1){
           idIndex = mFila.getColumnIndex(COLUMN_IDRECURRENCE);
           mrecurrence.setMidRecurrence(mFila.getInt(idIndex));}
       if(mFila.getColumnIndex(COLUMN_NAME)!=-1){
           nameIndex = mFila.getColumnIndex(COLUMN_NAME);
           mrecurrence.setMname(mFila.getString(nameIndex));}
       if (mFila.getColumnIndex(COLUMN_DESCRIPTION)!=-1){
           descriptionIndex = mFila.getColumnIndex(COLUMN_DESCRIPTION);
           mrecurrence.setMdescription(mFila.getString(descriptionIndex));
       }
       if (mFila.getColumnIndex(COLUMN_INTERVALTYPE)!=-1){
           mFila.getColumnIndex(COLUMN_INTERVALTYPE);
           mrecurrence.setMtype(mFila.getString(intervaltypeIndex));
       }
       if (mFila.getColumnIndex(COLUMN_INTERVAL)!=-1){
           mFila.getColumnIndex(COLUMN_INTERVAL);
           mrecurrence.setMinterval(mFila.getInt(intervalIndex));
       }

        return mrecurrence;
    }

    @Override
    public boolean deleteRecurrence(RecurrenceVO recurrence) {
        return false;
    }
    private void setValues(){
        mRegistro.put("idRecurrence","klk");
        mRegistro.put("name","Semanal");
        mRegistro.put("description","nada");
        mRegistro.put("intervaltype","Dia");
        mRegistro.put("interval","klk");
        Log.d("Database","hola");
    }
    private ContentValues getContentValues() {
        return mRegistro;
    }
}
