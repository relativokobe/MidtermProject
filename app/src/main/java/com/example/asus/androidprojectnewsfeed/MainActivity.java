package com.example.asus.androidprojectnewsfeed;

import android.annotation.TargetApi;
import android.app.LoaderManager.LoaderCallbacks;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<ArrayList<Articles>> {

    private static final String USGS_REQUEST_URL =
            "https://newsapi.org/v1/articles?source=espn&sortBy=top&apiKey=b4d01380decb41e498766bf5ecf7310b";
    private NewsAdapter newsAdapter;
    private RecyclerView recyclerView;
    private static final int news_id = 1;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.list);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();


        if (networkInfo != null && networkInfo.isConnected()) {
            android.app.LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(news_id, null, this);

        } else {
            Toast.makeText(this, "No Connection", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public Loader<ArrayList<Articles>> onCreateLoader(int id, Bundle args) {
        Uri baseUri = Uri.parse(USGS_REQUEST_URL);
        Log.e("kobe","nisud sa Loadermethod");
        return new NewsLoader(this,baseUri.toString());
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Articles>> loader, ArrayList<Articles> data) {

        if(data!=null && !data.isEmpty()){
            newsAdapter = new NewsAdapter(this,data);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(newsAdapter);

        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Articles>> loader) {

    }
}
