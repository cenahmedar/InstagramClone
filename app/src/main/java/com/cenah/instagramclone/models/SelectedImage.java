package com.cenah.instagramclone.models;

public class SelectedImage {
    private Picture.PictureDetail picture;
    private int position;

    public Picture.PictureDetail getPicture() {
        return picture;
    }

    public void setPicture(Picture.PictureDetail picture) {
        this.picture = picture;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
