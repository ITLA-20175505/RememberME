package com.example.user.remember_me;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;


@RequiresApi(api = Build.VERSION_CODES.N)
public class Nueva_Tarea_Fragment extends Fragment {

    Spinner nt_spinner;
    Spinner est_spinner;

    @SuppressLint("WrongViewCast")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_nueva__tarea_, container, false);



        //Llenando el nt_spinner del array en el archivo strings
        String[] frecuencia;
        nt_spinner = (Spinner) view.findViewById(R.id.nt_spinner);
        frecuencia = getResources().getStringArray(R.array.nt_spinner_array);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, frecuencia);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nt_spinner.setAdapter(arrayAdapter);

        //Llenando el est_spinner del array en el archivo strings
        String[] estandar;
        est_spinner = (Spinner) view.findViewById(R.id.est_spinner);
        estandar = getResources().getStringArray(R.array.est_spinner_array);
        ArrayAdapter<String> array_Adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, estandar);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        est_spinner.setAdapter(array_Adapter);







        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    }




