package com.restaurant.service;

import com.restaurant.model.Customer;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Owns customer ID generation so no two customers can ever collide,
 * even if this were called from multiple threads.
 */
public class CustomerService {

    private final AtomicInteger idCounter = new AtomicInteger(101);

    public Customer register(String name, String phone) {
        String id = "C" + idCounter.getAndIncrement();
        return new Customer(id, name, phone);
    }
}
