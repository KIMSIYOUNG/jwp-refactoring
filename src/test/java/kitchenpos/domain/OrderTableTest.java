package kitchenpos.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTableTest {

    @DisplayName("static method로 생성하는 경우 id가 null인 형태로 생성된다.")
    @Test
    void create() {
        OrderTable orderTable = OrderTable.create(1L, 3, false);

        assertThat(orderTable.getId()).isNull();
    }

    @DisplayName("tableGroup없이 테이블을 변경하려하면 예외가 발생한다.")
    @Test
    void changeFullWithNull() {
        OrderTable orderTable = OrderTable.create(1L, 3, false);

        assertThatThrownBy(() -> orderTable.changeFull(null))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("changeFull이 정상적으로 동작한다.")
    @Test
    void changeFull() {
        OrderTable orderTable = OrderTable.create(null, 3, true);
        orderTable.changeFull(1L);

        assertAll(
            () -> assertThat(orderTable.isEmpty()).isFalse(),
            () -> assertThat(orderTable.getTableGroupId()).isEqualTo(1L)
        );
    }

    @DisplayName("테이블을 정상적으로 비울 수 있다.")
    @Test
    void changeEmptyTable() {
        OrderTable orderTable = OrderTable.create(null, 3, false);
        orderTable.changeEmptyTable();

        assertAll(
            () -> assertThat(orderTable.getTableGroupId()).isNull(),
            () -> assertThat(orderTable.isEmpty()).isTrue()
        );
    }
}