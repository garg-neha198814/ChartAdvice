package com.mobilyte.chartadvice.Modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neha.garg on 5/26/2016.
 */
public class TradersCenterBean {
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("data")
    @Expose
    private List<TradersCenterData> dataarray = new ArrayList<TradersCenterData>();
    @SerializedName("stock_list")
    @Expose
    private List<String> stockList = new ArrayList<String>();
    @SerializedName("analyst_list")
    @Expose
    private List<String> analystList = new ArrayList<String>();
    @SerializedName("views_list")
    @Expose
    private List<String> viewsList = new ArrayList<String>();
    @SerializedName("time_list")
    @Expose
    private List<String> timeList = new ArrayList<String>();

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<TradersCenterData> getData() {
        return dataarray;
    }

    public void setData(List<TradersCenterData> data) {
        this.dataarray = data;
    }

    public List<String> getStockList() {
        return stockList;
    }

    public void setStockList(List<String> stockList) {
        this.stockList = stockList;
    }

    public List<String> getAnalystList() {
        return analystList;
    }

    public void setAnalystList(List<String> analystList) {
        this.analystList = analystList;
    }

    public List<String> getViewsList() {
        return viewsList;
    }

    public void setViewsList(List<String> viewsList) {
        this.viewsList = viewsList;
    }

    public List<String> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<String> timeList) {
        this.timeList = timeList;
    }
}
