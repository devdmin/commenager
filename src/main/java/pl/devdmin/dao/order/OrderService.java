package pl.devdmin.dao.order;


import java.util.Set;

public interface OrderService {
    void save(Order order);
    Set<Order> findAll();
}
