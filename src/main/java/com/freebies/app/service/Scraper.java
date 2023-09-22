package com.freebies.app.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service

public class Scraper {
    private ArrayList<Items> itemArrayList;
    public static WebDriver driver(){

        System.out.println("Configuring Chrome");
        System.setProperty("webdriver.chrome.driver","webpack/chromedriver");
        ChromeOptions options = new ChromeOptions();
        String userAget = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36";
        options.addArguments(String.format("user-agent=%s", userAget));
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("\"excludeSwitches\", [\"enable-automation\"]");
        options.addArguments("--disable-extensions");
        options.addArguments("--profile-directory=Default");
        options.addArguments("--disable-plugins-discovery");
        options.addArguments("--start-maximized");
        WebDriver driver= new ChromeDriver(options);
        System.out.println("Logging into Facebook");
        //Login to facebook
        driver.get("https://www.facebook.com");
        driver.manage().window().maximize();
        driver.findElement(By.id("email")).sendKeys(UrlLinkEnum.UserName.getLink());
        driver.findElement(By.name("pass")).sendKeys(UrlLinkEnum.Password.getLink());
        driver.findElement(By.name("login")).click();
        driver.findElement(By.className("_97w4")).click();
        driver.findElement(By.className("_aklt")).click();
        driver.findElement(By.name("pass")).sendKeys(UrlLinkEnum.Password.getLink());
        driver.findElement(By.name("login")).click();
        return driver;


    }
    public ArrayList<Items> scrapeData(String category, WebDriver driver)throws InterruptedException{
             itemArrayList=new ArrayList<>();
            System.out.println("Scraping Data");
            driver.get(category);
            //Scrape items from page
            List<WebElement> WebElement  =  driver.findElements(By.className("xjp7ctv"));
            for (WebElement element : WebElement) {
                Items item=new Items();
                String[] lines = element.getText().split("\n");
                try {
                    item.setPrice(lines[0]);
                }catch (ArrayIndexOutOfBoundsException e){

                }
                try {
                    item.setName(lines[1]);
                }catch (ArrayIndexOutOfBoundsException e){

                }
                try {
                    item.setLocation(lines[2]);
                }catch (ArrayIndexOutOfBoundsException e){
                }
                String outerHtml = element.getAttribute("outerHTML");
                // Extract the image link using a regular expression

                Pattern imgPattern = Pattern.compile("<img\\s+.*?src\\s*=\\s*['\"]([^'\"]+)['\"].*?>");
                Matcher imgMatcher = imgPattern.matcher(outerHtml);
                if (imgMatcher.find()) {
                    String imageLink = imgMatcher.group(1);
                    imageLink = imageLink.replace("&amp;", "&");
                    item.setImageLink(imageLink);
                    itemArrayList.add(item);
                }
            }
            for (Items i:itemArrayList
            ) {
                System.out.println(i.toString());
            }
            return itemArrayList;
        }

    public static void main(String[] args) throws InterruptedException {
        Scraper scrape=new Scraper();
        WebDriver driver=scrape.driver();
        scrape.scrapeData(UrlLinkEnum.Garden.getLink(),driver);
        scrape.scrapeData(UrlLinkEnum.Home.getLink(),driver);
        scrape.scrapeData(UrlLinkEnum.Toys.getLink(),driver);
    }
    }
