package InjectionAndMocking;

public class Order {

    private String orderId;
    private String productId;
    private int quantity;
    private double price;


    public Order(String orderId, String productId, int quantity, double price) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;


    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getId() {
        return orderId;
    }

    public double getTotalAmount() {
        return price * quantity;
    }


}
