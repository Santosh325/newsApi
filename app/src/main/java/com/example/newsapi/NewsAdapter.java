package com.example.newsapi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    Context mContext;
    List<Article> mArticles;

    public NewsAdapter(Context context,List<Article> articles) {
        this.mContext = context;
        this.mArticles = articles;
    }
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        final Article data = mArticles.get(position);
        holder.author.setText(data.getAuthor());
        holder.title.setText(data.getTitle());SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date newDate = null;
        try {
            newDate = spf.parse(data.getPublishedAt());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spf = new SimpleDateFormat("dd MMM yyyy");
        String newDateString = spf.format(newDate);
        holder.publish.setText(newDateString);

        Picasso.get().load(data.getUrlToImage()).into(holder.mImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,home.class);
                intent.putExtra("title",data.getTitle());
                intent.putExtra("image",data.getUrlToImage());
                intent.putExtra("author",data.getAuthor());
                intent.putExtra("description",data.getDescription());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }


    public class NewsViewHolder extends  RecyclerView.ViewHolder{

       TextView title,author,publish,mdescription;
        ImageView mImageView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            publish = itemView.findViewById(R.id.publish);
            mImageView = itemView.findViewById(R.id.profileimage);
            mdescription = itemView.findViewById(R.id.description);
        }
    }
}
