package com.singh.maharshi.ilovezappos2.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.singh.maharshi.ilovezappos2.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Maharshi on 2/4/17.
 */

public class DownloadUtil {
    @BindingAdapter({"bind:imageUrl"})
    public static void downloadImage(ImageView view, String url) {
        if (url == null || url.isEmpty())
            view.setImageResource(R.mipmap.ic_launcher);
        else {
            Picasso.with(view.getContext()).load(url).error(R.mipmap.ic_launcher).into(view);
        }
    }
}