package com.cenah.instagramclone.rest;

import com.cenah.instagramclone.helpers.Utils;
import com.cenah.instagramclone.rest.services.InstagramService;
import com.cenah.instagramclone.rest.services.TokenService;

public class RestFullHelper {
    private String BASE_URL;

    public RestFullHelper() {
        this.BASE_URL = Utils.BASE_URL;
    }

    public TokenService getTokenServiceClient() {
        return RetrofitClient.getClient(BASE_URL).create(TokenService.class);
    }

    public InstagramService getInstagramServiceClient() {
        return RetrofitClient.getClient(BASE_URL).create(InstagramService.class);
    }
}
