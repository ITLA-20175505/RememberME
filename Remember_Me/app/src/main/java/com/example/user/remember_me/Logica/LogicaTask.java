package com.example.user.remember_me.Logica;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;


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

public void validarAddTask(TaskVO task) {

    boolean isSaved = BaseDeDatos.mTaskDAO.addTask(task);

    if (isSaved == true) {
        Log.d("Database", "Registro de la tabla Task Anadido");
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
public ArrayList<TaskVO> validarListaTask() {

    listaTask = BaseDeDatos.mTaskDAO.fetchAllTask();
    Log.d("Database", "Lista Task creada");
    return listaTask;
}
}



