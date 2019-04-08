package com.example.user.remember_me.Coordinador;
import android.util.Log;


import com.example.user.remember_me.Logica.LogicaTask;
import com.example.user.remember_me.ModeloVO.TaskVO;

import java.util.ArrayList;

public class CoordinadorTask {
    private LogicaTask mLogicaTask;

    private LogicaTask getLogica(){
        return mLogicaTask;
    }
    public void setLogica(LogicaTask logicaTask){
        this.mLogicaTask = logicaTask;
    }
    public void addTask(TaskVO task){
        mLogicaTask.validarAddTask(task);
    }
    public void deleteTask(int id){
        mLogicaTask.validarDeleteTask(id);
    }
    public TaskVO buscarTask(int id){
        return mLogicaTask.validarBuscarTask(id);
    }
    public ArrayList<TaskVO> listaTask(){
        return mLogicaTask.validarListaTask();
    }
}
