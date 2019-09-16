package com.cenah.instagramclone.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;


public class Picture implements Serializable {

    @SerializedName("pagination")
    @Expose
    private Pagination PaginationObject;

    @SerializedName("data")
    @Expose
    private ArrayList<PictureDetail> data = new ArrayList<PictureDetail>();

    @SerializedName("meta")
    @Expose
    private Meta MetaObject;

    public Pagination getPagination() {
        return PaginationObject;
    }

    public Meta getMeta() {
        return MetaObject;
    }

    public void setPagination(Pagination paginationObject) {
        this.PaginationObject = paginationObject;
    }

    public void setMeta(Meta metaObject) {
        this.MetaObject = metaObject;
    }

    public Pagination getPaginationObject() {
        return PaginationObject;
    }

    public void setPaginationObject(Pagination paginationObject) {
        PaginationObject = paginationObject;
    }

    public ArrayList<PictureDetail> getData() {
        return data;
    }

    public void setData(ArrayList<PictureDetail> data) {
        this.data = data;
    }

    public Meta getMetaObject() {
        return MetaObject;
    }

    public void setMetaObject(Meta metaObject) {
        MetaObject = metaObject;
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

    private class Pagination {
    }

    ////////////////////
    //////////////////////
    //////////////////////////
    //////////////////////////
    ////////////////////////////

    public class PictureDetail {


        @SerializedName("id")
        @Expose
        private String id;

        @SerializedName("user")
        @Expose
        private PublisherUser publisherUser;

        @SerializedName("images")
        @Expose
        private ImagesRresolutions images;

        @SerializedName("created_time")
        @Expose
        private String created_time;

        @SerializedName("caption")
        @Expose
        private Caption caption;

        @SerializedName("user_has_liked")
        @Expose
        private boolean user_has_liked;

        @SerializedName("likes")
        @Expose
        private Likes likes;

        @SerializedName("tags")
        @Expose
        private ArrayList<String> tags = new ArrayList<>();

        @SerializedName("comments")
        @Expose
        private Likes comments;

        @SerializedName("link")
        @Expose
        private String link;

        @SerializedName("location")
        @Expose
        private Location location;


        //note that i didint use 1.filter  2.type 3.attribution 4.users_in_photo


        public class ImagesRresolutions {

            @SerializedName("thumbnail")
            @Expose
            private Rresolutions thumbnail;

            @SerializedName("low_resolution")
            @Expose
            private Rresolutions low_resolution;

            @SerializedName("standard_resolution")
            @Expose
            private Rresolutions standard_resolution;


            public class Rresolutions {
                @SerializedName("width")
                @Expose
                private int width;

                @SerializedName("height")
                @Expose
                private int height;

                @SerializedName("url")
                @Expose
                private String url;

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }

            public Rresolutions getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(Rresolutions thumbnail) {
                this.thumbnail = thumbnail;
            }

            public Rresolutions getLow_resolution() {
                return low_resolution;
            }

            public void setLow_resolution(Rresolutions low_resolution) {
                this.low_resolution = low_resolution;
            }

            public Rresolutions getStandard_resolution() {
                return standard_resolution;
            }

            public void setStandard_resolution(Rresolutions standard_resolution) {
                this.standard_resolution = standard_resolution;
            }


        }

        public class Likes {
            @SerializedName("count")
            @Expose
            private int count;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }

        private class Location {

            @SerializedName("latitude")
            @Expose
            private String latitude;

            @SerializedName("longitude")
            @Expose
            private String longitude;

            @SerializedName("name")
            @Expose
            private String name;

            @SerializedName("id")
            @Expose
            private String id;

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        public class PublisherUser{
            @SerializedName("id")
            @Expose
            private String id;

            @SerializedName("full_name")
            @Expose
            private String full_name;

            @SerializedName("profile_picture")
            @Expose
            private String profile_picture;

            @SerializedName("username")
            @Expose
            private String username;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getFull_name() {
                return full_name;
            }

            public void setFull_name(String full_name) {
                this.full_name = full_name;
            }

            public String getProfile_picture() {
                return profile_picture;
            }

            public void setProfile_picture(String profile_picture) {
                this.profile_picture = profile_picture;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }

        public class Caption{
            @SerializedName("id")
            @Expose
            String id;

            @SerializedName("text")
            @Expose
            String text;

            @SerializedName("created_time")
            @Expose
            String created_time;

            @SerializedName("from")
            @Expose
            From from;

            public class From{
                @SerializedName("id")
                @Expose
                String id;

                @SerializedName("full_name")
                @Expose
                String full_name;

                @SerializedName("profile_picture")
                @Expose
                String profile_picture;

                @SerializedName("username")
                @Expose
                String username;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getFull_name() {
                    return full_name;
                }

                public void setFull_name(String full_name) {
                    this.full_name = full_name;
                }

                public String getProfile_picture() {
                    return profile_picture;
                }

                public void setProfile_picture(String profile_picture) {
                    this.profile_picture = profile_picture;
                }

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


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public PublisherUser getPublisherUser() {
            return publisherUser;
        }

        public void setPublisherUser(PublisherUser publisherUser) {
            this.publisherUser = publisherUser;
        }

        public ImagesRresolutions getImages() {
            return images;
        }

        public void setImages(ImagesRresolutions images) {
            this.images = images;
        }

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }

        public Caption getCaption() {
            return caption;
        }

        public void setCaption(Caption caption) {
            this.caption = caption;
        }

        public boolean isUser_has_liked() {
            return user_has_liked;
        }

        public void setUser_has_liked(boolean user_has_liked) {
            this.user_has_liked = user_has_liked;
        }

        public Likes getLikes() {
            return likes;
        }

        public void setLikes(Likes likes) {
            this.likes = likes;
        }

        public ArrayList<String> getTags() {
            return tags;
        }

        public void setTags(ArrayList<String> tags) {
            this.tags = tags;
        }

        public Likes getComments() {
            return comments;
        }

        public void setComments(Likes comments) {
            this.comments = comments;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }
    }


}
