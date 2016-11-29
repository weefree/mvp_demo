package com.example.mvpdemo.ui;

import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mvpdemo.R;

public class PictureActivity extends AppCompatActivity {
    public static final String EXTRA_IMAGE_URL = "EXTRA_IMAGE_URL";
    public static final String EXTRA_IMAGE_TITLE = "EXTRA_IMAGE_TITLE";
    public static final String TRANSIT_IMG = "TRANSIT_IMG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        ImageView picImg = (ImageView)findViewById(R.id.pic_iv);
        TextView picTitle = (TextView)findViewById(R.id.pic_title);

        Glide.with(this).load(getIntent().getStringExtra(EXTRA_IMAGE_URL)).into(picImg);
        picTitle.setText(getIntent().getStringExtra(EXTRA_IMAGE_TITLE));

        ViewCompat.setTransitionName(picImg,TRANSIT_IMG);
    }
}
