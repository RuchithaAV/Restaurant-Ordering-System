package com.restaurant.model;

/**
 * One line of an order: a menu item and the quantity requested.
 * Immutable once created - if the quantity is wrong, the order should
 * never have been allowed to hold this line in the first place.
 */
public class OrderLine {

    private final MenuItem menuItem;
    private final int quantity;

    public OrderLine(MenuItem menuItem, int quantity) {
        if (menuItem == null) {
            throw new IllegalArgumentException("Menu item cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getLineTotal() {
        return menuItem.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return String.format("%-20s x%-3d = Rs. %.2f", menuItem.getItemName(), quantity, getLineTotal());
    }
}
