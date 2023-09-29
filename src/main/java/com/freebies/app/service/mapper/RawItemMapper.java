package com.freebies.app.service.mapper;

import com.freebies.app.domain.Item;
import com.freebies.app.domain.RawItem;
import com.freebies.app.domain.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class RawItemMapper {
    public Item externalToInternal(RawItem rawItem){

        Item item=new Item();
        item.setName(rawItem.getName());
        item.setDescription(rawItem.getDescription());
        item.setLocation(rawItem.getLocation());
        item.setImage(rawItem.getImage());
        item.setPrice(0.0);
        return item;
    }
}
