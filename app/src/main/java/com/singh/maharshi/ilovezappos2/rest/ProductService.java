package com.singh.maharshi.ilovezappos2.rest;

/**
 * Created by Maharshi on 2/3/17.
 */

import com.singh.maharshi.ilovezappos2.models.Products;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductService {
    @GET("/Search?key=b743e26728e16b81da139182bb2094357c31d331")
    Call<Products> getProducts(@Query("term") String searchTerm);
}
