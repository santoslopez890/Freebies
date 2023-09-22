package com.freebies.app.service;

public enum UrlLinkEnum {

    Garden("https://www.facebook.com/marketplace/category/garden?minPrice=0&maxPrice=0&daysSinceListed=1&deliveryMethod=local_pick_up&sortBy=creation_time_descend&exact=false"),
    Electronics("https://www.facebook.com/marketplace/category/electronics?minPrice=0&maxPrice=0&daysSinceListed=1&deliveryMethod=local_pick_up&sortBy=creation_time_descend&exact=false"),
    Home("https://www.facebook.com/marketplace/category/home?minPrice=0&maxPrice=0&daysSinceListed=1&deliveryMethod=local_pick_up&sortBy=creation_time_descend&exact=false"),
    apparel("https://www.facebook.com/marketplace/category/apparel?minPrice=0&maxPrice=0&daysSinceListed=1&deliveryMethod=local_pick_up&sortBy=creation_time_descend&exact=false"),
    Instruments("https://www.facebook.com/marketplace/category/instruments?minPrice=0&maxPrice=0&daysSinceListed=1&deliveryMethod=local_pick_up&sortBy=creation_time_descend&exact=false"),
    PetSupplies("https://www.facebook.com/marketplace/category/pets?minPrice=0&maxPrice=0&daysSinceListed=1&deliveryMethod=local_pick_up&sortBy=creation_time_descend&exact=false"),
    UserName("3025259704"), Password("BobLee30??"),

    Toys("https://www.facebook.com/marketplace/category/toys?minPrice=0&maxPrice=0&daysSinceListed=1&deliveryMethod=local_pick_up&sortBy=creation_time_descend&exact=false");


    private final String link;

    UrlLinkEnum(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
