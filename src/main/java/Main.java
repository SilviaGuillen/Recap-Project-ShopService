
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws ProductNotFoundException {

        //create products
        Product product1 = new Product("1", "Apfel");
        Product product2 = new Product("2", "Banana");
        Product product3 = new Product("3", "Melone");



        ProductRepo productRepo = new ProductRepo();
        product1.addProduct(product1);
        product2.addProduct(product2);


        ShopService shopService = new ShopService();





        List<String> order1IDs = new ArrayList<>();
        order1IDs.add("1");
        order1IDs.add("1");
        shopService.addOrder(order1IDs);
        List<String> order2IDs = new ArrayList<>();
        order2IDs.add("2");
        order2IDs.add("2");
        shopService.addOrder(order2IDs);
        shopService.getOrders().get(0).toString();
        System.out.println(shopService);

        //Test Stream
        OrderStatus status1 = OrderStatus.PROCESSING;
        OrderStatus status2 = OrderStatus.IN_DELIVERY;
        List<Order> ordersWithStatus = shopService.orderRepo().getOrders().stream()
                .filter(order -> order.getStatus().equals(status2))
                .toList();
        System.out.println(ordersWithStatus);
        System.out.println(status1);



    }
}
