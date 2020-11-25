package kitchenpos.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

    @DisplayName("static method로 생성하는 경우, ID를 제외한 값이 정상적으로 대입된다.")
    @Test
    void create() {
        Order order = Order.create(1L, OrderStatus.COOKING.name(), LocalDateTime.now(), null);

        assertThat(order.getId()).isNull();
    }

    @DisplayName("조리를 시작하면, 상태가 변경된다.")
    @Test
    void startCooking() {
        Order order = Order.create(1L, OrderStatus.COMPLETION.name(), null, null);
        order.startCooking();

        assertAll(
            () -> assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.COOKING.name()),
            () -> assertThat(order.getOrderedTime()).isCloseTo(LocalDateTime.now(), within(10, ChronoUnit.SECONDS))
        );
    }

    @DisplayName("정상적으로 상태가 변경된다.")
    @Test
    void changeStatus() {
        OrderStatus expected = OrderStatus.MEAL;

        Order order = Order.create(1L, OrderStatus.COMPLETION.name(), null, null);
        order.changeStatus(expected);

        assertThat(order.getOrderStatus()).isEqualTo(expected.name());
    }
}