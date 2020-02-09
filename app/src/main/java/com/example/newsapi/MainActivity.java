package com.example.newsapi;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    RequestQueue queue;
    NewsAdapter mNewsAdapter;
    List<Article> mArticles;
String url="https://newsapi.org/v2/everything?q=bitcoin&from=2020-01-07&sortBy=publishedAt&apiKey=4e4b02a166574cefb2a6130fae7a3d39";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         recyclerview = findViewById(R.id.recycleview);
         recyclerview.setLayoutManager(new GridLayoutManager(this,2));
         recyclerview.setHasFixedSize(true);
         mArticles = new ArrayList<>();
         loadDataFromUrl();
    }


    private void loadDataFromUrl() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                getArticleFromResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }

    private void getArticleFromResponse(JSONObject response) {
        try {
            JSONArray jsonArray = response.getJSONArray("articles");
            for(int i = 0; i < jsonArray.length(); i++) {
                Article article = new Article();
                JSONObject newjsonobject = jsonArray.getJSONObject(i);
                article.setTitle(newjsonobject.getString("title"));
                article.setAuthor(newjsonobject.getString("author"));
                article.setDescription(newjsonobject.getString("description"));
                article.setPublishedAt(newjsonobject.getString("publishedAt"));
                article.setUrlToImage(newjsonobject.getString("urlToImage"));
                mArticles.add(article);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        mNewsAdapter = new NewsAdapter(this,mArticles);
        recyclerview.setAdapter(mNewsAdapter);
        mNewsAdapter.notifyDataSetChanged();
    }
}
