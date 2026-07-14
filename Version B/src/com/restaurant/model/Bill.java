package com.restaurant.model;

/**
 * A bill is a read-only financial view over an order: it derives
 * tax, discount and grand total from the order rather than storing
 * them separately, so the numbers can never drift out of sync with
 * what was actually ordered.
 */
public class Bill {

    private final String billId;
    private final Order order;
    private final double taxRate;
    private final double discountRate;

    public Bill(String billId, Order order, double taxRate, double discountRate) {
        this.billId = billId;
        this.order = order;
        this.taxRate = taxRate;
        this.discountRate = discountRate;
    }

    public String getBillId() {
        return billId;
    }

    public Order getOrder() {
        return order;
    }

    public double getSubtotal() {
        return order.calculateSubtotal();
    }

    public double getTax() {
        return getSubtotal() * taxRate;
    }

    public double getDiscount() {
        return getSubtotal() * discountRate;
    }

    public double getGrandTotal() {
        return getSubtotal() + getTax() - getDiscount();
    }
}
