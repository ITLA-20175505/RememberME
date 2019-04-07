package com.example.sottox19.remembermeapp.ModeloDAO.Interface;

import com.example.sottox19.remembermeapp.ModeloVO.RecurrenceVO;
import com.example.sottox19.remembermeapp.ModeloVO.TaskVO;

import java.util.ArrayList;

public interface ITaskDAO {
    // Buscar por id
     TaskVO fetchById(int id);
    // Buscar todos las tareas
     ArrayList<TaskVO> fetchAllTask();
    // add Recurrence
     boolean addTask(TaskVO task);
    // delete Recurrence
     int deleteTask(int id);

}
