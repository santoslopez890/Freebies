package com.freebies.app.repository;

import com.freebies.app.domain.Item;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Item entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("select item from Item item where item.login.login = ?#{principal.username}")
    List<Item> findByLoginIsCurrentUser();
}
