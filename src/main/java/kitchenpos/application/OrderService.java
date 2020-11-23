package kitchenpos.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kitchenpos.dao.OrderDao;
import kitchenpos.dao.OrderLineItemDao;
import kitchenpos.domain.Order;
import kitchenpos.domain.OrderLineItem;
import kitchenpos.domain.OrderStatus;
import kitchenpos.validation.OrderValidation;

@Service
public class OrderService {
    private final OrderLineItemDao orderLineItemDao;
    private final OrderValidation orderValidation;
    private final OrderDao orderDao;

    public OrderService(
        OrderDao orderDao,
        OrderLineItemDao orderLineItemDao,
        OrderValidation orderValidation
    ) {
        this.orderDao = orderDao;
        this.orderLineItemDao = orderLineItemDao;
        this.orderValidation = orderValidation;
    }

    @Transactional
    public Order create(final Order order) {
        Order validOrder = orderValidation.create(order);
        validOrder.startCooking();
        final Order savedOrder = orderDao.save(order);

        final Long orderId = savedOrder.getId();
        final List<OrderLineItem> savedOrderLineItems = new ArrayList<>();
        for (final OrderLineItem orderLineItem : validOrder.getOrderLineItems()) {
            orderLineItem.setOrderId(orderId);
            savedOrderLineItems.add(orderLineItemDao.save(orderLineItem));
        }
        savedOrder.setOrderLineItems(savedOrderLineItems);

        return savedOrder;
    }

    public List<Order> list() {
        List<Order> orders = orderDao.findAll();
        orders.forEach(order -> order.setOrderLineItems(orderLineItemDao.findAllByOrderId(order.getId())));

        return orders;
    }

    @Transactional
    public Order changeOrderStatus(final Long orderId, final Order order) {
        Order validOrder = orderValidation.changeOrderStatus(orderId);

        validOrder.changeStatus(OrderStatus.valueOf(order.getOrderStatus()));
        orderDao.save(validOrder);
        validOrder.setOrderLineItems(orderLineItemDao.findAllByOrderId(orderId));

        return validOrder;
    }
}
