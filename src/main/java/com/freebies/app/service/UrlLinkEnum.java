package com.freebies.app.service;

public enum UrlLinkEnum {

    linkp1("https://www.facebook.com/marketplace/category/"),
    linkp2("?minPrice=0&maxPrice=0&daysSinceListed=1&deliveryMethod=local_pick_up&sortBy=creation_time_descend&exact=false"),
    UserName("santosshop890@gmail.com"), Password("BobLangis20??");


    private final String link;

    UrlLinkEnum(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
