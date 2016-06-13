package com.mobilyte.chartadvice.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mobilyte.chartadvice.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mohit on 4/6/16.
 */
public class SpinnerStockAdapter extends BaseAdapter {
    // intializing all necessary variables
    private List<String> itemlist;
    Context context;
    View v;

    //constructor  getting status and context
    public SpinnerStockAdapter(List<String> data, Context context) {
        itemlist = data;
        this.context = context;
        System.out.println(Arrays.asList(itemlist).toArray());

    }


    @Override
    public int getCount() {
        return itemlist.size();
    }

    @Override
    public Object getItem(int position) {
        return itemlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    //setting layout
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(R.layout.spinner_row, parent, false);
        TextView label = (TextView) row.findViewById(R.id.company);
        label.setText(itemlist.get(position));
        return row;
    }
}
