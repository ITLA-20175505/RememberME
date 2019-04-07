package com.example.sottox19.remembermeapp.ModeloDAO;

import android.content.ContentValues;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import com.example.sottox19.remembermeapp.CRUD.ABCRUD;
import com.example.sottox19.remembermeapp.TableSchemes.IRecurrenceSchema;
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
        final String selectionArgs[] = {String.valueOf(id),String.valueOf(0)};
        final String selection = COLUMN_IDRECURRENCE  + "= ? AND " + COLUMN_ISCANCELLED + " = ?";
         mFila = super.consulta(recurrenceTable,recurrenceColumn,selection,selectionArgs,COLUMN_IDRECURRENCE);
            if(mFila!=null){
                mFila.moveToFirst();
                while (!mFila.isAfterLast()) {
                    mrecurrence = cursorToEntity(mFila);
                    mFila.moveToNext();
                }mFila.close();}
        return mrecurrence;
    }

    @Override
    public ArrayList<RecurrenceVO> fetchAllRecurrence() {
        ArrayList<RecurrenceVO> listaRecurrence = new ArrayList<RecurrenceVO>();
        final String selectionArgs[] = {String.valueOf(0)};
        final String selection = COLUMN_ISCANCELLED  + " = ?";

        mFila = super.consulta(recurrenceTable,recurrenceColumn,selection ,selectionArgs,COLUMN_IDRECURRENCE);
        if (mFila != null){
            mFila.moveToFirst();
            while(!mFila.isAfterLast()) {
                mrecurrence = cursorToEntity(mFila);
                listaRecurrence.add(mrecurrence);
                mFila.moveToNext();
            }mFila.close();
        }
        return listaRecurrence;
    }

    @Override
    public boolean addRecurrence(RecurrenceVO recurrence) {
        setValues(recurrence);
      try{
         return super.insert(recurrenceTable,getContentValues())>0;

      }catch (SQLiteConstraintException e){
          Log.d("Database",e.getMessage());
          return false;
      }
    }
    @Override
    public int deleteRecurrence(int id) {
        ContentValues isCancelled = new ContentValues();
        isCancelled.put(COLUMN_ISCANCELLED,1);
        final String selectionArgs[] = {String.valueOf(id)};
        final String selection = COLUMN_IDRECURRENCE + "= ?";
        try{
        return super.update(recurrenceTable,isCancelled,selection,selectionArgs);}
        catch (SQLiteConstraintException e){
            Log.d("Database",e.getMessage());
            return 0;
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
           mrecurrence.setidRecurrence(mFila.getInt(idIndex));}
       else if(mFila.getColumnIndex(COLUMN_NAME)!=-1){
           nameIndex = mFila.getColumnIndex(COLUMN_NAME);
           mrecurrence.setname(mFila.getString(nameIndex));}
       else if (mFila.getColumnIndex(COLUMN_DESCRIPTION)!=-1){
           descriptionIndex = mFila.getColumnIndex(COLUMN_DESCRIPTION);
           mrecurrence.setdescription(mFila.getString(descriptionIndex));
       }
        else if (mFila.getColumnIndex(COLUMN_INTERVALTYPE)!=-1){
           mFila.getColumnIndex(COLUMN_INTERVALTYPE);
           mrecurrence.settype(mFila.getString(intervaltypeIndex));
       }
       else if (mFila.getColumnIndex(COLUMN_INTERVAL)!=-1){
           mFila.getColumnIndex(COLUMN_INTERVAL);
           mrecurrence.setinterval(mFila.getInt(intervalIndex));
       }

        return mrecurrence;
    }


    private void setValues(RecurrenceVO recurrence){
         mRegistro.put(COLUMN_NAME,recurrence.getname());
        mRegistro.put(COLUMN_DESCRIPTION,recurrence.getdescription());
        mRegistro.put(COLUMN_INTERVALTYPE,recurrence.gettype());
        mRegistro.put(COLUMN_INTERVAL,recurrence.getinterval());

    }
    private ContentValues getContentValues() {
        return mRegistro;
    }
}
