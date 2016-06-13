package com.mobilyte.chartadvice.Modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 21/5/16.
 */
public class LoginDataBean {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("data")
    @Expose
    private DataLogin datalogin;

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
     * The datalogin
     */
    public DataLogin getDataLogin() {
        return datalogin;
    }

    /**
     *
     * @param datalogin
     * The dataServices
     */
    public void setDataLogin(DataLogin datalogin) {
        this.datalogin = datalogin;
    }

}
