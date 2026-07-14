package com.restaurant.app;

import com.restaurant.exception.DuplicateItemException;
import com.restaurant.exception.InvalidOrderException;
import com.restaurant.exception.ItemNotFoundException;
import com.restaurant.model.Bill;
import com.restaurant.model.Customer;
import com.restaurant.model.MenuItem;
import com.restaurant.model.Order;
import com.restaurant.repository.InMemoryMenuRepository;
import com.restaurant.repository.MenuRepository;
import com.restaurant.service.BillingService;
import com.restaurant.service.CustomerService;
import com.restaurant.service.MenuService;
import com.restaurant.service.OrderService;
import com.restaurant.ui.ReceiptPrinter;

import java.util.Scanner;

/**
 * The console entry point. Its only job is to read input, call the
 * right service, and hand the result to the printer - it holds no
 * business rules of its own, and every service call that can fail
 * is guarded so a bad input never crashes the program.
 */
public class RestaurantApp {

    private final Scanner input = new Scanner(System.in);

    private final MenuService menuService;
    private final CustomerService customerService = new CustomerService();
    private final OrderService orderService = new OrderService();
    private final BillingService billingService = new BillingService(0.10, 0.05);

    private Customer currentCustomer;
    private Order currentOrder;

    public RestaurantApp() {
        MenuRepository repository = new InMemoryMenuRepository();
        this.menuService = new MenuService(repository);
        seedMenu();
    }

    private void seedMenu() {
        menuService.addItem("B101", "Chicken Burger", "Burger", 1200, true);
        menuService.addItem("P101", "Cheese Pizza", "Pizza", 1800, true);
        menuService.addItem("D101", "Coca Cola", "Drink", 350, true);
    }

    public void run() {
        boolean running = true;

        while (running) {
            System.out.println("\n===== RESTAURANT ORDERING SYSTEM =====");
            System.out.println("1. Staff");
            System.out.println("2. Customer");
            System.out.println("3. Exit");
            int choice = readInt("Enter Choice: ");

            switch (choice) {
                case 1 -> runStaffMenu();
                case 2 -> runCustomerMenu();
                case 3 -> {
                    running = false;
                    System.out.println("Thank You!");
                }
                default -> System.out.println("Invalid Choice!");
            }
        }

        input.close();
    }

    // ================= STAFF =================

    private void runStaffMenu() {
        boolean staffRunning = true;

        while (staffRunning) {
            System.out.println("\n===== STAFF MENU =====");
            System.out.println("1. Add Item");
            System.out.println("2. Display Menu");
            System.out.println("3. Search Item");
            System.out.println("4. Update Price");
            System.out.println("5. Update Availability");
            System.out.println("6. Remove Item");
            System.out.println("7. Back");
            int choice = readInt("Enter Choice: ");

            try {
                switch (choice) {
                    case 1 -> addMenuItem();
                    case 2 -> ReceiptPrinter.printMenu(menuService.getFullMenu());
                    case 3 -> searchMenuItem();
                    case 4 -> updateMenuPrice();
                    case 5 -> updateMenuAvailability();
                    case 6 -> removeMenuItem();
                    case 7 -> staffRunning = false;
                    default -> System.out.println("Invalid Choice!");
                }
            } catch (ItemNotFoundException | DuplicateItemException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addMenuItem() {
        String id = readLine("Item ID: ");
        String name = readLine("Item Name: ");
        String category = readLine("Category: ");
        double price = readDouble("Price: ");
        boolean available = readYesNo("Available (y/n): ");

        menuService.addItem(id, name, category, price, available);
        System.out.println(name + " added successfully");
    }

    private void searchMenuItem() {
        String id = readLine("Enter Item ID: ");
        MenuItem item = menuService.findById(id);
        System.out.println(item);
    }

    private void updateMenuPrice() {
        String id = readLine("Enter Item ID: ");
        double price = readDouble("New Price: ");
        menuService.updatePrice(id, price);
        System.out.println("Price updated successfully");
    }

    private void updateMenuAvailability() {
        String id = readLine("Enter Item ID: ");
        boolean available = readYesNo("Available (y/n): ");
        menuService.updateAvailability(id, available);
        System.out.println("Availability updated successfully");
    }

    private void removeMenuItem() {
        String id = readLine("Enter Item ID: ");
        menuService.removeItem(id);
        System.out.println("Item removed successfully");
    }

    // ================= CUSTOMER =================

    private void runCustomerMenu() {
        boolean customerRunning = true;

        while (customerRunning) {
            System.out.println("\n===== CUSTOMER MENU =====");
            System.out.println("1. View Menu");
            System.out.println("2. Register Customer");
            System.out.println("3. Place Order");
            System.out.println("4. Print Bill");
            System.out.println("5. Back");
            int choice = readInt("Enter Choice: ");

            try {
                switch (choice) {
                    case 1 -> ReceiptPrinter.printMenu(menuService.getAvailableMenu());
                    case 2 -> registerCustomer();
                    case 3 -> placeOrder();
                    case 4 -> printCurrentBill();
                    case 5 -> customerRunning = false;
                    default -> System.out.println("Invalid Choice!");
                }
            } catch (ItemNotFoundException | InvalidOrderException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void registerCustomer() {
        String name = readLine("Enter Customer Name: ");
        String phone = readLine("Enter Phone Number: ");

        currentCustomer = customerService.register(name, phone);
        currentOrder = null; // starting fresh - no leftover order from a previous customer

        System.out.println("Customer Registered Successfully!");
        System.out.println("Your Customer ID is: " + currentCustomer.getCustomerId());
    }

    private void placeOrder() {
        if (currentCustomer == null) {
            throw new InvalidOrderException("Please register first!");
        }
        if (currentOrder == null) {
            currentOrder = orderService.startOrder(currentCustomer);
        }

        boolean ordering = true;

        while (ordering) {
            ReceiptPrinter.printMenu(menuService.getAvailableMenu());
            String itemName = readLine("Enter Item Name: ");

            try {
                MenuItem item = menuService.findAvailableByName(itemName);
                int quantity = readInt("Enter Quantity: ");
                orderService.addItemToOrder(currentOrder, item, quantity);
                System.out.println("Added: " + item.getItemName() + " x" + quantity);
            } catch (ItemNotFoundException | InvalidOrderException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            String again = readLine("Order another item? (y/n): ");
            if (!again.equalsIgnoreCase("y")) {
                ordering = false;
            }
        }

        orderService.confirmOrder(currentOrder);
        System.out.println("\nOrder confirmed!");
        printCurrentBill();
    }

    private void printCurrentBill() {
        if (currentOrder == null || currentOrder.isEmpty()) {
            throw new InvalidOrderException("No order has been placed.");
        }
        Bill bill = billingService.generateBill(currentOrder);
        ReceiptPrinter.printBill(bill);
    }

    // ================= INPUT HELPERS =================
    // Every helper loops until it gets valid input instead of
    // crashing the whole program on a bad keystroke.

    private String readLine(String prompt) {
        System.out.print(prompt);
        return input.nextLine();
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = input.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = input.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = input.nextLine().trim();
            if (line.equalsIgnoreCase("y") || line.equalsIgnoreCase("true")) {
                return true;
            }
            if (line.equalsIgnoreCase("n") || line.equalsIgnoreCase("false")) {
                return false;
            }
            System.out.println("Please enter y or n.");
        }
    }
}
