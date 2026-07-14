public class Order {
    private String orderId;
    private Customer customer;
    private String orderStatus;
    private int orderQuantity;
    private MenuItem menuItem;

    public Order(String orderId, Customer customer, String orderStatus, int orderQuantity, MenuItem menuItem) {
        this.orderId = orderId;
        this.customer = customer;
        this.orderStatus = orderStatus;
        this.orderQuantity = orderQuantity;
        this.menuItem = menuItem;
    }

    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomerId(Customer customer) {
        this.customer = customer;
    }
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }
    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }
    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }


   public double calculateTotalPrice(){
       return menuItem.getPrice() * orderQuantity;
   }

   public void displayOrder(){
       System.out.println("----------- ORDER -----------");
       System.out.println("Order Id : " + this.orderId);
       System.out.println("Customer Name : " + customer.getCustomerName());
       System.out.println("Customer Id : " + customer.getCustomerId());
       System.out.println("Menu Item : " + menuItem.getItemName());
       System.out.println("Order Status : " + this.orderStatus);
       System.out.println("Order Quantity : " + this.orderQuantity);
       System.out.println("Total Price : Rs " + calculateTotalPrice());
       System.out.println("----------------------");
   }

}



