import lombok.Builder;
import lombok.Setter;
import lombok.With;

import java.time.ZonedDateTime;
import java.util.List;

@Builder
@With
@Setter
public class Order{

    private final String id;
    private List<Product> products;
    @With
    private OrderStatus status;

    public Order(String id, List<Product> products, OrderStatus status, ZonedDateTime zonedDateTime) {
        this.id = id;
        this.products = products;
        this.status = status;
    }

    @Override
    public String toString() {
        return id + '|' + products + '|'+ status;
    }

    public String getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String id() {
        return id;
    }
}
