package com.example.user.remember_me;

import android.content.Context;
import android.net.Uri;
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

import com.example.user.remember_me.Coordinador.CoordinadorTask;
import com.example.user.remember_me.Logica.LogicaTask;
import com.example.user.remember_me.ModeloVO.TaskVO;

import java.util.ArrayList;
import java.util.List;

public class Proximas_tareas extends Fragment {
    private ListView mlistview;
    private ArrayList<TaskVO> mlistaTarea;
    private CoordinadorTask mcoordTask;
    private LogicaTask mlogicaTask;
    List<Pair> mPairs = new ArrayList<Pair>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tareas_realizadas_, container, false);
        // Declarar Logica y Coordinador
        mlogicaTask = new LogicaTask();
        mcoordTask = new CoordinadorTask();
        mlogicaTask.setCoordinador(mcoordTask);
        mcoordTask.setLogica(mlogicaTask);

        mlistview = (ListView) view.findViewById(R.id.list_tareas_real);

        mlistaTarea = mcoordTask.getNextTask();

        if (mlistaTarea.isEmpty()) {

            Pair pair = new Pair("No hay", "Proximas Tareas");
            mPairs.add(pair);
        }
        for (int i = 0; i < mlistaTarea.size(); i++) {
            Pair pair = new Pair(mlistaTarea.get(i).gettaskDate(),mlistaTarea.get(i).getname());
            mPairs.add(pair);
        }
        ArrayAdapter<Pair> arrayAdapter = new ArrayAdapter<Pair>(getActivity(), android.R.layout.simple_list_item_1,
                mPairs);
        mlistview.setAdapter(arrayAdapter);
        return view;
    }
        @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
