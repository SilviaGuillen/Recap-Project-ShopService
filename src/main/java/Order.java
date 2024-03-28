import lombok.Builder;
import lombok.With;

import java.util.List;

@Builder
@With

public record Order(
        String id,
        List<Product> products,
        OrderStatus status

) {
    public OrderStatus getOrderStatus() {
    return status;

    }
}
