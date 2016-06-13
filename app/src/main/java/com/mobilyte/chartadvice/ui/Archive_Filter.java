package com.mobilyte.chartadvice.ui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mobilyte.chartadvice.Adapter.SpinnerStockAdapter;
import com.mobilyte.chartadvice.Api.ApiHandler;
import com.mobilyte.chartadvice.Api.ApiResponse;
import com.mobilyte.chartadvice.Api.Apis;
import com.mobilyte.chartadvice.Modal.ArchivesBean;
import com.mobilyte.chartadvice.R;
import com.mobilyte.chartadvice.interfaces.RetryInterface;
import com.mobilyte.chartadvice.ui.classes.BaseActivity;
import com.mobilyte.chartadvice.utility.Preferences;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class Archive_Filter extends BaseActivity implements ApiResponse, AdapterView.OnItemSelectedListener, View.OnClickListener, RetryInterface {

    // all necessary variables
    private Spinner spinnerstock;
    private Spinner spinneranalyst;
    private Spinner spinnerView;
    private SpinnerStockAdapter adapterStockList, adapterAnalyst, adapterView;
    private String stockdata, analystdata, viewdata, mFirstDate, mSecondDate;
    private ImageButton firstdate, seconddate;
    private EditText F_date, S_date;
    private int mYear, mMonth, mDay;
    private Button mSubmit;
    private Toolbar mToolbar;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive__fragment);

        //intializing all necessary variables
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView back = (ImageView) mToolbar.findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        spinnerstock = (Spinner) findViewById(R.id.spinnerstock);
        spinneranalyst = (Spinner) findViewById(R.id.spinneranalyst);
        spinnerView = (Spinner) findViewById(R.id.spinnerview);
       /* firstdate = (ImageButton) findViewById(R.id.firstdate);
        seconddate = (ImageButton) findViewById(R.id.secondate);*/
        F_date = (EditText) findViewById(R.id.from_date);
        S_date = (EditText) findViewById(R.id.to_date);
        mSubmit = (Button) findViewById(R.id.button_send);
        mSubmit.setOnClickListener(this);

        //setting touch listmer on buttons
        spinnerstock.setOnItemSelectedListener(this);
        spinneranalyst.setOnItemSelectedListener(this);
        spinnerView.setOnItemSelectedListener(this);
        F_date.setOnClickListener(this);
        S_date.setOnClickListener(this);
        /*firstdate.setOnClickListener(this);
        seconddate.setOnClickListener(this);*/

        loadData();
    }

    private void loadData() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("user_id", Preferences.getInstance(this).getUserId());
        hashMap.put("product_name", getString(R.string.Traders_center));
        hashMap.put("token", getString(R.string.token_String));
        showDialog();
        ApiHandler.getInstance(this).GetData(Apis.trader_center_archives, this, 1, hashMap, Apis.trader_center_archives_tag);
    }

    // on success of api
    @Override
    public void onSuccess(String response, String tag) {
        hideDialog();
        if (tag.equalsIgnoreCase(Apis.trader_center_archives_tag)) {
            Gson gson = new Gson();
            ArchivesBean dataBean = gson.fromJson(response, ArchivesBean.class);

            //setting spinner api
            if (dataBean.getStatus() == 1) {
                adapterStockList = new SpinnerStockAdapter(dataBean.getData().get(0).getStocksList(), this);
                spinnerstock.setAdapter(adapterStockList);

                adapterAnalyst = new SpinnerStockAdapter(dataBean.getData().get(0).getAnalystsList(), this);
                spinneranalyst.setAdapter(adapterAnalyst);

                adapterView = new SpinnerStockAdapter(dataBean.getData().get(0).getViewsList(), this);
                spinnerView.setAdapter(adapterView);
            } else {
                snackBarMessage(findViewById(R.id.main), response.toString());
            }
        }
    }

    // on failure of api
    @Override
    public void onFailure(Throwable t, String tag) {
        hideDialog();
        if (tag.equalsIgnoreCase(Apis.trader_center_archives_tag)) {
            snackBarRetry(findViewById(R.id.main), tag, this);
        }
    }

    //setting spinner on item click methord
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinnerstock:
                stockdata = ((TextView) view.findViewById(R.id.company)).getText().toString();
                break;
            case R.id.spinneranalyst:
                analystdata = ((TextView) view.findViewById(R.id.company)).getText().toString();
                break;
            case R.id.spinnerview:
                viewdata = ((TextView) view.findViewById(R.id.company)).getText().toString();
                break;

        }
    }

    //setting spinner onNothingSelected methord
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //setting date picket for firstdate
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.from_date) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dpd = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            String formattedsSDate = String.format(Locale.ENGLISH, "%d-%02d-%02d", year, (monthOfYear + 1), dayOfMonth);
                            F_date.setText(formattedsSDate);
                            mFirstDate = F_date.getText().toString();
                        }
                    }, mYear, mMonth, mDay);
            dpd.show();
        }
        //setting date picket for Seconddate

        if (id== R.id.to_date) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dpd = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            String formattedDate = String.format(Locale.ENGLISH, "%d-%02d-%02d", year, (monthOfYear + 1), dayOfMonth);
                            S_date.setText(formattedDate);
                            mSecondDate = S_date.getText().toString();
                        }
                    }, mYear, mMonth, mDay);
            dpd.show();
        }

        //submit button click
        if (v == mSubmit) {
            if (TextUtils.isEmpty(mFirstDate) || TextUtils.isEmpty(mSecondDate)) {
                snackBarMessage(findViewById(R.id.main), "Please select the date");
            } else {
                Intent filter = new Intent(this, Archives_Result.class);
                filter.putExtra("StockData", stockdata);
                filter.putExtra("ViewData", viewdata);
                filter.putExtra("AnalystData", analystdata);
                filter.putExtra("FirstDate", mFirstDate);
                filter.putExtra("SecondDate", mSecondDate);
                filter.putExtra("status", "0");
                startActivity(filter);
                finish();
            }
        }
    }
    @Override
    public void onRetry(String tag) {
        switch (tag) {
            case Apis.trader_center_archives_tag:
                loadData();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent traderCenter = new Intent(this, TradersCentral.class);
        startActivity(traderCenter);
        finish();
    }
}
