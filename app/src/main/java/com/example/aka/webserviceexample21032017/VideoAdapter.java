package com.example.aka.webserviceexample21032017;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Aka on 3/21/2017.
 */

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    ArrayList<Video> mListData;

    public VideoAdapter(Context mContext, ArrayList<Video> mListData) {
        this.mContext = mContext;
        this.mListData = mListData;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder{
        TextView name, id, url;

        public VideoViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textViewName);
            id   = (TextView) itemView.findViewById(R.id.textViewId);
            url  = (TextView) itemView.findViewById(R.id.textViewUrl);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Video video = mListData.get(position);
        VideoViewHolder viewHolder = (VideoViewHolder) holder;
        viewHolder.name.setText(video.getName());
        viewHolder.id.setText(video.getId());
        viewHolder.url.setText(video.getUrl());
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }
}
