package kitchenpos.domain;

import java.util.Objects;

public class OrderTable {
    private Long id;
    private Long tableGroupId;
    private int numberOfGuests;
    private boolean empty;

    public OrderTable() {
    }

    public OrderTable(Long id, Long tableGroupId, int numberOfGuests, boolean empty) {
        this.id = id;
        this.tableGroupId = tableGroupId;
        this.numberOfGuests = numberOfGuests;
        this.empty = empty;
    }

    public static OrderTable create(Long tableGroupId, int numberOfGuests, boolean empty) {
        return new OrderTable(null, tableGroupId, numberOfGuests, empty);
    }

    public void changeFull(Long tableGroupId) {
        if (Objects.isNull(tableGroupId)) {
            throw new IllegalArgumentException();
        }

        this.tableGroupId = tableGroupId;
        this.empty = false;
    }

    public void changeEmptyTable() {
        this.tableGroupId = null;
        this.empty = true;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getTableGroupId() {
        return tableGroupId;
    }

    public void setTableGroupId(Long tableGroupId) {
        this.tableGroupId = tableGroupId;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public boolean isEmpty() {
        return empty;
    }
}
