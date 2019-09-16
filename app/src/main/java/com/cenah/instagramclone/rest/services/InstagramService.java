package com.cenah.instagramclone.rest.services;

import com.cenah.instagramclone.models.Comment;
import com.cenah.instagramclone.models.Picture;
import com.cenah.instagramclone.models.UserInfo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface InstagramService {



    @GET("/v1/tags/{tag-name}/media/recent")
    Call<ResponseBody> getResponse(@Path("tag-name") String tagName, @Query("access_token") String accessToken,
                                   @Query("max_id") String maxId, @Query("min_id") String minId);


    @GET("/v1/users/self")
    Call<UserInfo> getUserInfo(@Query("access_token") String accessToken);

    @GET("/v1/users/self/media/recent")
    Call<Picture> getUserPhotos(@Query("access_token") String accessToken);

    @GET("/v1/media/{media-id}/comments")
    Call<Comment> getPhotoComments(@Path("media-id") String media_id, @Query("access_token") String accessToken);



}
