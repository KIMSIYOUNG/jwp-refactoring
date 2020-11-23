package kitchenpos.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Long id;
    private final Long orderTableId;
    private String orderStatus;
    private LocalDateTime orderedTime;
    private List<OrderLineItem> orderLineItems;

    public Order(
        Long id,
        Long orderTableId,
        String orderStatus,
        LocalDateTime orderedTime,
        List<OrderLineItem> orderLineItems
    ) {
        this.id = id;
        this.orderTableId = orderTableId;
        this.orderStatus = orderStatus;
        this.orderedTime = orderedTime;
        this.orderLineItems = orderLineItems;
    }

    public static Order create(
        Long orderTableId,
        String orderStatus,
        LocalDateTime orderedTime,
        List<OrderLineItem> orderLineItems
    ) {
        return new Order(
            null,
            orderTableId,
            orderStatus,
            orderedTime,
            orderLineItems);
    }

    public void startCooking() {
        this.orderStatus = OrderStatus.COOKING.name();
        this.orderedTime = LocalDateTime.now();
    }

    public void changeStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus.name();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderLineItems(List<OrderLineItem> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }

    public Long getId() {
        return id;
    }

    public Long getOrderTableId() {
        return orderTableId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public LocalDateTime getOrderedTime() {
        return orderedTime;
    }

    public List<OrderLineItem> getOrderLineItems() {
        return orderLineItems;
    }
}
