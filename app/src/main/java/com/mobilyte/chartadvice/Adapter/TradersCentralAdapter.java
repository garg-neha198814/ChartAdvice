package com.mobilyte.chartadvice.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobilyte.chartadvice.Modal.Querry_Bean;
import com.mobilyte.chartadvice.Modal.TradersCenterData;
import com.mobilyte.chartadvice.R;
import com.mobilyte.chartadvice.interfaces.GitHubClient;
import com.mobilyte.chartadvice.ui.TradersCentral;
import com.mobilyte.chartadvice.utility.Preferences;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by neha.garg on 5/26/2016.
 */
public class TradersCentralAdapter extends RecyclerView.Adapter<TradersCentralAdapter.ViewHolder> {

    // intializing all necessary variables

    private ArrayList<TradersCenterData> itemlist;
    private ArrayList<Querry_Bean> mStatus;
    Context context;
    private ImageButton query;
    public HashMap<String, String> hashMap;
    public String Data_url = "http://www.chartadvise.com/web-services/query.php";
    String url = "http://www.chartadvise.com/filess/";
    private AlertDialog dialog;
    private View dialogView;
    private int REQUEST_CODE = 100;
    private int RESULT_LOAD_IMAGE = 200;
    private ImageView Addimage;
    private TradersCentral tradersCentral;
    private String Imagepath = "";
    GitHubClient client;

    //constructor  getting status and context
    public TradersCentralAdapter(ArrayList<TradersCenterData> data, Context context, TradersCentral tradersCentral) {
        itemlist = data;
        this.context = context;
        this.tradersCentral = tradersCentral;

    }

    //setting data to layout
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.traders_center_cardview, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //setting data
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.manalyst.setText(itemlist.get(position).getAnalyst());
        holder.Viewmmessage.setText(itemlist.get(position).getMessage());
        holder.mtime.setText(itemlist.get(position).getTime());
        holder.mdate.setText(itemlist.get(position).getDate());
        holder.mstock.setText(itemlist.get(position).getStock());
        holder.mmessage_type.setText(itemlist.get(position).getView());
        Picasso.with(context).load(url + (itemlist.get(position).getImage().toString())).placeholder(R.drawable.placeholder).into(holder.mtradersimage);
        if (Preferences.getInstance(context).getQuery() == 3) {
            holder.query.setVisibility(View.VISIBLE);
        } else {
            holder.query.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }


    public void showImage(String base64, String path) {
        Imagepath = path.toString();
        byte b[] = Base64.decode(base64, Base64.DEFAULT);
        Addimage.setImageBitmap(BitmapFactory.decodeByteArray(b, 0, b.length));
    }

    //setting variable to layout
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView Viewmmessage;
        private TextView manalyst;
        private TextView mdate;
        private TextView mtime;
        private TextView mstock;
        private TextView mmessage_type;
        private ImageView mtradersimage;
        private ImageButton query;

        public ViewHolder(final View itemView) {
            super(itemView);
            Viewmmessage = (TextView) itemView.findViewById(R.id.text_description);
            manalyst = (TextView) itemView.findViewById(R.id.text_title);
            mtime = (TextView) itemView.findViewById(R.id.text_time);
            mdate = (TextView) itemView.findViewById(R.id.text_calender);
            mstock = (TextView) itemView.findViewById(R.id.stock_Title);
            mmessage_type = (TextView) itemView.findViewById(R.id.message_View);
            mtradersimage = (ImageView) itemView.findViewById(R.id.imagetrader);
            query = (ImageButton) itemView.findViewById(R.id.image_Button_query);
            query.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //setting Alert Dialog to layout
                    AlertDialog.Builder ab = new AlertDialog.Builder(context);
                    dialogView = LayoutInflater.from(context).inflate(R.layout.trader_central_dialog_layout, null);
                    ab.setView(dialogView);
                    TextView subject = (TextView) dialogView.findViewById(R.id.editText_subject);
                    Addimage = (ImageView) dialogView.findViewById(R.id.upload);
                    subject.setText(itemlist.get(getAdapterPosition()).getMessage().toString());
                    final EditText message = (EditText) dialogView.findViewById(R.id.editText_message);
                    Button sendbutton = (Button) dialogView.findViewById(R.id.button_send);
                    TextView mcountid = (TextView) dialogView.findViewById(R.id.countid);
                    // String count=Preferences.getInstance(context).getQuery_limit();
                    mcountid.setText(Preferences.getInstance(context).getQuery_limit() + "");
                    System.out.println(">>>>>>>>>>>> datta >>>>>>>> >> >> " + Preferences.getInstance(context).getQuery_limit());
                    Addimage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tradersCentral.openChooser();
                        }
                    });

                    sendbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String mMessage = message.getText().toString();
                            if (TextUtils.isEmpty(mMessage)) {
                                Toast.makeText(context, "Please enter your message", Toast.LENGTH_SHORT).show();
                            } else {
                                client = ClassClientmultipatrt.createService(GitHubClient.class);

                                hashMap = new HashMap<String, String>();
                                hashMap.put("query", mMessage);  // DEFAULT TOKEN
                                hashMap.put("user_id", Preferences.getInstance(context).getUserId());
                                hashMap.put("message_id", itemlist.get(getAdapterPosition()).getMessage_id().toString());
                                hashMap.put("token", context.getString(R.string.token_String));
                                hashMap.put("file", Imagepath);
                                tradersCentral.showDialog();
                                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), new File(Imagepath));
                                MultipartBody.Part body = MultipartBody.Part.createFormData("file", "user_profile.jpg", requestBody);
                                HashMap<String, RequestBody> bodyParam = new HashMap<>();
                                for (Map.Entry<String, String> obj : hashMap.entrySet()) {
                                    bodyParam.put(obj.getKey(), RequestBody.create(MediaType.parse("multipart/form-data"), obj.getValue()));
                                }
                                Call call = client.updateQuery(bodyParam, body);
                                call.enqueue(new Callback() {

                                    @Override
                                    public void onResponse(Call call, Response response) {
                                        dialog.dismiss();
                                        tradersCentral.hideDialog();
                                        if (response.isSuccessful()) {
                                            Querry_Bean driverObj = (Querry_Bean) response.body();
                                            System.out.println("driver>>" + driverObj.getMessage());
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call call, Throwable t) {
                                        dialog.dismiss();
                                        tradersCentral.hideDialog();
                                        {
                                            tradersCentral.snackBarMessage(tradersCentral.getString(R.string.internet_connection_error));
                                        }
                                    }
                                });
                            }
                        }
                    });
                    dialog = ab.create();
                    dialog.show();
                }
            });
        }
    }
}






