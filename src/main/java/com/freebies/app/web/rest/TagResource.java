package com.freebies.app.web.rest;

import com.freebies.app.domain.Item;
import com.freebies.app.domain.Tag;
import com.freebies.app.repository.TagRepository;
import com.freebies.app.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.freebies.app.domain.Tag}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TagResource {

    private final Logger log = LoggerFactory.getLogger(TagResource.class);

    private static final String ENTITY_NAME = "tag";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TagRepository tagRepository;

    public TagResource(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    /**
     * {@code POST  /tags} : Create a new tag.
     *
     * @param tag the tag to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tag, or with status {@code 400 (Bad Request)} if the tag has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tags")
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) throws URISyntaxException {
        log.debug("REST request to save Tag : {}", tag);
        if (tag.getId() != null) {
            throw new BadRequestAlertException("A new tag cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Tag result = tagRepository.save(tag);
        return ResponseEntity
            .created(new URI("/api/tags/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tags/:id} : Updates an existing tag.
     *
     * @param id the id of the tag to save.
     * @param tag the tag to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tag,
     * or with status {@code 400 (Bad Request)} if the tag is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tag couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tags/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable(value = "id", required = false) final Long id, @RequestBody Tag tag)
        throws URISyntaxException {
        log.debug("REST request to update Tag : {}, {}", id, tag);
        if (tag.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tag.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tagRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Tag result = tagRepository.save(tag);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tag.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tags/:id} : Partial updates given fields of an existing tag, field will ignore if it is null
     *
     * @param id the id of the tag to save.
     * @param tag the tag to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tag,
     * or with status {@code 400 (Bad Request)} if the tag is not valid,
     * or with status {@code 404 (Not Found)} if the tag is not found,
     * or with status {@code 500 (Internal Server Error)} if the tag couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tags/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Tag> partialUpdateTag(@PathVariable(value = "id", required = false) final Long id, @RequestBody Tag tag)
        throws URISyntaxException {
        log.debug("REST request to partial update Tag partially : {}, {}", id, tag);
        if (tag.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tag.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tagRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Tag> result = tagRepository
            .findById(tag.getId())
            .map(existingTag -> {
                if (tag.getTitle() != null) {
                    existingTag.setTitle(tag.getTitle());
                }

                return existingTag;
            })
            .map(tagRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tag.getId().toString())
        );
    }

    /**
     * {@code GET  /tags} : get all the tags.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tags in body.
     */
    @GetMapping("/tags")
    public List<Tag> getAllTags(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Tags");
        if (eagerload) {
            return tagRepository.findAllWithEagerRelationships();
        } else {
            return tagRepository.findAll();
        }
    }

    /**
     * {@code GET  /tags/:id} : get the "id" tag.
     *
     * @param id the id of the tag to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tag, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tags/{id}")
    public ResponseEntity<Tag> getTag(@PathVariable Long id) {
        log.debug("REST request to get Tag : {}", id);
        Optional<Tag> tag = tagRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(tag);
    }

    /**
     * {@code DELETE  /tags/:id} : delete the "id" tag.
     *
     * @param id the id of the tag to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tags/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        log.debug("REST request to delete Tag : {}", id);
        tagRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
    @GetMapping("/categories/{name}/articles")
    public List<Item> getCategoryArticles(
        @PathVariable String name,
        @RequestParam(required = false) Integer limit
    ) {
        log.debug("REST request to get Category : {}", name);
        // uses the tag repository to find all and filter by category and filters it by name and collects it and places it to a list
        List<Tag> categoryFound = tagRepository.findAll()
            .stream()
            .filter(category -> category.getTitle().equalsIgnoreCase(name))
            .collect(Collectors.toList());
        //makes a new list of items for later use
        List<Item> result;
        if(categoryFound.isEmpty()){
            result = new ArrayList<>();
        }
        else if(limit == null){
            result = new ArrayList<>(categoryFound.get(0).getItems());
        }
        else{
            result = categoryFound.get(0).getItems()
                .stream()
                .limit(limit)
                .collect(Collectors.toList());
        }
        return result;
    }
}
