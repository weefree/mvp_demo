package com.example.mvpdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mvpdemo.R;
import com.example.mvpdemo.contract.MainContract;
import com.example.mvpdemo.model.entity.Picture;

import java.util.List;

/**
 * Created by fmw on 2016/11/28.
 */

public class MainPictureAdapter extends RecyclerView.Adapter<MainPictureAdapter.MainPictureViewHolder>{

    private List<Picture> data;
    private OnClickListener listener;

    public MainPictureAdapter(List<Picture> data,OnClickListener listener){
        this.data = data;
        this.listener = listener;
    }

    @Override
    public MainPictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LinearLayout.inflate(parent.getContext(), R.layout.item_main_picture,null);
        return new MainPictureViewHolder(rootView,parent.getContext());
    }

    @Override
    public void onBindViewHolder(MainPictureViewHolder holder, int position) {

        final Picture picture = data.get(position);
        Glide.with(holder.context).load(picture.share_url).into(holder.itemImage);
        holder.itemTitle.setText(picture.desc);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null)listener.onClick(view,picture);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MainPictureViewHolder extends RecyclerView.ViewHolder{

        public Context context;
        public ImageView itemImage;
        public TextView itemTitle;

        public MainPictureViewHolder(View itemView,Context context) {
            super(itemView);
            this.context = context;
            itemImage = (ImageView)itemView.findViewById(R.id.item_image);
            itemTitle = (TextView)itemView.findViewById(R.id.item_title);
        }
    }

    public interface OnClickListener{
        void onClick(View view,Picture picture);
    }

}
