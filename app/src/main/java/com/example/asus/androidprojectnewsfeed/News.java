package com.example.asus.androidprojectnewsfeed;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 06/02/2017.
 */

public class News {
    private String status;
    private String source;
    private String sortBy;
    private ArrayList<Articles> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public ArrayList<Articles> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Articles> articles) {
        this.articles = articles;
    }

    public News(String status, String source, String sortBy, ArrayList<Articles> articles) {

        this.status = status;
        this.source = source;
        this.sortBy = sortBy;
        this.articles = articles;
    }
}
