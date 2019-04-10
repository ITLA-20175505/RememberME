package com.example.user.remember_me.Logica;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;
import android.widget.Toast;


import com.example.user.remember_me.Conexion.BaseDeDatos;
import com.example.user.remember_me.Coordinador.CoordinadorTask;
import com.example.user.remember_me.ModeloVO.TaskVO;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class LogicaTask {
    private CoordinadorTask mCoordTask;
    private TaskVO mTask;
    private ArrayList<TaskVO> listaTask;
public void setCoordinador(CoordinadorTask coordTask){
    this.mCoordTask = coordTask;
}

public void validarAddTask(TaskVO task, Context context) {

    boolean isSaved = BaseDeDatos.mTaskDAO.addTask(task);

    if (isSaved == true) {
        Toast.makeText(context,"Tarea Registrada con exito",Toast.LENGTH_SHORT);
        Log.d("Database", "Registro de la tabla Task Anadido");
    }else{
        Toast.makeText(context,"No se pudo registrar la tarea",Toast.LENGTH_LONG);
    }

}

public void validarDeleteTask(int id){
        int isDeleted = BaseDeDatos.mTaskDAO.deleteTask(id);
    if(isDeleted !=0){
        Log.d("Database","Se eliminaron " + isDeleted + " registros de la tabla task");
    }else{
        Log.d("Database","No se encontraron registros en la tabla Task");
    }
}
public void validarUpdateTask(ArrayList<TaskVO> listaTask){
    int isUpdated = BaseDeDatos.mTaskDAO.updateTask(listaTask);
    if(isUpdated !=0){
        Log.d("Database","Se actualizaron " + isUpdated + " registros");
    }}
    public void validarsetDoneTask(ArrayList<TaskVO> listaTask){
      int isSetDone = BaseDeDatos.mTaskDAO.setDoneTask(listaTask);
      if (isSetDone !=0){
          Log.d("Database","Se marcaron " + isSetDone + " tareas realizadas");
    }
}
public TaskVO validarBuscarTask(int id){
    mTask = BaseDeDatos.mTaskDAO.fetchById(id);
    if(mTask!= null){
        Log.d("Database","Se encontro el registro "+ id+ " en la tabla Task");
        return mTask;
    } else {
        Log.d("Database","No se encontro el registro " + id + " en la tabla Task");
        return null;
    }
}
public ArrayList<TaskVO> validarGetDoneTask(){
    listaTask = BaseDeDatos.mTaskDAO.getDoneTask();
    if (listaTask != null){
        Log.d("Database","Lista De Tareas Realizadas creada");
    }return listaTask;
}
public ArrayList<TaskVO> validarListaTask() {
    listaTask = BaseDeDatos.mTaskDAO.fetchAllTask();
    if (listaTask != null) {
        Log.d("Database", "Lista Task creada");}
            return listaTask;
        }
    }
