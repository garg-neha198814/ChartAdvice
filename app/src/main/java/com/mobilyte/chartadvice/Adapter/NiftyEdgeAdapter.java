package com.mobilyte.chartadvice.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobilyte.chartadvice.CheckPermissions.ExpandableTextView;
import com.mobilyte.chartadvice.Modal.NiftyEdgeData;
import com.mobilyte.chartadvice.R;
import com.mobilyte.chartadvice.ui.NiftyEdge;

import java.util.ArrayList;

public class NiftyEdgeAdapter extends RecyclerView.Adapter<NiftyEdgeAdapter.ViewHolder> {
    // intializing all necessary variables
    private ArrayList<NiftyEdgeData> itemlist;
    public Context context;

    //constructor  getting status and context of nifty edge
    public NiftyEdgeAdapter(ArrayList<NiftyEdgeData> status, NiftyEdge niftyEdge) {
        this.itemlist = status;
        this.context = niftyEdge;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);//
        View v = inflater.inflate(R.layout.niftyedge_recycleview_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //setting data to layout
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final NiftyEdgeData data = itemlist.get(position);
        holder.expandableTextViewmmessage.setText(itemlist.get(position).getPost_content());
        holder.title.setText(itemlist.get(position).getPost_title());

    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }

    public void updatList(ArrayList<NiftyEdgeData> arrayList) {
        this.itemlist.addAll(this.itemlist.size(), arrayList);
        notifyDataSetChanged();
    }

    //setting variable to layout
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ExpandableTextView expandableTextViewmmessage;
        private TextView title;

        public ViewHolder(View itemView) {

            super(itemView);
            expandableTextViewmmessage = (ExpandableTextView) itemView.findViewById(R.id.text_description);
            title = (TextView) itemView.findViewById(R.id.text_title);


        }
    }
}
