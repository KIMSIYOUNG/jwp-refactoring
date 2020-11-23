package kitchenpos.application.fixture;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import kitchenpos.domain.OrderTable;

public class OrderTableFixture {

    public static List<OrderTable> createOrderTableCountBy(int count) {
        return LongStream.range(1, count + 1)
            .mapToObj((i) -> new OrderTable(i, i, 0, true))
            .collect(Collectors.toList());
    }

    public static OrderTable createBeforeSave() {
        return OrderTable.create(null, 0, true);
    }

    public static OrderTable createBeforeSave(Long tableGroup) {
        return OrderTable.create(tableGroup, 0, true);
    }

    public static OrderTable createBeforeSaveWithFull(Long tableGroup) {
        return OrderTable.create(tableGroup, 0, false);
    }

    public static OrderTable createGroupedOrderTable(Long groupId) {
        return OrderTable.create(groupId, 0, true);
    }

    public static OrderTable createEmptyFieldOrderTable() {
        return OrderTable.create(null, 0, true);
    }
}
