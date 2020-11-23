package kitchenpos.application.fixture;

import java.time.LocalDateTime;

import kitchenpos.domain.Order;
import kitchenpos.domain.OrderStatus;

public class OrderFixture {

    public static Order createWithStatus(Long orderTableId, OrderStatus status) {
        return Order.create(orderTableId, status.name(), LocalDateTime.now(), null);
    }

    public static Order createEmptyFieldOrder() {
        return new Order(null, null, null, null, null);
    }
}
