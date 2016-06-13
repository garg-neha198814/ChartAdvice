package com.mobilyte.chartadvice.Modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 21/5/16.
 */
public class DataServices {
    @SerializedName("product")
    @Expose
    private String product;
    @SerializedName("product_id")
    @Expose
    private int productId;

    /**
     *
     * @return
     * The product
     */
    public String getProduct() {
        return product;
    }

    /**
     *
     * @param product
     * The product
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     *
     * @return
     * The productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     *
     * @param productId
     * The product_id
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }
}
