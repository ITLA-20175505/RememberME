package com.example.user.remember_me;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.remember_me.Conexion.BaseDeDatos;
import com.example.user.remember_me.Coordinador.CoordinadorRecurrence;
import com.example.user.remember_me.Coordinador.CoordinadorTask;
import com.example.user.remember_me.Logica.LogicaRecurrence;
import com.example.user.remember_me.Logica.LogicaTask;
import com.example.user.remember_me.ModeloVO.RecurrenceVO;
import com.example.user.remember_me.ModeloVO.TaskVO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import static java.util.Calendar.YEAR;


@RequiresApi(api = Build.VERSION_CODES.N)
public class Nueva_Tarea_Fragment extends Fragment {

    private Spinner mnt_spinner;
    private Spinner mest_spinner;
    private EditText mtxtNombreTarea;
    private EditText mtxtNota;
    private TextView view_fecha;
    private Button mbtnSave;
    private LinearLayout mHorainicial;
    private LinearLayout mHoraFinal;
    private Switch mswitchDia;

    private CoordinadorTask mcoordTask;
    private LogicaTask mlogicaTask;
    private  ImageButton btnFecha;
    private TaskVO mtask;
    private ArrayList<RecurrenceVO> mlistaRecurrence;
    private CoordinadorRecurrence mcoordRecurrence;
    private LogicaRecurrence mlogicaRecurrence;
    private RecurrenceVO mrecurrence;
    private BaseDeDatos mDb;
    private ArrayList<TaskVO> mlistaTask;

    private int day, month,year;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_nueva__tarea_, container, false);

        mtxtNombreTarea = (EditText) view.findViewById(R.id.nom_tarea);
        mtxtNota = (EditText) view.findViewById(R.id.txt_nota);
        mbtnSave = view.findViewById(R.id.btn_guardar);
        mHoraFinal = (LinearLayout) view.findViewById(R.id.horafinal);
        mHorainicial = (LinearLayout) view.findViewById(R.id.horainicial);
        mswitchDia = (Switch) view.findViewById(R.id.switch2);

        mswitchDia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    mHoraFinal.setVisibility(View.INVISIBLE);
                    mHorainicial.setVisibility(View.INVISIBLE);
                }else{
                    mHoraFinal.setVisibility(View.VISIBLE);
                    mHorainicial.setVisibility(View.VISIBLE);

                }
            }
        });

        // Set Logica y Coordinador de Task
        mcoordTask = new CoordinadorTask();
        mlogicaTask = new LogicaTask();
        mcoordTask.setLogica(mlogicaTask);
        mlogicaTask.setCoordinador(mcoordTask);
        // Set Logica y Coordinador de Recurrence
        mcoordRecurrence = new CoordinadorRecurrence();
        mlogicaRecurrence = new LogicaRecurrence();
        mlogicaRecurrence.setCoordinador(mcoordRecurrence);
        mcoordRecurrence.setLogica(mlogicaRecurrence);
        //Abrir base de Datos
        mDb = new BaseDeDatos(getContext());


        mlistaRecurrence = mcoordRecurrence.listaRecurrence();
        ArrayList<String> frecuencia = new ArrayList<String>();
        for(int i=0;i<mlistaRecurrence.size();i++){
            frecuencia.add(mlistaRecurrence.get(i).getname());
        }
        // Llenar el spinner de frecuencia con la clase Recurrence
        mnt_spinner = (Spinner) view.findViewById(R.id.nt_spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1,frecuencia );
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mnt_spinner.setAdapter(arrayAdapter);

        //Llenando el est_spinner del array en el archivo strings
        String[] estandar;
        mest_spinner = (Spinner) view.findViewById(R.id.est_spinner);
        estandar = getResources().getStringArray(R.array.est_spinner_array);
        ArrayAdapter<String> array_Adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, estandar);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mest_spinner.setAdapter(array_Adapter);
    mbtnSave.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mtask = new TaskVO();
            mrecurrence = mcoordRecurrence.buscarRecurrence((int)mnt_spinner.getSelectedItemId() + 1);
            mtask.setname(mtxtNombreTarea.getText().toString());
            mtask.setdescription(mtxtNota.getText().toString());
            mtask.setRecurrence(mrecurrence);
            mtask.settaskDate("1/04/19");
            Toast.makeText(getActivity(),"Hola",Toast.LENGTH_LONG);
            mcoordTask.addTask(mtask,getContext());
            mtxtNombreTarea.setText("");
            mtxtNota.setText("");
        }
    });



return view;
    }



    }





