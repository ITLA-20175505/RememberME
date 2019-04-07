package com.example.sottox19.remembermeapp.ModeloDAO.Interface;

import com.example.sottox19.remembermeapp.ModeloVO.RecurrenceVO;

import java.util.ArrayList;

public interface IRecurrenceDAO {
    // Buscar por id
    public RecurrenceVO fetchById(int id);
    // Buscar todos las recurrencias
    public ArrayList<RecurrenceVO> fetchAllRecurrence();
    // add Recurrence
    public boolean addRecurrence(RecurrenceVO recurrence);
    // delete Recurrence
    public boolean deleteRecurrence(RecurrenceVO recurrence);



}
