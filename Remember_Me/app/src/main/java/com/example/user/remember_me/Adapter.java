package com.example.user.remember_me;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.user.remember_me.ModeloVO.TaskVO;
import com.example.user.remember_me.ModeloVO.UserVO;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<TaskVO> {

        public Adapter(Context context, ArrayList<TaskVO> users) {

            super(context, 0, users);

        }



        @Override

        public View getView(int position, View convertView, ViewGroup parent) {

            // Get the data item for this position

            TaskVO mtask = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view

            if (convertView == null) {

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_tareas_realizadas_, null);

            }

            // Lookup view for data population

         /*   TextView tvName = (TextView) convertView.findViewById(R.id.test1);

            TextView tvHome = (TextView) convertView.findViewById(R.id.test2);

            // Populate the data into the template view using the data object

            tvName.setText(mtask.getname());

            tvHome.setText(mtask.gettaskDate());*/

            // Return the completed view to render on screen

            return convertView;

        }

    }

