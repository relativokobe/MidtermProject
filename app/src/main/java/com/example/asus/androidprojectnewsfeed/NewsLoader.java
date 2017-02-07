package com.example.asus.androidprojectnewsfeed;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 06/02/2017.
 */

public class NewsLoader extends AsyncTaskLoader<ArrayList<Articles>> {
    String url;

    public NewsLoader(Context context, String url) {
        super(context);
        this.url = url;
        Log.e("kobe","url "+url);
    }
    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<Articles> loadInBackground() {
        Log.e("kobe","size asdsds= ");
        if(url == null){
            return null;
        }

        ArrayList<Articles> news = QueryUtils.fetchData(url);

        return news;
    }
}
