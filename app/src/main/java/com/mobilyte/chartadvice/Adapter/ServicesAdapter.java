package com.mobilyte.chartadvice.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobilyte.chartadvice.Modal.DataServices;
import com.mobilyte.chartadvice.Modal.ServiceModalData;
import com.mobilyte.chartadvice.R;
import com.mobilyte.chartadvice.ui.NiftyEdge;
import com.mobilyte.chartadvice.ui.TradersCentral;
import com.mobilyte.chartadvice.ui.ServicesActivity;
import com.mobilyte.chartadvice.utility.Preferences;

/**
 * Created by root on 21/5/16.
 */
public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> {

    // intializing all necessary variables
    private ServiceModalData itemlist;
    ServicesActivity context;
    View v;
    //background images
    int icon[] = {R.drawable.equity_gain, R.drawable.future_gain, R.drawable.nifty_edge, R.drawable.option_gain, R.drawable.trader_central};
    int background[] = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h};

    //constructor  getting status and context
    public ServicesAdapter(ServiceModalData products, ServicesActivity context) {
        this.itemlist = products;
        this.context = context;
        Preferences.getInstance(context).storequery(0);
    }

    //setting layout
    @Override
    public ServicesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        v = inflater.inflate(R.layout.services_recycleview_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //setting data to layout
    @Override
    public void onBindViewHolder(final ServicesAdapter.ViewHolder holder, final int position) {
        final DataServices data = itemlist.getData().get(position);

        holder.bg.setImageResource(background[position % 4]);
        holder.mProduct.setText(itemlist.getData().get(position).getProduct());
        if (itemlist.getData().get(position).getProductId() == 3) {
            Preferences.getInstance(context).storequery(3);
        }
        else if (data.getProduct().equalsIgnoreCase("equity gains")) {
            holder.image.setImageResource(icon[0]);

        } else if (data.getProduct().equalsIgnoreCase("future gains")) {
            holder.image.setImageResource(icon[1]);

        } else if (data.getProduct().equalsIgnoreCase("nifty edge")) {
            holder.image.setImageResource(icon[2]);

        } else if (data.getProduct().equalsIgnoreCase("traders central")) {
            holder.image.setImageResource(icon[4]);

        } else if (data.getProduct().equalsIgnoreCase("option gains")) {
            holder.image.setImageResource(icon[3]);

        }

        holder.Relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (data.getProduct().equalsIgnoreCase("equity gains")) {


                    } else if (data.getProduct().equalsIgnoreCase("future gains")) {


                    } else if (data.getProduct().equalsIgnoreCase("nifty edge")) {
                        Intent niftyedge = new Intent(context, NiftyEdge.class);
                        context.startActivity(niftyedge);
                        context.finish();
                    } else if (data.getProduct().equalsIgnoreCase("traders central")) {
                        Intent traderscenter = new Intent(context, TradersCentral.class);
                        context.startActivity(traderscenter);
                        context.finish();
                    } else if (data.getProduct().equalsIgnoreCase("option gains")) {


                    }
                    else if (data.getProduct().equalsIgnoreCase("money control")) {


                    }
                    else if (data.getProduct().equalsIgnoreCase("Shcil")) {


                    }
                    else if (data.getProduct().equalsIgnoreCase("Query rooms")) {


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        System.out.println(">>>>>>>>>>>>" + itemlist.getData().size());
        return itemlist.getData().size();

    }

    //setting variable to layout
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mProduct;
        private RelativeLayout Relative;
        private ImageView image, bg;


        public ViewHolder(View itemView) {
            super(itemView);

            mProduct = (TextView) itemView.findViewById(R.id.textView_service);
            Relative = (RelativeLayout) itemView.findViewById(R.id.background);
            image = (ImageView) itemView.findViewById(R.id.imageView_service);
            bg = (ImageView) itemView.findViewById(R.id.imageView_back);

        }


    }
}
