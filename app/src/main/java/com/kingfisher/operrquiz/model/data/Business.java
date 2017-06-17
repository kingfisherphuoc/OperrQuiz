package com.kingfisher.operrquiz.model.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kingfisher on 6/17/17.
 */

public class Business {
    /**
     * id : woods-cervecería-san-francisco-2
     * name : Woods Cervecería
     * image_url : https://s3-media1.fl.yelpcdn.com/bphoto/PFPxHJYsLHrnu473K9Poog/o.jpg
     * is_closed : false
     * url : https://www.yelp.com/biz/woods-cervecer%C3%ADa-san-francisco-2?adjust_creative=uWUJ0_3moyYtDtTalfNviQ&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=uWUJ0_3moyYtDtTalfNviQ
     * review_count : 162
     * categories : [{"alias":"bars","title":"Bars"},{"alias":"breweries","title":"Breweries"},{"alias":"venues","title":"Venues & Event Spaces"}]
     * rating : 4
     * coordinates : {"latitude":37.7610893249512,"longitude":-122.428520202637}
     * transactions : []
     * price : $$
     * location : {"address1":"3801 18th St","address2":"","address3":"","city":"San Francisco","zip_code":"94114","country":"US","state":"CA","display_address":["3801 18th St","San Francisco, CA 94114"]}
     * phone : +14152128412
     * display_phone : (415) 212-8412
     * distance : 875.926103444
     */

    @SerializedName ("id")
    public String id;
    @SerializedName ("name")
    public String name;
    @SerializedName ("image_url")
    public String imageUrl;
    @SerializedName ("is_closed")
    public boolean isClosed;
    @SerializedName ("url")
    public String url;
    @SerializedName ("review_count")
    public int reviewCount;
    @SerializedName ("rating")
    public float rating;
    @SerializedName ("coordinates")
    public Coordinate coordinate;
    @SerializedName ("price")
    public String price;
    @SerializedName ("location")
    public LocationBean location;
    @SerializedName ("phone")
    public String phone;
    @SerializedName ("display_phone")
    public String displayPhone;
    @SerializedName ("distance")
    public double distance;
    @SerializedName ("categories")
    public List<Category> categories;
    @SerializedName ("transactions")
    public List<?> transactions;

    public static class Coordinate {
        /**
         * latitude : 37.7610893249512
         * longitude : -122.428520202637
         */

        @SerializedName ("latitude")
        public double latitude;
        @SerializedName ("longitude")
        public double longitude;
    }

    public static class LocationBean {
        /**
         * address1 : 3801 18th St
         * address2 :
         * address3 :
         * city : San Francisco
         * zip_code : 94114
         * country : US
         * state : CA
         * display_address : ["3801 18th St","San Francisco, CA 94114"]
         */

        @SerializedName ("address1")
        public String address1;
        @SerializedName ("address2")
        public String address2;
        @SerializedName ("address3")
        public String address3;
        @SerializedName ("city")
        public String city;
        @SerializedName ("zip_code")
        public String zipCode;
        @SerializedName ("country")
        public String country;
        @SerializedName ("state")
        public String state;
        @SerializedName ("display_address")
        public List<String> displayAddress;
    }

    public static class Category {
        /**
         * alias : bars
         * title : Bars
         */

        @SerializedName ("alias")
        public String alias;
        @SerializedName ("title")
        public String title;
    }
}
