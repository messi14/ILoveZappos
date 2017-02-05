package com.singh.maharshi.ilovezappos2.models;

/**
 * Created by Maharshi on 2/3/17.
 */

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("productName")
    private String name;

    @SerializedName("brandName")
    private String brand;

    @SerializedName("thumbnailImageUrl")
    private String thumbnail;

    @SerializedName("productId")
    private String id;

    @SerializedName("originalPrice")
    private String originalPrice;

    @SerializedName("styleId")
    private String sid;

    @SerializedName("colorId")
    private String cid;

    @SerializedName("price")
    private String price;

    @SerializedName("percentOff")
    private String off;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOff() {
        return off;
    }

    public void setOff(String off) {
        this.off = off;
    }
}
