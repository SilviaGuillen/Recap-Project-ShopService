import lombok.Getter;
import lombok.Setter;
import lombok.With;

import java.time.ZonedDateTime;
import java.util.*;



@With

public record ShopService() {
    static ProductRepo productRepo = new ProductRepo();
    static OrderRepo orderRepo = new OrderMapRepo();


    public Order addOrder(List<String> productIds) throws ProductNotFoundException {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            products.add(productRepo.getProductById(productId)
                    .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found in ProductRepo")));

        }
        Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderStatus.PROCESSING, ZonedDateTime.now());
        return orderRepo.addOrder(newOrder);
    }

    public List<Order> getOrdersWithStatus(OrderStatus status) {
        return orderRepo.getOrders().stream()
                .filter(order -> order.getStatus().equals(status))
                .toList();
    }

    public void updateOrder(String orderID, OrderStatus newStatus) {
        Order updatedOrder = orderRepo.getOrderById(orderID).withStatus(newStatus);
        orderRepo.removeOrder(orderID);
        orderRepo.addOrder(updatedOrder);
    }

    public void setOrderRepo(OrderMapRepo orderMapRepo) {

    }

    public Map<Object, Object> getOrders() {
        return null;
    }

    public OrderRepo orderRepo() {
        return null;
    }
}