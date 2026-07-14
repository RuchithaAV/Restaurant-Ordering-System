public class Bill {
    private String billId;
    private Order order;
    private double taxRate;
    private double discountRate;
    private Customer customer;
    private MenuItem menuItem;

    public Bill(String billId, Order order, double taxRate, double discountRate) {
        this.billId = billId;
        this.taxRate = taxRate;
        this.discountRate = discountRate;
        this.order = order;
    }
    public String getBillId() {
        return billId;
    }
    public void setBillId(String billId) {
        this.billId = billId;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getDiscountRate() {
        return discountRate;
    }
    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public double calculateSubtotal(){
        return order.calculateTotalPrice();
    }

    public double tax(){

        return calculateSubtotal()*taxRate;
    }

    public double discount(){
        return calculateSubtotal()*discountRate;
    }

    public double grandTotal(){
        return calculateSubtotal()+tax()+discount();
    }

    public double displayBill(){
        System.out.println("---------- BILL ----------");
        System.out.println("Bill ID: " + billId);
        System.out.println("Customer: " + order.getCustomer().getCustomerName());
        System.out.println("Item: " + order.getMenuItem().getItemName());
        System.out.println("Quantity: " + order.getOrderQuantity());
        System.out.println("Subtotal: " + order.calculateTotalPrice());
        System.out.println("Tax : " + tax());
        System.out.println("Discount : " + discount());
        System.out.println("Grand Total : " + grandTotal());
        System.out.println("--------------------------");

        return 0;
    }
}
