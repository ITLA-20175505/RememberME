package com.example.user.remember_me;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.user.remember_me.Conexion.BaseDeDatos;
import com.example.user.remember_me.Coordinador.CoordinadorRecurrence;
import com.example.user.remember_me.Coordinador.CoordinadorTask;
import com.example.user.remember_me.Logica.LogicaRecurrence;
import com.example.user.remember_me.Logica.LogicaTask;
import com.example.user.remember_me.ModeloVO.RecurrenceVO;
import com.example.user.remember_me.ModeloVO.TaskVO;

import java.util.ArrayList;

public class test extends AppCompatActivity {
    private CoordinadorRecurrence mCoordRecurrence;
    private LogicaRecurrence mlogicaRecurrence;
    private ArrayList<RecurrenceVO> mlistaRecurrence;
    private RecurrenceVO mrecurrence;
    private CoordinadorTask mcoordTask;
    private LogicaTask mlogicaTask;
    private ArrayList<TaskVO> listaTask;
    private TaskVO mtask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mrecurrence = new RecurrenceVO();
        mrecurrence.setname("KLK");
        mrecurrence.setdescription("Semanal");
        mrecurrence.setinterval(1);
        mrecurrence.settype("Semana");

        mtask = new TaskVO();
        mtask.setname("Lavar");
        mtask.setRecurrence(mrecurrence);

        mCoordRecurrence.addRecurrence(mrecurrence);
        Log.d("Database","ola");
    }

}
