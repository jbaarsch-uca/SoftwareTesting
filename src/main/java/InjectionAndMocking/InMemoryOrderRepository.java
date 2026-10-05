package InjectionAndMocking;

import java.util.HashMap;
import java.util.Map;

// Hand-coded implementation of the domain interface
public class InMemoryOrderRepository implements OrderRepository {
    private final Map<String, Order> database = new HashMap<>();

    @Override
    public void save(Order order) {
        database.put(order.getId(), order);
    }

    @Override
    public Order findById(String id) {
        return database.get(id);
    }

    public int getCount() {
        return database.size();
    }
}