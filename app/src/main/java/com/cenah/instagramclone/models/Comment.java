package com.cenah.instagramclone.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Comment {
    @SerializedName("data")
    @Expose
    private ArrayList<Data> data = new ArrayList<>();

    public class Data {
        @SerializedName("id")
        @Expose
        private String id;

        @SerializedName("text")
        @Expose
        private String text;

        @SerializedName("created_time")
        @Expose
        private String created_time;

        @SerializedName("from")
        @Expose
        private From from;

        public class From{
            @SerializedName("username")
            @Expose
            private String username;

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }

        public From getFrom() {
            return from;
        }

        public void setFrom(From from) {
            this.from = from;
        }
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }
}
