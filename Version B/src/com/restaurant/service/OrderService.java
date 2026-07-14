package com.restaurant.service;

import com.restaurant.exception.InvalidOrderException;
import com.restaurant.model.Customer;
import com.restaurant.model.MenuItem;
import com.restaurant.model.Order;
import com.restaurant.model.OrderLine;
import com.restaurant.model.OrderStatus;

import java.util.concurrent.atomic.AtomicInteger;

public class OrderService {

    private final AtomicInteger orderCounter = new AtomicInteger(101);

    public Order startOrder(Customer customer) {
        if (customer == null) {
            throw new InvalidOrderException("Cannot start an order without a registered customer");
        }
        return new Order("O" + orderCounter.getAndIncrement(), customer);
    }

    public void addItemToOrder(Order order, MenuItem item, int quantity) {
        if (!item.isAvailable()) {
            throw new InvalidOrderException(item.getItemName() + " is currently unavailable");
        }
        order.addLine(new OrderLine(item, quantity));
    }

    public void confirmOrder(Order order) {
        if (order.isEmpty()) {
            throw new InvalidOrderException("Cannot confirm an order with no items");
        }
        order.setStatus(OrderStatus.CONFIRMED);
    }
}
