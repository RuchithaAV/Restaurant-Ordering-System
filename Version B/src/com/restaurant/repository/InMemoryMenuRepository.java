package com.restaurant.repository;

import com.restaurant.exception.DuplicateItemException;
import com.restaurant.model.MenuItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryMenuRepository implements MenuRepository {

    private final Map<String, MenuItem> itemsById = new LinkedHashMap<>();

    @Override
    public void save(MenuItem item) {
        String key = item.getItemId().toUpperCase();
        if (itemsById.containsKey(key)) {
            throw new DuplicateItemException("An item with ID " + item.getItemId() + " already exists");
        }
        itemsById.put(key, item);
    }

    @Override
    public Optional<MenuItem> findById(String itemId) {
        if (itemId == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(itemsById.get(itemId.toUpperCase()));
    }

    @Override
    public Optional<MenuItem> findByName(String itemName) {
        if (itemName == null) {
            return Optional.empty();
        }
        return itemsById.values().stream()
                .filter(item -> item.getItemName().equalsIgnoreCase(itemName))
                .findFirst();
    }

    @Override
    public List<MenuItem> findAll() {
        return new ArrayList<>(itemsById.values());
    }

    @Override
    public void delete(String itemId) {
        findById(itemId).ifPresent(item -> itemsById.remove(item.getItemId().toUpperCase()));
    }
}
