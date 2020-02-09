package com.example.newsapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class home extends AppCompatActivity {
    TextView title,author,description;
    ImageView image;
    String getTitle,getImage,getAuthor,getDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        title = findViewById(R.id.title);
        image = findViewById(R.id.image);
        author = findViewById(R.id.author);
        description = findViewById(R.id.description);
        getTitle = getIntent().getStringExtra("title");
        getImage = getIntent().getStringExtra("image");
        getAuthor = getIntent().getStringExtra("author");
        getDesc = getIntent().getStringExtra("description");
        Picasso.get().load(getImage).into(image);
        title.setText(getTitle);
        author.setText(getAuthor);
        description.setText(getDesc);
    }
}
