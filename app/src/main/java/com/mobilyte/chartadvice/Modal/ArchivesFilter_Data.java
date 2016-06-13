package com.mobilyte.chartadvice.Modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohit Sood on 6/5/2016.
 */
public class ArchivesFilter_Data {
    @SerializedName("analyst")
    @Expose
    private String analyst;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("stock")
    @Expose
    private String stock;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;

    /**
     *
     * @return
     * The analyst
     */
    public String getAnalyst() {
        return analyst;
    }

    /**
     *
     * @param analyst
     * The analyst
     */
    public void setAnalyst(String analyst) {
        this.analyst = analyst;
    }

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     * The stock
     */
    public String getStock() {
        return stock;
    }

    /**
     *
     * @param stock
     * The stock
     */
    public void setStock(String stock) {
        this.stock = stock;
    }

    /**
     *
     * @return
     * The image
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     * The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     *
     * @return
     * The date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     * The time
     */
    public String getTime() {
        return time;
    }

    /**
     *
     * @param time
     * The time
     */
    public void setTime(String time) {
        this.time = time;
    }

}
