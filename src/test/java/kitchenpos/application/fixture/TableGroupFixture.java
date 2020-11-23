package kitchenpos.application.fixture;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import kitchenpos.domain.OrderTable;
import kitchenpos.domain.TableGroup;

public class TableGroupFixture {

    public static TableGroup createEmptyOrderTable() {
        return new TableGroup(
            null,
            null,
            Arrays.asList(OrderTableFixture.createEmptyFieldOrderTable())
        );
    }

    public static TableGroup createTableGroupWithOrderTableSize(int count) {
        return new TableGroup(
            1L,
            LocalDateTime.now(),
            OrderTableFixture.createOrderTableCountBy(count)
        );
    }

    public static TableGroup createTableGroupWithOrderTableSize(List<OrderTable> orderTables) {
        return new TableGroup(
            1L,
            LocalDateTime.now(),
            orderTables
        );
    }

    public static TableGroup createTableGroupWithNotEmptyOrderTableSize(int count) {
        return new TableGroup(
            null,
            LocalDateTime.now(),
            OrderTableFixture.createOrderTableCountBy(count)
        );
    }
}