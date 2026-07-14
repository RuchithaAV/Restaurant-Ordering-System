import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Menu menu = new Menu();


        menu.addItem(new MenuItem("B101", "Chicken Burger", "Burger", 1200, true));
        menu.addItem(new MenuItem("P101", "Cheese Pizza", "Pizza", 1800, true));
        menu.addItem(new MenuItem("D101", "Coca Cola", "Drink", 350, true));

        Customer customer = null;

        ArrayList<Order> customerOrders = new ArrayList<>();
        boolean running = true;
        int orderCounter = 101;
        int billCounter = 101;

        while (running) {

            System.out.println("\n---------- Restaurant System ---------- ");
            System.out.println("1. Staff");
            System.out.println("2. Customer");
            System.out.println("3. Exit");
            System.out.print("Enter Choice: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {



                case 1:

                    System.out.println("\n---- Staff Catalog ----");
                    System.out.println("1. Add Item");
                    System.out.println("2. Display Menu");
                    System.out.println("3. Search Item");
                    System.out.println("4. Update Price");
                    System.out.println("5. Update Availability");
                    System.out.println("6. Remove Item");
                    System.out.print("Enter Choice: ");

                    int staffChoice = input.nextInt();
                    input.nextLine();

                    switch (staffChoice) {

                        case 1:

                            System.out.print("Item ID: ");
                            String id = input.nextLine();

                            System.out.print("Item Name: ");
                            String name = input.nextLine();

                            System.out.print("Category: ");
                            String category = input.nextLine();

                            System.out.print("Price: ");
                            double price = input.nextDouble();

                            System.out.print("Available (true/false): ");
                            boolean available = input.nextBoolean();
                            input.nextLine();

                            MenuItem item = new MenuItem(id, name, category, price, available);
                            menu.addItem(item);

                            break;

                        case 2:

                            menu.displayMenu();
                            break;

                        case 3:

                            System.out.print("Enter Item ID: ");
                            String searchId = input.nextLine();

                            MenuItem found = menu.searchItem(searchId);

                            if (found != null) {
                                found.displayItem();
                            } else {
                                System.out.println("Item not found.");
                            }

                            break;

                        case 4:

                            System.out.print("Enter Item ID: ");
                            String updateId = input.nextLine();

                            System.out.print("New Price: ");
                            double newPrice = input.nextDouble();
                            input.nextLine();

                            menu.updatePrice(updateId, newPrice);

                            break;

                        case 5:

                            System.out.print("Enter Item ID: ");
                            String availId = input.nextLine();

                            System.out.print("Available (true/false): ");
                            boolean newAvailability = input.nextBoolean();
                            input.nextLine();

                            menu.updateAvailability(availId, newAvailability);

                            break;

                        case 6:

                            System.out.print("Enter Item ID: ");
                            String removeId = input.nextLine();

                            menu.removeItem(removeId);

                            break;

                        default:
                            System.out.println("Invalid Choice!");
                    }

                    break;



                case 2:

                    System.out.println("\n---- Customer Catalog ----");
                    System.out.println("1. View Menu");
                    System.out.println("2. Register Customer");
                    System.out.println("3. Place Order");
                    System.out.println("4. Print Bill");
                    System.out.print("Enter Choice: ");

                    int customerChoice = input.nextInt();
                    input.nextLine();

                    switch (customerChoice) {

                        case 1:

                            menu.displayMenu();
                            break;

                        case 2:

                            String customerId = "C101";

                            System.out.print("Customer Name: ");
                            String customerName = input.nextLine();

                            System.out.print("Phone Number: ");
                            String phone = input.nextLine();

                            customer = new Customer(customerId, customerName, phone);
                            customerOrders.clear();

                            System.out.println("Customer Registered!");
                            System.out.println("Your Customer ID is: " + customerId);

                            break;
                        case 3:

                            if (customer == null) {
                                System.out.println("Please register first!");
                                break;
                            }

                            boolean ordering = true;

                            while (ordering) {

                                menu.displayMenu();

                                System.out.print("Enter Item Name: ");
                                String itemName = input.nextLine();

                                MenuItem orderedItem = menu.searchItemByName(itemName);

                                if (orderedItem == null) {
                                    System.out.println("Item not found!");
                                    continue;
                                }

                                System.out.print("Enter Quantity: ");
                                int quantity = input.nextInt();
                                input.nextLine();

                                Order order = new Order("O" + orderCounter, customer, "Pending", quantity, orderedItem);
                                orderCounter++;
                                customerOrders.add(order);

                                System.out.println("\nOrder placed successfully!\n");
                                order.displayOrder();

                                System.out.print("\nDo you want to order another item? (y/n): ");
                                String again = input.nextLine();

                                if (!again.equalsIgnoreCase("y")) {
                                    ordering = false;
                                }
                            }

                            // Generate the bill once ordering is done
                            double grandTotalAllOrders = 0;

                            for (Order o : customerOrders) {
                                Bill bill = new Bill("BL" + billCounter, o, 0.10, 0.05);
                                billCounter++;
                                bill.displayBill();
                                grandTotalAllOrders += bill.grandTotal();
                            }

                            System.out.println("\n---- Total Bill: Rs. " + grandTotalAllOrders + " ----");

                            break;
                        case 4:

                            if (customerOrders.isEmpty()) {
                                System.out.println("No order has been placed.");
                                break;
                            }

                            for (Order o : customerOrders) {
                                Bill bill = new Bill("BL" + billCounter, o, 0.10, 0.05);
                                billCounter++;
                                bill.displayBill();
                            }

                            break;

                        default:
                            System.out.println("Invalid Choice!");
                    }

                    break;



                case 3:

                    running = false;
                    System.out.println("Thank You!");
                    break;

                default:

                    System.out.println("Invalid Choice!");
            }

        }

        input.close();
    }
}