package InjectionAndMocking;

public class OrderProcessor {

    private final OrderRepository repository;
    private final PaymentGateway paymentGateway;
    private final InventoryService inventoryService;
    private final NotificationService notificationService;

    public OrderProcessor(OrderRepository repository,
                          PaymentGateway paymentGateway,
                          InventoryService inventoryService,
                          NotificationService notificationService) {
        this.repository = repository;
        this.paymentGateway = paymentGateway;
        this.inventoryService = inventoryService;
        this.notificationService = notificationService;
    }

    public boolean processOrder(Order order, Customer customer) {
        if (!inventoryService.isInStock(order.getProductId(), order.getQuantity())) {
            return false;
        }

        boolean paid = paymentGateway.charge(customer.getCreditCard(), order.getTotalAmount());
        if (paid) {
            repository.save(order);
            notificationService.sendReceipt(customer.getEmail(), order.getId());
            return true;
        }
        return false;
    }

}
