package com.mobilyte.chartadvice.Modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 21/5/16.
 */
public class ServiceModalData {


    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("data")
    @Expose
    private List<DataServices> data = new ArrayList<DataServices>();

    /**
     *
     * @return
     * The status
     */
    public int getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The data
     */
    public List<DataServices> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<DataServices> data) {
        this.data = data;
    }
}
