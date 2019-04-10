package com.example.user.remember_me.ModeloDAO;

import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.util.Log;
import com.example.user.remember_me.CRUD.ABCRUD;
import com.example.user.remember_me.ModeloDAO.Interface.ITaskDAO;
import com.example.user.remember_me.ModeloVO.RecurrenceVO;
import com.example.user.remember_me.ModeloVO.TaskVO;
import com.example.user.remember_me.TableScheme.ISchemaTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TaskDAO extends ABCRUD implements ITaskDAO,ISchemaTask {
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
        TaskVO task = new TaskVO();
        RecurrenceVO recurrence = new RecurrenceVO();
        int idTaskindex = 0;
        int idNameIndex = 0;
        int idDescriptionIndex = 0;
        int taskDateIndex = 0;
        int nextDateIndex = 0;
        int recurrenceIndex = 0;
        int isCancelledIndex = 0;
        int isDoneIndex = 0;
        if(mFila!=null) {
            if(mFila.getColumnIndex(COLUMN_IDTASK)!= -1){
                idTaskindex = mFila.getColumnIndexOrThrow(COLUMN_IDTASK);
                task.setidTask(mFila.getInt(idTaskindex));}

            if (mFila.getColumnIndex(COLUMN_NAME)!=-1){
                idNameIndex = mFila.getColumnIndexOrThrow(COLUMN_NAME);
                task.setname(mFila.getString(idNameIndex));
            }
            if (mFila.getColumnIndex(COLUMN_DESCRIPTION)!= -1){
                idDescriptionIndex = mFila.getColumnIndexOrThrow(COLUMN_DESCRIPTION);
                task.setdescription(mFila.getString(idDescriptionIndex));
            }
        }
        return task;
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
    public int updateTask(ArrayList<TaskVO> listaTask) {
        int u = 0;
        for (int i=0;i<listaTask.size();i++){
            setValues(listaTask.get(i));
            final String [] selectionArgs = {String.valueOf(listaTask.get(i).getidTask()),String.valueOf(0)};
            final String selection = COLUMN_IDTASK + " = ? AND " + COLUMN_ISCANCELLED +" = ?";
            try{
                super.update(taskTable,mRegistro,selection,selectionArgs);
                u++;
            }  catch (SQLiteConstraintException e){
                Log.d("Database",e.getMessage());
                return u;
            }}return u;}
    @Override
    public TaskVO fetchById(int id) {
        final String selectionArgs[]   = {String.valueOf(id),String.valueOf(0)};
        final String selection = COLUMN_IDTASK + " = ? AND " + COLUMN_ISCANCELLED +" = ?";
            mFila =super.consulta(taskTable,COLUMNTASK,selection,selectionArgs,COLUMN_IDTASK);
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
        mFila = super.consulta(taskTable,COLUMNTASK,selection,selectionArgs,COLUMN_IDTASK);
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
         getNextDate(task);
         mRegistro.put(COLUMN_NAME,task.getname());
         mRegistro.put(COLUMN_DESCRIPTION,task.getdescription());
         mRegistro.put(COLUMN_CREATEDDATE,mformato.format(mCalendar.getTime()));
         mRegistro.put(COLUMN_DATE,task.gettaskDate());
         mRegistro.put(COLUMN_RECURRENCE,task.getRecurrence().getidRecurrence());
         mRegistro.put(COLUMN_NEXTDATE,task.getnextDate());
         mRegistro.put(COLUMN_DONE,0);
     }
    private ContentValues getValues(){
        return mRegistro;
    }
    private void getNextDate(TaskVO task){
        try {
            mCalendar.setTime(mformato.parse(task.gettaskDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        switch(task.getRecurrence().getidRecurrence()) {
            case 1:
                mCalendar.add(Calendar.DAY_OF_MONTH, task.getRecurrence().getinterval());
                break;
            case 2:
                mCalendar.add(Calendar.DAY_OF_MONTH,  task.getRecurrence().getinterval());
                break;
            case 3:
                mCalendar.add(Calendar.WEEK_OF_MONTH,  task.getRecurrence().getinterval());
                break;
            case 4:
                mCalendar.add(Calendar.WEEK_OF_MONTH,  task.getRecurrence().getinterval());
                break;
            case 5:
                mCalendar.add(Calendar.MONTH,  task.getRecurrence().getinterval());
                break;
        }
        task.setnextDate(mformato.format(mCalendar.getTime()));
    }



}
