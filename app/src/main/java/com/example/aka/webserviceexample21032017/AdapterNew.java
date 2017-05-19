package com.example.aka.webserviceexample21032017;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Aka on 3/21/2017.
 */

public class AdapterNew extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Video> mListVideo;

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_lOADING = 1;

    public AdapterNew(Context mContext, List<Video> mListVideo) {
        this.mContext = mContext;
        this.mListVideo = mListVideo;
    }

    @Override
    public int getItemViewType(int position) {
        return mListVideo.get(position) == null ? VIEW_TYPE_lOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM){
            return new ViewHoler(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false));
        }else if (viewType == VIEW_TYPE_lOADING){
            return new LoadingViewHoler(LayoutInflater.from(parent.getContext()).inflate(R.layout.header, parent, false));
        }
        return null;

    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder1, int position) {

        if(holder1 instanceof ViewHoler) {
            Video video = mListVideo.get(position);
            ViewHoler viewHolder = (ViewHoler) holder1;
            viewHolder.name.setText(video.getName());
            viewHolder.id.setText(video.getId());
            viewHolder.url.setText(video.getUrl());

        }else if(holder1 instanceof LoadingViewHoler) {
            LoadingViewHoler loadingViewHoler = (LoadingViewHoler) holder1;

        }


    }

    @Override
    public int getItemCount() {
        return mListVideo.size();
    }

    public static class ViewHoler extends RecyclerView.ViewHolder {
        TextView  name, id, url;

        public ViewHoler(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textViewName);
            id   = (TextView) itemView.findViewById(R.id.textViewId);
            url  = (TextView) itemView.findViewById(R.id.textViewUrl);
        }

    }

    public static class LoadingViewHoler extends RecyclerView.ViewHolder{

        public LoadingViewHoler(View itemView) {
            super(itemView);



        }
    }

}
