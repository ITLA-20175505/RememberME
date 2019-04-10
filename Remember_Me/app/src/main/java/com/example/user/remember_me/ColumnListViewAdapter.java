package com.example.user.remember_me;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.user.remember_me.ModeloVO.TaskVO;

import java.util.ArrayList;

public class ColumnListViewAdapter extends ArrayAdapter<TaskVO> {
private LayoutInflater mInflater;
private ArrayList<TaskVO> mtask;
private int mViewResourceid;

public ColumnListViewAdapter(Context context,int textViewResourceId,ArrayList<TaskVO> tasks){
    super(context, textViewResourceId,tasks);
this.mtask = tasks;
mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
mViewResourceid = textViewResourceId;
}

public View view(int position, View convertView, ViewGroup parents) {
    convertView = mInflater.inflate(mViewResourceid, null);

    TaskVO task = mtask.get(position);
    if (task != null) {
        Log.d("dd", "");
    }return convertView;
}
}
