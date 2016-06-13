package com.mobilyte.chartadvice.Modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohit on 4/6/16.
 */
public class ArchivesBean {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("data")
    @Expose
    private List<ArchivesData> data = new ArrayList<ArchivesData>();

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
    public List<ArchivesData> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<ArchivesData> data) {
        this.data = data;
    }
}

