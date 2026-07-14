package com.restaurant.repository;

import com.restaurant.model.MenuItem;

import java.util.List;
import java.util.Optional;

/**
 * Abstracts how menu items are stored. The service layer only ever
 * talks to this interface, so swapping the in-memory implementation
 * for a file- or database-backed one later requires no changes above
 * this layer.
 */
public interface MenuRepository {
    void save(MenuItem item);
    Optional<MenuItem> findById(String itemId);
    Optional<MenuItem> findByName(String itemName);
    List<MenuItem> findAll();
    void delete(String itemId);
}
