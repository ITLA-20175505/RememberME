package com.example.user.remember_me.ModeloDAO;

import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import com.example.user.remember_me.CRUD.ABCRUD;
import com.example.user.remember_me.ModeloDAO.Interface.ITaskDAO;
import com.example.user.remember_me.ModeloVO.RecurrenceVO;
import com.example.user.remember_me.ModeloVO.TaskVO;
import com.example.user.remember_me.TableScheme.ITaskSchema;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TaskDAO extends ABCRUD implements ITaskDAO,ITaskSchema {
    private Cursor mFila;
    private ContentValues mRegistro = new ContentValues();
    private TaskVO mtask;
    private RecurrenceVO mrecurrence;
    private Calendar mCalendar;
    private SimpleDateFormat mformato;

    public TaskDAO(SQLiteDatabase db) {
        super(db);
    }

    protected TaskVO cursorToEntity(Cursor consulta) {
        mtask = new TaskVO();
        mrecurrence = new RecurrenceVO();
        int idTaskindex = 0;
        int idNameIndex = 0;
        int idDescriptionIndex = 0;
        int taskDateIndex = 0;
        int nextDateIndex = 0;
        int recurrenceIndex = 0;
        int isCancelledIndex = 0;
        int isDoneIndex = 0;
        if (mFila.getColumnIndex(COLUMN_IDTASK) != -1) {
            idTaskindex = mFila.getColumnIndex(COLUMN_IDTASK);
            mtask.setidTask(mFila.getInt(idTaskindex));
        } else if (mFila.getColumnIndex(COLUMN_NAME) != -1) {
            idNameIndex = mFila.getColumnIndex(COLUMN_NAME);
            mtask.setname(mFila.getString(idNameIndex));
        } else if (mFila.getColumnIndex(COLUMN_DESCRIPTION) != -1) {
            idDescriptionIndex = mFila.getColumnIndex(COLUMN_DESCRIPTION);
            mtask.setdescription(mFila.getString(idDescriptionIndex));
        } else if (mFila.getColumnIndex(COLUMN_DATE) != -1) {
            taskDateIndex = mFila.getColumnIndex(COLUMN_DATE);
            mtask.settaskDate(mFila.getString(taskDateIndex));
        } else if (mFila.getColumnIndex(COLUMN_NEXTDATE) != -1) {
            nextDateIndex = mFila.getColumnIndex(COLUMN_NEXTDATE);
            mtask.setnextDate(mFila.getString(nextDateIndex));
        } else if (mFila.getColumnIndex(COLUMN_RECURRENCE) != -1) {
            recurrenceIndex = mFila.getColumnIndex(COLUMN_RECURRENCE);
            mrecurrence.setidRecurrence(recurrenceIndex);
            mtask.setRecurrence(mrecurrence);
        } else if (mFila.getColumnIndex(COLUMN_ISCANCELLED) != -1) {
            isCancelledIndex = mFila.getColumnIndex(COLUMN_ISCANCELLED);
            mtask.setIsCancelled(mFila.getInt(isCancelledIndex) >= 0);
        } else if (mFila.getColumnIndex(COLUMN_ISDONE) != -1) {
            isDoneIndex = mFila.getColumnIndex(COLUMN_ISDONE);
            mtask.setisDone(mFila.getInt(isDoneIndex) >= 0);
        }
        return mtask;
    }

    @Override
    public boolean addTask(TaskVO task) {
         setValues(task);
        try{
            return super.insert(taskTable,getValues())>0;
        }catch (SQLiteConstraintException e){
            Log.d("Database",e.getMessage());
            return false;
        }
    }

    @Override
    public int deleteTask(int id) {
        ContentValues isCancelled = new ContentValues();
        isCancelled.put(COLUMN_ISCANCELLED,1);
        final String [] selectionArgs = {String.valueOf(id),String.valueOf(0)};
        final String selection = COLUMN_IDTASK + " = ? AND " + COLUMN_ISCANCELLED +" = ?";
        try{
               return super.update(taskTable,isCancelled,selection,selectionArgs);
        }  catch (SQLiteConstraintException e){
            Log.d("Database",e.getMessage());
            return 0;
        }
    }
    @Override
    public TaskVO fetchById(int id) {
            String [] selectionArgs = {String.valueOf(id)};
            String selection = COLUMN_IDTASK + " = ?";
            mFila =super.consulta(taskTable,taskColumn,selection,selectionArgs,COLUMN_IDTASK);
            if(mFila!=null){
                mFila.moveToFirst();
                while (!mFila.isAfterLast()){
                    mtask = cursorToEntity(mFila);
                    mFila.moveToNext();
                }mFila.close();
            } return mtask;
        }

    @Override
    public ArrayList<TaskVO> fetchAllTask() {
        ArrayList<TaskVO> listaTarea = new ArrayList<TaskVO>();
        String[] selectionArgs = {String.valueOf(0)};
        String selection = COLUMN_ISCANCELLED + " = ?";
        mFila = super.consulta(taskTable,taskColumn,selection,selectionArgs,COLUMN_IDTASK);
        if(mFila!= null){
            mFila.moveToFirst();
            while(!mFila.isAfterLast()){
                mtask = cursorToEntity(mFila);
                listaTarea.add(mtask);
            }mFila.close();
        }return listaTarea;
    }

     private void setValues(TaskVO task){
         mCalendar = Calendar.getInstance();
         mformato = new SimpleDateFormat("dd/mm/yy");

         mRegistro.put(COLUMN_NAME,task.getname());
         mRegistro.put(COLUMN_DESCRIPTION,task.getdescription());
         mRegistro.put(COLUMN_CREATEDDATE,mformato.format(mCalendar.getTime()));
         mRegistro.put(COLUMN_DATE,task.gettaskDate());
         mRegistro.put(COLUMN_RECURRENCE,task.getRecurrence().getidRecurrence());
     }
    private ContentValues getValues(){
        return mRegistro;
    }




}
