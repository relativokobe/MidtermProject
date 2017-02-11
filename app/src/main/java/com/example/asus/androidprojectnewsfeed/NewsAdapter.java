package com.example.asus.androidprojectnewsfeed;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.ListMenuItemView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 06/02/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Articles> articles;

    public NewsAdapter(Context mContext, ArrayList<Articles> articles) {
        this.mContext = mContext;
        this.articles = articles;
        Log.d("charles","ni sud sa news adapter" + articles.size());
        Log.d("charles","Sud sa article 1:\n" + articles.get(5).getTitle() + "\n"+ articles.get(5).getDescription());
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_adapter,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {
        String time;
        String date;
        String str;
        str = articles.get(position).getPublishedAt();
        Log.e("kobe",""+str);
//        date = str.substring(str.indexOf('T'));
        time = str.substring(str.lastIndexOf('T')+1);
        Glide.with(mContext).load(articles.get(position).getUrlToImage()).into(holder.news_image);
        holder.news_title.setText(articles.get(position).getTitle());
        holder.news_desc.setText(articles.get(position).getDescription());
     //   holder.date.setText(date);
//        Log.e("kobe",""+time);
//        holder.time.setText(time.substring(time.indexOf('Z')));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView news_image;
        private TextView news_title;
        private TextView news_desc;
        private LinearLayout news_link;
        TextView date;
        TextView time;
        public ViewHolder(final View itemView){
            super(itemView);
            news_image = (ImageView) itemView.findViewById(R.id.image);
            news_title = (TextView) itemView.findViewById(R.id.titleID);
            news_desc = (TextView) itemView.findViewById(R.id.descID);
            news_link = (LinearLayout) itemView.findViewById(R.id.linear);
            date = (TextView)itemView.findViewById(R.id.dateID);
            time = (TextView)itemView.findViewById(R.id.timeID);

            news_link.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri newsUri = Uri.parse(articles.get(getAdapterPosition()).getUrl());
                    Intent websiteIntent = new Intent(Intent.ACTION_VIEW,newsUri);
                    mContext.startActivity(websiteIntent);
                }
            });
        }
    }
}

