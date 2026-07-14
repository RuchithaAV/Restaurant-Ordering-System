package com.restaurant.ui;

import com.restaurant.model.Bill;
import com.restaurant.model.MenuItem;
import com.restaurant.model.Order;
import com.restaurant.model.OrderLine;

import java.util.List;

/**
 * All console formatting lives here, separate from the business
 * logic in the service layer. If this became a GUI or web app
 * tomorrow, only this class would need to be replaced.
 */
public final class ReceiptPrinter {

    private ReceiptPrinter() {
    }

    public static void printMenu(List<MenuItem> items) {
        System.out.println("\n========== MENU ==========");
        if (items.isEmpty()) {
            System.out.println("(no items available)");
        }
        for (MenuItem item : items) {
            System.out.println(item);
        }
        System.out.println("===========================");
    }

    public static void printBill(Bill bill) {
        Order order = bill.getOrder();

        System.out.println("\n========== BILL ==========");
        System.out.printf("Bill ID  : %s%n", bill.getBillId());
        System.out.printf("Customer : %s (%s)%n",
                order.getCustomer().getCustomerName(), order.getCustomer().getCustomerId());
        System.out.println("---------------------------");

        for (OrderLine line : order.getOrderLines()) {
            System.out.println(line);
        }

        System.out.println("---------------------------");
        System.out.printf("Subtotal    : Rs. %.2f%n", bill.getSubtotal());
        System.out.printf("Tax         : Rs. %.2f%n", bill.getTax());
        System.out.printf("Discount    : Rs. %.2f%n", bill.getDiscount());
        System.out.printf("Grand Total : Rs. %.2f%n", bill.getGrandTotal());
        System.out.println("===========================");
    }
}
