package com.example.jose.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manjusha on 3/17/2015.
 */
public class MyAdapter extends ArrayAdapter<Details> {
    private final Context context;
    private final ArrayList<Details> dtlsArraylist;

    public MyAdapter(Context context,  ArrayList<Details> dtlsArraylist) {
        super(context, R.layout.row, dtlsArraylist);
        this.context=context;
        this.dtlsArraylist = dtlsArraylist;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflator = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflator.inflate(R.layout.row,parent,false);
        TextView title = (TextView)rowView.findViewById(R.id.txttitle);
        TextView descr = (TextView)rowView.findViewById(R.id.txtdesc);
        TextView dates = (TextView)rowView.findViewById(R.id.txtdate);
        title.setText(dtlsArraylist.get(position).getDtitle());
        descr.setText(dtlsArraylist.get(position).getDdesc());
        dates.setText(dtlsArraylist.get(position).getDdate());
        return rowView;
    }
}
