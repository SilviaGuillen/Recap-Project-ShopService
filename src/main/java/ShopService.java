import lombok.RequiredArgsConstructor;
import lombok.With;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@With


public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    public ShopService(ProductRepo productRepo, OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }
    public ShopService() {}

    public Order addOrder(List<String> productIds) throws NoSuchElementException {
        List<Product> products = new ArrayList<>();



        for (String productId : productIds) {
            Product productToOrder = productRepo.getProductById(productId);
            if (productToOrder == null) {
                System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                throw new NoSuchElementException(productId);
            }
            products.add(productToOrder);
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products,OrderStatus.IN_DELIVERY);

        return orderRepo.addOrder(newOrder);
    }

    public List<Order> findOrderByStatus(OrderStatus status) {
        List<Order> orderWithSameStatus = new ArrayList<>();
        orderWithSameStatus = orderRepo.getOrders()
                .stream()
                .filter(order -> order.status() == status)
                .collect(Collectors.toList());

        return orderWithSameStatus;


    }

    public Order updateOrder(String orderId) {
        Order order1 = orderRepo.getOrderById(orderId).withStatus(OrderStatus.IN_DELIVERY);
        orderRepo.removeOrder(orderId);
        return orderRepo.addOrder(order1);


    }

}
