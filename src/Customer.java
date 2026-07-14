public class Customer {
    private String customerId;
    private String customerName;
    private String customerNumber;

    public Customer(String customerId, String customerName, String customerNumber) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerNumber = customerNumber;
    }
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }
    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public void displayCustomer() {
        System.out.println("---------- Customer Details ----------");
        System.out.println("Customer ID     : " + customerId);
        System.out.println("Customer Name   : " + customerName);
        System.out.println("Mobile Number    : " + customerNumber);
        System.out.println("------------------------");

    }
}
