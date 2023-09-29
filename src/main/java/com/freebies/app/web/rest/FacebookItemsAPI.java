package com.freebies.app.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.freebies.app.domain.RawItem;

import com.freebies.app.service.RawItemServiceTransactional;
import com.freebies.app.service.Scraper;
import com.freebies.app.service.UrlLinkEnum;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


@RestController
@RequestMapping("/FaceApi")
public class FacebookItemsAPI {
    @Autowired
    WebDriver webDriver;
    @Autowired
    RawItemServiceTransactional itemServiceTransactional;

    private final Logger log = LoggerFactory.getLogger(FacebookItemsAPI.class);
    @GetMapping("/fetch-and-save-garden")
    public ResponseEntity<String> fetchAndSaveNews(
        @RequestParam String category
    ) throws JsonProcessingException, InterruptedException {
        log.debug("request params are {}", category);
        // Fetch news articles from the external API
        List<RawItem> items = Scraper.scrapeData(category,webDriver); // Fetch and map raw articles here
        // Save the articles to the database
        itemServiceTransactional.saveToDataBase(items);
        return ResponseEntity.ok("News articles saved successfully.");
    }

}
