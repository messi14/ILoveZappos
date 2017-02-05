package com.singh.maharshi.ilovezappos2.viewmodel;

/**
 * Created by Maharshi on 2/3/17.
 */

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.singh.maharshi.ilovezappos2.BR;

public class ProductViewModel extends BaseObservable{
    private String name;
    private String price;
    private String thumbnail;
    private String originalPrice;
    private String off;
    private boolean exists;

    @Bindable
    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
        notifyPropertyChanged(BR.exists);
    }

    @Bindable
    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
        notifyPropertyChanged(BR.originalPrice);
    }

    @Bindable
    public String getOff() {
        return off;
    }

    public void setOff(String off) {
        this.off = off;
        notifyPropertyChanged(BR.off);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
        notifyPropertyChanged(BR.price);
    }

    @Bindable
    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        notifyPropertyChanged(BR.thumbnail);
    }

}
