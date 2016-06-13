package com.mobilyte.chartadvice.Modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohit Sood on 6/5/2016.
 */
public class Archives_FilterBean {
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("data")
    @Expose
    private List<ArchivesFilter_Data> data = new ArrayList<ArchivesFilter_Data>();

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
    public List<ArchivesFilter_Data> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<ArchivesFilter_Data> data) {
        this.data = data;
    }

}
