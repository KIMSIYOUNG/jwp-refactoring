package kitchenpos.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kitchenpos.dao.OrderTableDao;
import kitchenpos.domain.OrderTable;
import kitchenpos.validation.OrderTableValidator;

@Service
public class TableService {
    private final OrderTableDao orderTableDao;
    private final OrderTableValidator orderTableValidator;

    public TableService(OrderTableDao orderTableDao, OrderTableValidator orderTableValidator) {
        this.orderTableDao = orderTableDao;
        this.orderTableValidator = orderTableValidator;
    }

    @Transactional
    public OrderTable create(final OrderTable orderTable) {
        orderTable.setId(null);
        orderTable.setTableGroupId(null);
        return orderTableDao.save(orderTable);
    }

    public List<OrderTable> list() {
        return orderTableDao.findAll();
    }

    @Transactional
    public OrderTable changeEmpty(final Long orderTableId) {
        OrderTable validOrderTable = orderTableValidator.changeEmpty(orderTableId);

        validOrderTable.changeEmptyTable();

        return orderTableDao.save(validOrderTable);
    }

    @Transactional
    public OrderTable changeNumberOfGuests(final Long orderTableId, final OrderTable orderTable) {
        OrderTable validOrderTable = orderTableValidator.changeNumberOfGuests(orderTableId, orderTable);

        validOrderTable.setNumberOfGuests(validOrderTable.getNumberOfGuests());

        return orderTableDao.save(validOrderTable);
    }
}
