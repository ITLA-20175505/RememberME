package com.example.sottox19.remembermeapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.example.sottox19.remembermeapp.Conexion.BaseDeDatos;
import com.example.sottox19.remembermeapp.Coordinador.CoordinadorRecurrence;
import com.example.sottox19.remembermeapp.Coordinador.CoordinadorTask;
import com.example.sottox19.remembermeapp.Logica.LogicaRecurrence;
import com.example.sottox19.remembermeapp.Logica.LogicaTask;
import com.example.sottox19.remembermeapp.ModeloVO.RecurrenceVO;
import com.example.sottox19.remembermeapp.ModeloVO.TaskVO;

import java.util.ArrayList;

public class test extends AppCompatActivity {

    public static final String TAG = test.class.getSimpleName();
    public static BaseDeDatos mdb;
    private RecurrenceVO mrecurrence;
    private LogicaRecurrence mLogicaRecurrence;
    private CoordinadorRecurrence mCoordRecurrence;
    private ArrayList<RecurrenceVO> mListaRecurrence;
    private TaskVO mTask;
    private LogicaTask mLogicaTask;
    private CoordinadorTask mCoordTask;
    private ArrayList<TaskVO> mlistaTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        /* Set Coordinador y Logica */
        mLogicaRecurrence = new LogicaRecurrence();
        mCoordRecurrence = new CoordinadorRecurrence();
        mLogicaRecurrence.setCoordinador(mCoordRecurrence);
        mCoordRecurrence.SetLogica(mLogicaRecurrence);
        /* Declarar Base de Datos*/
        mdb = new BaseDeDatos(this);
        mdb.open();
        mrecurrence = new RecurrenceVO();
        mrecurrence.setname("HOLA freddy");
        mrecurrence.settype("nose");
        mrecurrence.setidRecurrence(1);

       /* mTask = new TaskVO();
        mTask.setname("Lavar");
        mTask.setdescription("klk");
        mTask.settaskDate("24/04/19");
        mTask.setRecurrence(mrecurrence);

        mCoordTask.addTask(mTask);
        Log.d("Database",mTask.getname());*/
    }



    }


