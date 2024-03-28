import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {
    @Test
    void addOrderTest() throws ProductNotFoundException {
        //GIVEN
        ProductRepo productRepo = new ProductRepo();
        productRepo.addProduct(new Product("1", "Apfel"));
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")), OrderStatus.PROCESSING,ZonedDateTime.now());
        assertEquals(expected.getProducts(), actual.getProducts());
        assertNotNull(expected.getId());
    }

    @Test
    void addOrderTest_whenInvalidProductId_expectNull() throws ProductNotFoundException {
        //GIVEN

        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        assertThrows(NoSuchElementException.class,() -> shopService.addOrder(productsIds));
    }

    @Test
    void getOrderWithStatusTest_ShouldReturnTrue_WhenCalledWithPROCESSING() {
        //GIVEN
        List<Product> products = new ArrayList<>();
        products.add(new Product("1", "Apfel"));
        Order order = new Order("1", products, OrderStatus.PROCESSING,ZonedDateTime.now());
        OrderMapRepo orderMapRepo = new OrderMapRepo();
        orderMapRepo.addOrder(order);
        ShopService shopService = new ShopService();
        shopService.setOrderRepo(orderMapRepo);

        List<Order> expected = new ArrayList<>();
        expected.add(order);
        //WHEN
        List<Order> actual = shopService.getOrdersWithStatus(OrderStatus.PROCESSING);
        //THEN
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getOrderWithStatusTest_ShouldReturnFalse_WhenCalledWithINDELIVERY() {
        //GIVEN
        List<Product> products = new ArrayList<>();
        products.add(new Product("1", "Apfel"));
        Order order = new Order("1", products, OrderStatus.PROCESSING,ZonedDateTime.now());
        OrderMapRepo orderMapRepo = new OrderMapRepo();
        orderMapRepo.addOrder(order);
        ShopService shopService = new ShopService();
        shopService.setOrderRepo(orderMapRepo);

        List<Order> expected = new ArrayList<>();
        //WHEN
        List<Order> actual = shopService.getOrdersWithStatus(OrderStatus.IN_DELIVERY);
        //THEN
        Assertions.assertEquals(expected, actual);
    }
}