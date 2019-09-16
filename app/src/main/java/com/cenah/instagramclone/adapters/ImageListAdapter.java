package com.cenah.instagramclone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cenah.instagramclone.R;
import com.cenah.instagramclone.models.Picture;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder> {

    private ArrayList<Picture.PictureDetail> datalist;
    private LayoutInflater layoutInflater;
    private Context context;
    private OnProfileAdapterClick onClick;

    public ImageListAdapter(Context context, OnProfileAdapterClick onClick, ArrayList<Picture.PictureDetail> data) {
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
    public ImageListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = layoutInflater.inflate(R.layout.image_list_adapter_view, parent, false);
        final ImageListAdapter.ViewHolder myViewHolder = new ImageListAdapter.ViewHolder(v);
        myViewHolder.btn_cpmment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = myViewHolder.getAdapterPosition();
                onClick.onCommentClick(datalist.get(position), position, v);
            }
        });
        myViewHolder.btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = myViewHolder.getAdapterPosition();
                onClick.onLikeClick(datalist.get(position), position, v);
            }
        });
        myViewHolder.btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = myViewHolder.getAdapterPosition();
                onClick.onMoreClick(datalist.get(position), position, v);
            }
        });
        myViewHolder.btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = myViewHolder.getAdapterPosition();
                onClick.onSaveClick(datalist.get(position), position, v);
            }
        });
        myViewHolder.btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = myViewHolder.getAdapterPosition();
                onClick.onSendClick(datalist.get(position), position, v);
            }
        });
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ImageListAdapter.ViewHolder holder, int position) {

        holder.setIsRecyclable(false);
        holder.setData(datalist.get(position), position);

    }

    class ViewHolder extends RecyclerView.ViewHolder {


        ImageView iv_publisher, iv_picture, iv_liked_picture;
        TextView tx_username, tx_liked_name, tx_liked_count, tx_username_for_comment, tx_comment;
        ImageButton btn_more, btn_like, btn_cpmment, btn_send, btn_save;

        ViewHolder(View itemView) {
            super(itemView);
            iv_publisher = itemView.findViewById(R.id.iv_publisher);
            iv_picture = itemView.findViewById(R.id.iv_picture);
            iv_liked_picture = itemView.findViewById(R.id.iv_liked_picture);

            tx_username = itemView.findViewById(R.id.tx_username);
            tx_liked_name = itemView.findViewById(R.id.tx_liked_name);
            tx_liked_count = itemView.findViewById(R.id.tx_liked_count);
            tx_username_for_comment = itemView.findViewById(R.id.tx_username_for_comment);
            tx_comment = itemView.findViewById(R.id.tx_comment);

            btn_more = itemView.findViewById(R.id.btn_more);
            btn_like = itemView.findViewById(R.id.btn_like);
            btn_cpmment = itemView.findViewById(R.id.btn_cpmment);
            btn_send = itemView.findViewById(R.id.btn_send);
            btn_save = itemView.findViewById(R.id.btn_save);
        }

        void setData(final Picture.PictureDetail clicked, int position) {
            Picasso.get().load(clicked.getImages().getStandard_resolution().getUrl()).error(R.drawable.avatar).into(iv_picture);
            Picasso.get().load(clicked.getPublisherUser().getProfile_picture()).error(R.drawable.avatar).into(iv_publisher);
            Picasso.get().load(R.drawable.avatar).error(R.drawable.avatar).into(iv_liked_picture);

            tx_username.setText(clicked.getPublisherUser().getUsername());
           // tx_liked_name.setText("");
            tx_liked_count.setText(String.valueOf(clicked.getLikes().getCount()));

            if (clicked.getCaption() != null) {
                tx_username_for_comment.setText(clicked.getCaption().getFrom().getUsername());
                tx_comment.setText(clicked.getCaption().getText() == null ? "" : clicked.getCaption().getText());
            }


        }

    }

    public interface OnProfileAdapterClick {
        void onMoreClick(Picture.PictureDetail filesNameModel, int position, View view);

        void onLikeClick(Picture.PictureDetail filesNameModel, int position, View view);

        void onCommentClick(Picture.PictureDetail filesNameModel, int position, View view);

        void onSendClick(Picture.PictureDetail filesNameModel, int position, View view);

        void onSaveClick(Picture.PictureDetail filesNameModel, int position, View view);
    }


}
