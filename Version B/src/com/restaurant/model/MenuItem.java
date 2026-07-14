package com.restaurant.model;

import java.util.Objects;

/**
 * Represents a single sellable item on the restaurant's menu.
 * Enforces its own invariants (non-negative price, non-blank id)
 * so it can never exist in an invalid state.
 */
public class MenuItem {

    private final String itemId;
    private String itemName;
    private String category;
    private double price;
    private boolean available;

    public MenuItem(String itemId, String itemName, String category, double price, boolean available) {
        if (itemId == null || itemId.isBlank()) {
            throw new IllegalArgumentException("Item ID cannot be empty");
        }
        if (itemName == null || itemName.isBlank()) {
            throw new IllegalArgumentException("Item name cannot be empty");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
        this.price = price;
        this.available = available;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;
        MenuItem other = (MenuItem) o;
        return itemId.equalsIgnoreCase(other.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId.toLowerCase());
    }

    @Override
    public String toString() {
        return String.format("%-6s | %-20s | %-10s | Rs. %8.2f | %s",
                itemId, itemName, category, price, available ? "Available" : "Unavailable");
    }
}
