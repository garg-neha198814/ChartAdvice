package com.mobilyte.chartadvice.Modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohit on 4/6/16.
 */
public class ArchivesData {

    @SerializedName("stocks_list")
    @Expose
    private List<String> stocksList = new ArrayList<String>();
    @SerializedName("analysts_list")
    @Expose
    private List<String> analystsList = new ArrayList<String>();
    @SerializedName("views_list")
    @Expose
    private List<String> viewsList = new ArrayList<String>();

//    @SerializedName("stocks_list")
//    @Expose

    /**
     *
     * @return
     * The stocksList
     */
    public List<String> getStocksList() {
        return stocksList;
    }

    /**
     *
     * @param stocksList
     * The stocks_list
     */
    public void setStocksList(List<String> stocksList) {
        this.stocksList = stocksList;
    }

    /**
     *
     * @return
     * The analystsList
     */
    public List<String> getAnalystsList() {
        return analystsList;
    }

    /**
     *
     * @param analystsList
     * The analysts_list
     */
    public void setAnalystsList(List<String> analystsList) {
        this.analystsList = analystsList;
    }

    /**
     *
     * @return
     * The viewsList
     */
    public List<String> getViewsList() {
        return viewsList;
    }

    /**
     *
     * @param viewsList
     * The views_list
     */
    public void setViewsList(List<String> viewsList) {
        this.viewsList = viewsList;
    }
}
