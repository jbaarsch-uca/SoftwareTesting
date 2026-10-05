package InjectionAndMocking;

public interface OrderRepository {

    public void save(Order order);
    public Order findById(String id);
}
