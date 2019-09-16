package com.cenah.instagramclone.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {

    @SerializedName("data")
    @Expose
    private Data DataObject;
    @SerializedName("meta")
    @Expose
    private Meta MetaObject;


    // Getter Methods

    public Data getData() {
        return DataObject;
    }

    public Meta getMeta() {
        return MetaObject;
    }

    // Setter Methods

    public void setData(Data dataObject) {
        this.DataObject = dataObject;
    }

    public void setMeta(Meta metaObject) {
        this.MetaObject = metaObject;
    }

    public class Meta {
        @SerializedName("code")
        @Expose
        private float code;

        public float getCode() {
            return code;
        }

        public void setCode(float code) {
            this.code = code;
        }
    }

    public class Data {
        @SerializedName("id")
        @Expose
        private String id;

        @SerializedName("username")
        @Expose
        private String username;

        @SerializedName("profile_picture")
        @Expose
        private String profile_picture;

        @SerializedName("full_name")
        @Expose
        private String full_name;

        @SerializedName("bio")
        @Expose
        private String bio;

        @SerializedName("website")
        @Expose
        private String website;

        @SerializedName("is_business")
        @Expose
        private boolean is_business;

        @SerializedName("counts")
        @Expose
        Counts CountsObject;


        // Getter Methods

        public String getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public String getProfile_picture() {
            return profile_picture;
        }

        public String getFull_name() {
            return full_name;
        }

        public String getBio() {
            return bio;
        }

        public String getWebsite() {
            return website;
        }

        public boolean getIs_business() {
            return is_business;
        }

        public Counts getCounts() {
            return CountsObject;
        }

        // Setter Methods

        public void setId(String id) {
            this.id = id;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setProfile_picture(String profile_picture) {
            this.profile_picture = profile_picture;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public void setIs_business(boolean is_business) {
            this.is_business = is_business;
        }

        public void setCounts(Counts countsObject) {
            this.CountsObject = countsObject;
        }
    }

    public class Counts {
        @SerializedName("media")
        @Expose
        private int media;
        @SerializedName("follows")
        @Expose
        private int follows;
        @SerializedName("followed_by")
        @Expose
        private int followed_by;


        public int getMedia() {
            return media;
        }

        public void setMedia(int media) {
            this.media = media;
        }

        public int getFollows() {
            return follows;
        }

        public void setFollows(int follows) {
            this.follows = follows;
        }

        public int getFollowed_by() {
            return followed_by;
        }

        public void setFollowed_by(int followed_by) {
            this.followed_by = followed_by;
        }
    }

}
