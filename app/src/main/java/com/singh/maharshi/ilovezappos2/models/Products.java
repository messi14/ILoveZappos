package com.singh.maharshi.ilovezappos2.models;

/**
 * Created by Maharshi on 2/3/17.
 */

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Products {
    @SerializedName("results")
    private List<Product> productList;

    @SerializedName("originalTerm")
    private String term;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        System.out.println("originalTerm : " + term);
        this.term = term;
    }



    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}