package com.restaurant.service;

import com.restaurant.exception.ItemNotFoundException;
import com.restaurant.model.MenuItem;
import com.restaurant.repository.MenuRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Business rules around the menu. Never returns null - a missing
 * item is always signalled with ItemNotFoundException, so callers
 * can't forget a null check and crash later.
 */
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public void addItem(String id, String name, String category, double price, boolean available) {
        menuRepository.save(new MenuItem(id, name, category, price, available));
    }

    public List<MenuItem> getFullMenu() {
        return menuRepository.findAll().stream()
                .sorted(Comparator.comparing(MenuItem::getItemId))
                .collect(Collectors.toList());
    }

    public List<MenuItem> getAvailableMenu() {
        return menuRepository.findAll().stream()
                .filter(MenuItem::isAvailable)
                .sorted(Comparator.comparing(MenuItem::getItemName))
                .collect(Collectors.toList());
    }

    public MenuItem findById(String itemId) {
        return menuRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("No menu item found with ID: " + itemId));
    }

    public MenuItem findAvailableByName(String itemName) {
        return menuRepository.findByName(itemName)
                .filter(MenuItem::isAvailable)
                .orElseThrow(() -> new ItemNotFoundException("No available item named: " + itemName));
    }

    public void updatePrice(String itemId, double newPrice) {
        findById(itemId).setPrice(newPrice);
    }

    public void updateAvailability(String itemId, boolean available) {
        findById(itemId).setAvailable(available);
    }

    public void removeItem(String itemId) {
        findById(itemId); // throws ItemNotFoundException if it doesn't exist
        menuRepository.delete(itemId);
    }
}
