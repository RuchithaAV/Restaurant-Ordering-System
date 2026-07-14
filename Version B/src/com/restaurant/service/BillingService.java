package com.restaurant.service;

import com.restaurant.exception.InvalidOrderException;
import com.restaurant.model.Bill;
import com.restaurant.model.Order;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Tax and discount rates live here, once, instead of being passed
 * around as magic numbers every time a bill is created.
 */
public class BillingService {

    private final AtomicInteger billCounter = new AtomicInteger(101);
    private final double taxRate;
    private final double discountRate;

    public BillingService(double taxRate, double discountRate) {
        this.taxRate = taxRate;
        this.discountRate = discountRate;
    }

    public Bill generateBill(Order order) {
        if (order.isEmpty()) {
            throw new InvalidOrderException("Cannot bill an order with no items");
        }
        return new Bill("BL" + billCounter.getAndIncrement(), order, taxRate, discountRate);
    }
}
