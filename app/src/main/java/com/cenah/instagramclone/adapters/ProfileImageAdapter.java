package com.cenah.instagramclone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cenah.instagramclone.R;
import com.cenah.instagramclone.models.Picture;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileImageAdapter extends RecyclerView.Adapter<ProfileImageAdapter.ViewHolder>{

    private ArrayList<Picture.PictureDetail> datalist;
    private LayoutInflater layoutInflater;
    private Context context;
    private OnProfileAdapterClick onClick;

    public ProfileImageAdapter(Context context, OnProfileAdapterClick onClick, ArrayList<Picture.PictureDetail> data) {
        layoutInflater = LayoutInflater.from(context);
        this.datalist = data;
        setHasStableIds(true);
        this.context = context;
        this.onClick = onClick;

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = layoutInflater.inflate(R.layout.profile_image_adapter_view, parent, false);
        final ViewHolder myViewHolder = new ViewHolder(v);
        myViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = myViewHolder.getAdapterPosition();
                onClick.onClick(datalist.get(position), position, v);
            }
        });
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.setIsRecyclable(false);
        holder.setData(datalist.get(position), position);

    }

    class ViewHolder extends RecyclerView.ViewHolder {


        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }

        void setData(final Picture.PictureDetail clicked, int position) {
            Picasso.get().load(clicked.getImages().getStandard_resolution().getUrl()).error(R.drawable.avatar).into(imageView);
        }

    }

    public interface OnProfileAdapterClick {
        void onClick(Picture.PictureDetail filesNameModel, int position, View view);
    }


}
