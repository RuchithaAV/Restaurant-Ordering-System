package com.restaurant.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A customer's order. Can hold any number of order lines, so a
 * customer is never limited to ordering a single item.
 */
public class Order {

    private final String orderId;
    private final Customer customer;
    private final List<OrderLine> orderLines;
    private final LocalDateTime createdAt;
    private OrderStatus status;

    public Order(String orderId, Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("An order must belong to a registered customer");
        }

        this.orderId = orderId;
        this.customer = customer;
        this.orderLines = new ArrayList<>();
        this.status = OrderStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    public void addLine(OrderLine line) {
        orderLines.add(line);
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderLine> getOrderLines() {
        return Collections.unmodifiableList(orderLines);
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public double calculateSubtotal() {
        double subtotal = 0;
        for (OrderLine line : orderLines) {
            subtotal += line.getLineTotal();
        }
        return subtotal;
    }

    public boolean isEmpty() {
        return orderLines.isEmpty();
    }
}
