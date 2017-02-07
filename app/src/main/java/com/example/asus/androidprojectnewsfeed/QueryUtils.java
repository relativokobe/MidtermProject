package com.example.asus.androidprojectnewsfeed;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 06/02/2017.
 */

public class QueryUtils {
    public QueryUtils() {

    }
    public static ArrayList<Articles>fetchData(String requestUrl){
        URL url = createUrl(requestUrl);
        String jsonresponse = null;
        try{
            jsonresponse = makeRequest(url);
        }catch (IOException e){

        }
        ArrayList<Articles>news = extractJson(jsonresponse);
        return news;
    }

    public static URL createUrl(String Strurl){
        URL url = null;
        try{
            url = new URL(Strurl);

        }catch (MalformedURLException e){

        }
        return url;
    }
    public static String makeRequest(URL url) throws IOException{
        String jsonresponse="";
        if(url == null){
            return jsonresponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonresponse = readFromStream(inputStream);
            } else {
               // Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }

        }catch (IOException e){

        }finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonresponse;
    }
    public static String readFromStream(InputStream inputStream)throws IOException{
        StringBuilder output = new StringBuilder();
        if(inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while(line!=null){
                output.append(line);
                line = reader.readLine();

            }
        }
        return output.toString();
    }
    private static ArrayList<Articles>extractJson(String newsJson){
        if(TextUtils.isEmpty(newsJson)){
            return null;
        }
        ArrayList<Articles>news = new ArrayList<>();
        try{
            JSONObject baseJsonResponse = new JSONObject(newsJson);
            JSONArray newsarray = baseJsonResponse.getJSONArray("articles");

            for(int i = 0; i < newsarray.length();i++){
                JSONObject currNews = newsarray.getJSONObject(i);
              // JSONObject author = currNews.getJSONObject("author");
                String author = currNews.getString("author");
                String description = currNews.getString("description");
                String title = currNews.getString("title");
                String url = currNews.getString("url");
                String urlToImage = currNews.getString("urlToImage");
                String publishedAt = currNews.getString("publishedAt");

                Articles articles = new Articles(author,description,title,url,urlToImage,publishedAt);
                news.add(articles);
            }

        }catch (JSONException e){

        }
        return news;
    }

}
