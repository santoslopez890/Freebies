package com.freebies.app.service;

import com.freebies.app.domain.Item;
import com.freebies.app.domain.RawItem;
import com.freebies.app.domain.Tag;
import com.freebies.app.repository.ItemRepository;
import com.freebies.app.repository.TagRepository;
import com.freebies.app.repository.UserRepository;
import com.freebies.app.service.mapper.RawItemMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RawItemServiceTransactional {
    private final UserRepository userProfileRepository;
    private final ItemRepository itemRepository;
    private final TagRepository tagRepository;

    private final RawItemMapper itemMapper;



    public RawItemServiceTransactional(
        UserRepository userProfileRepository,
    ItemRepository itemRepository,
    TagRepository tagRepository,
        RawItemMapper itemMapper
    ){
        this.itemRepository=itemRepository;
        this.tagRepository=tagRepository;
        this.userProfileRepository=userProfileRepository;
        this.itemMapper = itemMapper;
    }
    public List<Item> saveToDataBase(List<RawItem> newsArticleRawList){
        return newsArticleRawList.stream().map(this::saveSingleItem).collect(Collectors.toList());

    }
    public Item saveSingleItem(RawItem rawItem){
        //Convert from newsArticle Raw to news Article
        Item item=itemMapper.externalToInternal(rawItem);
        //Categories
        for(String s: rawItem.getCategory()) {
            Tag category = findOrMakeNewCategory(capitalize(s));
            item.addTag(category);
        }

        return itemRepository.save(item);
    }

    private String capitalize(String input){
        return Character.toUpperCase(input.charAt(0)) + input.substring(1).toLowerCase();
    }

    private Tag findOrMakeNewCategory(String s) {
        List<Tag> listOfCategories = tagRepository.findAll()
            .stream()
            .filter(category -> category.getTitle() != null)
            .filter(category -> category.getTitle().equals(s))
            .collect(Collectors.toList());
        if(listOfCategories.isEmpty()){
            // create new category in repository
            return tagRepository.save(new Tag().title(s));
        }
        // otherwise return the category found
        return listOfCategories.get(0);
    }


}
