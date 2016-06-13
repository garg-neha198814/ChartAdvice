package com.mobilyte.chartadvice.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.mobilyte.chartadvice.Modal.ArchivesFilter_Data;
import com.mobilyte.chartadvice.R;

import java.util.ArrayList;

/**
 * Created by mohit on 3/6/16.
 */
public class ArchivesResult_Adapter extends RecyclerView.Adapter<ArchivesResult_Adapter.ViewHolder> {
    // intializing all necessary variables
    private ArrayList<ArchivesFilter_Data> itemlist;
    Context context;

    //constructor  getting status and context
    public ArchivesResult_Adapter(ArrayList<ArchivesFilter_Data> data, Context context) {
        this.itemlist = data;
        this.context = context;

    }
    //setting layout
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.archives_cardview, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    //setting data to layout
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTopic.setText(itemlist.get(position).getAnalyst());
        holder.mMessage.setText(itemlist.get(position).getMessage());
        holder.mStock.setText(itemlist.get(position).getStock());
        holder.mDate.setText(itemlist.get(position).getDate());
        holder.mTime.setText(itemlist.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }
    //setting variable to layout
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTopic;
        private TextView mMessage;
        private TextView mDate;
        private TextView mTime;
        private TextView mStock;
        public ViewHolder(View itemView) {
            super(itemView);
            mTopic = (TextView) itemView.findViewById(R.id.text_title);
            mMessage = (TextView) itemView.findViewById(R.id.text_description);
            mDate = (TextView) itemView.findViewById(R.id.text_stock);
            mTime = (TextView) itemView.findViewById(R.id.text_date);
            mStock = (TextView) itemView.findViewById(R.id.text_time);

        }
    }
}