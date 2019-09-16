package com.cenah.instagramclone.helpers;

import com.cenah.instagramclone.models.SelectedImage;

public class Utils {

    public static final String BASE_URL = "https://api.instagram.com";

    public static final String CLIENT_ID = "";
    public static final String CLIENT_SECRET = "";
    public static final String REDIRECT_URI = "";
    public static final String SCOPE = "public_content";

    public static final String AUTORISATION_CODE = "authorization_code";
    public static String LOGIN_URL = "https://api.instagram.com/oauth/authorize/?client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI + "&response_type=code";

    public static SelectedImage SELECTED_IMAGE = new SelectedImage();

}
