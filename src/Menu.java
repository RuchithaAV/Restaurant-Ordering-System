import java.util.ArrayList;

public class Menu {
    private final ArrayList<MenuItem> menuItems;

    public Menu() {
        menuItems = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        menuItems.add(item);
        System.out.println(item.getItemName() + " "  + "added successfully");
    }
    public void displayMenu() {

        System.out.println("\n---------- MENU ---------");

        for (MenuItem item : menuItems) {

            System.out.println("Item ID   : " + item.getItemId());
            System.out.println("Item Name : " + item.getItemName());
            System.out.println("Category  : " + item.getCategory());
            System.out.println("Price     : Rs. " + item.getPrice());
            System.out.println("------------------------------");
        }
    }

    public MenuItem searchItem(String itemId) {
        for (MenuItem item : menuItems) {
            if (item.getItemId().equalsIgnoreCase(itemId)) {
                return item;
            }
        }
        return null;
    }

    public MenuItem searchItemByName(String itemName) {
        for (MenuItem item : menuItems) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public void removeItem(String itemId) {
        MenuItem item = searchItem(itemId);
        {
            if (item != null) {
                menuItems.remove(item);
                System.out.println("Item removed successfully");
            } else {
                System.out.println("Item not found");
            }
        }
    }

    public void updatePrice(String itemId, double newPrice) {
        MenuItem item = searchItem(itemId);
        if (item != null) {
            item.setPrice(newPrice);
            System.out.println("Price updated successfully");
        } else {
            System.out.println("Item not found");
        }

    }

    public void updateAvailability(String itemId, boolean newAvailability) {
        MenuItem item = searchItem(itemId);
        if (item != null) {
            item.setAvailable(newAvailability);
            System.out.println("Available updated successfully");
        } else {
            System.out.println("Item not found");
        }
    }

}