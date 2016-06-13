package com.mobilyte.chartadvice.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobilyte.chartadvice.R;
import com.mobilyte.chartadvice.ui.TradersCenter_Filter;

import java.util.List;

/**
 * Created by mohit on 7/6/16.
 */
public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder> {
    private Context context;
    private TradersCenter_Filter filter;
    private List<String> list;
    private int type = -1;

    public FilterAdapter(Context context, TradersCenter_Filter filter, List<String> list, int type) {
        this.context = context;
        this.filter = filter;
        this.list = list;
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.filter_cardview, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mtital.setText(list.get(position).toString());
        if (type == 1) {
            if (filter.getStockCheckedPosition() == position) {
                holder.mcheckbox.setImageResource(R.drawable.ic_check);
            } else {
                holder.mcheckbox.setImageResource(R.drawable.ic_uncheck);
            }
        } else if (type == 2) {
            if (filter.getAnalystCheckedPosition() == position) {
                holder.mcheckbox.setImageResource(R.drawable.ic_check);
            } else {
                holder.mcheckbox.setImageResource(R.drawable.ic_uncheck);
            }
        } else {
            if (filter.getViewCheckedPosition() == position) {
                holder.mcheckbox.setImageResource(R.drawable.ic_check);
            } else {
                holder.mcheckbox.setImageResource(R.drawable.ic_uncheck);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mtital;
        private ImageView mcheckbox;

        public ViewHolder(View itemView) {
            super(itemView);
            mtital = (TextView) itemView.findViewById(R.id.tital);
            mcheckbox = (ImageView) itemView.findViewById(R.id.checkBox);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (type == 1) {
                        if (getAdapterPosition() == filter.getStockCheckedPosition()) {
                            // already checked, uncheck it
                            filter.setStockCheckedPosition(-1);
                        } else {
                            // its unchecked, check it
                            filter.setStockCheckedPosition(getAdapterPosition());
                        }
                    } else if (type == 2) {
                        if (getAdapterPosition() == filter.getAnalystCheckedPosition()) {
                            // already checked, uncheck it
                            filter.setAnalystCheckedPosition(-1);
                        } else {
                            // its unchecked, check it
                            filter.setAnalystCheckedPosition(getAdapterPosition());
                        }
                    } else if (type == 3) {
                        if (getAdapterPosition() == filter.getViewCheckedPosition()) {
                            // already checked, uncheck it
                            filter.setViewCheckedPosition(-1);
                        } else {
                            // its unchecked, check it
                            filter.setViewCheckedPosition(getAdapterPosition());
                        }
                    }
                    notifyDataSetChanged();
                }
            });
        }

    }
}
