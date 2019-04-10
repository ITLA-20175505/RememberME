package com.example.user.remember_me;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.user.remember_me.Coordinador.CoordinadorTask;
import com.example.user.remember_me.Logica.LogicaTask;
import com.example.user.remember_me.ModeloVO.TaskVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Tareas_realizadas_Fragment extends Fragment {
    private ListView mlistview;
    private ArrayList<TaskVO> mlistaTarea;
    private CoordinadorTask mcoordTask;
    private LogicaTask mlogicaTask;
    HashMap<String,String> listadoTareasRealizada;
    List<Pair> mPairs = new ArrayList<Pair>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View  view = inflater.inflate(R.layout.fragment_tareas_realizadas_, container, false);
      // Declarar Logica y Coordinador
      mlogicaTask = new LogicaTask();
      mcoordTask = new CoordinadorTask();
      mlogicaTask.setCoordinador(mcoordTask);
      mcoordTask.setLogica(mlogicaTask);

        mlistview = (ListView) view.findViewById(R.id.list_tareas_real);
        TaskVO task = new TaskVO();
        task.setidTask(1);
        task.setisDone(true);
        ArrayList<TaskVO> doneTask = new ArrayList<>();
        doneTask.add(task);
        mcoordTask.setDoneTask(doneTask);

        mlistaTarea = mcoordTask.getDoneTask();
        Log.d("Database",String.valueOf(mlistaTarea.size()));
        listadoTareasRealizada = new HashMap<String, String>();
        if (mlistaTarea.isEmpty()) {

            Pair pair = new Pair("No hay","Tareas Realizadas");
            mPairs.add(pair);}
        for (int i = 0;i<mlistaTarea.size();i++){
           Pair pair = new Pair(mlistaTarea.get(i).getname(),mlistaTarea.get(i).gettaskDate());
           mPairs.add(pair);
        }
        ArrayAdapter<Pair> arrayAdapter = new ArrayAdapter<Pair>(getActivity(),android.R.layout.simple_list_item_1,
             mPairs   );
        mlistview.setAdapter(arrayAdapter);



return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
