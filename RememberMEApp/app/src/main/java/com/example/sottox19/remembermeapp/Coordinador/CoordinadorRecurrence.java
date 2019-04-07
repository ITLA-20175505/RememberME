package com.example.sottox19.remembermeapp.Coordinador;

import com.example.sottox19.remembermeapp.Logica.LogicaRecurrence;
import com.example.sottox19.remembermeapp.ModeloVO.RecurrenceVO;

import java.util.ArrayList;

public class CoordinadorRecurrence {
    private LogicaRecurrence mlogicaRecurrence;

    private LogicaRecurrence getLogica(){
        return mlogicaRecurrence;
    }
    public void SetLogica(LogicaRecurrence logicaRecurrence){
        this.mlogicaRecurrence = logicaRecurrence;
    }
    public void addRecurrence(RecurrenceVO recurrence){
        mlogicaRecurrence.validarAddRecurrence(recurrence);
    }
    public void deleteRecurrence(int id){
        mlogicaRecurrence.validarDeleteRecurrence(id);
    }
    public ArrayList<RecurrenceVO> listaRecurrence(){
        return mlogicaRecurrence.validarListRecurrence();
    }
    public RecurrenceVO buscarRecurrence(int id){
        return mlogicaRecurrence.validarBuscarRecurrence(id);
    }
}
