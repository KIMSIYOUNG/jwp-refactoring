package kitchenpos.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderLineItemTest {

    @DisplayName("static method로 생성하는 경우 seq과 orderId는 null로 생성된다.")
    @Test
    void create() {
        OrderLineItem orderLineItem = OrderLineItem.create(1L, 3);

        assertAll(
            () -> assertThat(orderLineItem.getSeq()).isNull(),
            () -> assertThat(orderLineItem.getOrderId()).isNull()
        );
    }
}