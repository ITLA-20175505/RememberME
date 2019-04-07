package com.example.sottox19.remembermeapp;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.sottox19.remembermeapp.Conexion.Database;
import com.example.sottox19.remembermeapp.ModeloVO.RecurrenceVO;

import java.util.ArrayList;

public class test extends AppCompatActivity {

    public static final String TAG = test.class.getSimpleName();
    public static Database mdb;
    private RecurrenceVO recurrence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mdb = new Database(this);
    mdb.open();
    addUser();
    }

        private void addUser(){
            recurrence = new RecurrenceVO();
            recurrence.setMidRecurrence(1);
            recurrence.setMname("Diaria");
            recurrence.setMdescription("nada");
            recurrence.setMtype("Dia");
            boolean isSaved = Database.mRecurrenceDAO.addRecurrence(recurrence);
            System.out.println(recurrence.getMname());
            if (isSaved == true) {
                Log.d("agregado", recurrence.getMname());
            }else{
                Log.d("bueno",recurrence.getMname());
            }
            listUser();


        }
        private void listUser(){
            ArrayList<RecurrenceVO> listado = Database.mRecurrenceDAO.fetchAllRecurrence();
            for (int i = 0;i<listado.size();i++){
                System.out.println(listado.get(i).getMname());
                Log.d("listado",listado.get(i).getMname());
            }
        }

    }


