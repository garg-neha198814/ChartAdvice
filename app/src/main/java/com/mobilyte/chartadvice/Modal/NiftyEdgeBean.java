package com.mobilyte.chartadvice.Modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by neha.garg on 5/26/2016.
 */
public class NiftyEdgeBean extends NiftyEdgeData {
    @SerializedName("data")
    @Expose
    ArrayList<NiftyEdgeData> data;

    @SerializedName("status")
    @Expose
    int status ;

    public ArrayList<NiftyEdgeData> getArrayList() {
        return data;
    }

    public void setArrayList(ArrayList<NiftyEdgeData> arrayList) {
        this.data = arrayList;
    }

    public int getStatus() {

        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
