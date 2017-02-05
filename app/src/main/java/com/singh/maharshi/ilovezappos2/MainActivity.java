package com.singh.maharshi.ilovezappos2;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.singh.maharshi.ilovezappos2.databinding.ActivityMainBinding;
import com.singh.maharshi.ilovezappos2.models.Product;
import com.singh.maharshi.ilovezappos2.models.Products;
import com.singh.maharshi.ilovezappos2.rest.ProductService;
import com.singh.maharshi.ilovezappos2.viewmodel.ProductViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.color.white;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    private ActivityMainBinding binding;
    private ProductViewModel vm;
    private FloatingActionButton fab;
    private FloatingActionButton share;
    private FloatingActionButton favorite;
    boolean flag = true;
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setModel(vm = new ProductViewModel());
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.clayout);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getBaseContext(), "Floating action button clicked", Toast.LENGTH_LONG).show();
                AnimatorSet anim = new AnimatorSet();
                ObjectAnimator rotate = ObjectAnimator.ofFloat(fab, "rotation", 0.0f, 360.0f);
                rotate.setDuration(500);
                AnimatorSet anim2 = new AnimatorSet();
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(fab, "scaleX", 1.0f, 2.0f);
                scaleX.setDuration(500);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(fab, "scaleY", 1.0f, 2.0f);
                scaleY.setDuration(500);

                AnimatorSet anim3 = new AnimatorSet();
                ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(fab, "scaleX", 2.0f, 1.0f);
                scaleX.setDuration(500);
                ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(fab, "scaleY", 2.0f, 1.0f);
                scaleY.setDuration(500);

                anim.play(rotate).before(anim2);

                anim2.play(scaleX).with(scaleY).before(anim3);
                fab.setImageResource(R.drawable.cart_loaded);
                anim3.play(scaleDownX).with(scaleDownY);
                anim.start();
                fab.setClickable(false);
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Added to Cart!", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
        share = (FloatingActionButton) findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getBaseContext(), "Floating action button clicked", Toast.LENGTH_LONG).show();
            }
        });
        favorite = (FloatingActionButton) findViewById(R.id.favorite);

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag){
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Product added to favorites!", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    favorite.setImageResource(R.drawable.liked1);
                    favorite.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(white)));
                }else{
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Product removed from favorites!", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    favorite.setImageResource(R.drawable.like1);
                    favorite.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                }
                flag = !flag;
                //Toast.makeText(getBaseContext(), "Floating action button clicked", Toast.LENGTH_LONG).show();
            }
        });

        searchView=(SearchView) findViewById(R.id.searchView);
        searchView.setQueryHint("Search Product");
        searchView.setIconified(false);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(final String query) {
                fab.setImageResource(R.drawable.cart1);
                fab.setClickable(true);
                flag = true;
                favorite.setImageResource(R.drawable.like1);
                favorite.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                //Toast.makeText(getBaseContext(), query, Toast.LENGTH_LONG).show();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.zappos.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ProductService service = retrofit.create(ProductService.class);
                Call call = service.getProducts(query);
                call.enqueue(new Callback<Products>() {
                    @Override
                    public void onResponse(Call<Products> call, Response<Products> response) {
                        Products product = response.body();
                        if (product == null || product.getProductList().size() == 0){
                            vm.setName("Sorry, We could not find any product named " + query + ". Please try again!");
                            vm.setPrice("");
                            vm.setThumbnail("");
                            vm.setOff("");
                            vm.setOriginalPrice("");
                            vm.setExists(false);
                        }
                        else{
                            TextView tv = (TextView) findViewById(R.id.originalPrice);
                            tv.setPaintFlags(tv.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                            Product item = product.getProductList().get(0);
                            vm.setName(item.getBrand() + " " + item.getName());
                            vm.setPrice(item.getPrice());
                            System.out.print(item.getPrice());
                            vm.setThumbnail(item.getThumbnail());
                            vm.setOff(item.getOff() + " OFF");
                            vm.setOriginalPrice(item.getOriginalPrice());
                            vm.setExists(true);
                            if (!item.getOff().equals("0%")){
                                tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Products> call, Throwable t) {
                        vm.setName("Trouble connecting to the server. Please ensure that the Internet is working!");
                        vm.setPrice("");
                        vm.setThumbnail("");
                        vm.setOff("");
                        vm.setOriginalPrice("");
                        vm.setExists(false);
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Toast.makeText(getBaseContext(), newText, Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
}