package kitchenpos.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kitchenpos.dao.OrderTableDao;
import kitchenpos.dao.TableGroupDao;
import kitchenpos.domain.OrderTable;
import kitchenpos.domain.TableGroup;
import kitchenpos.validation.TableGroupValidator;

@Service
public class TableGroupService {
    private final OrderTableDao orderTableDao;
    private final TableGroupDao tableGroupDao;
    private final TableGroupValidator tableGroupValidator;

    public TableGroupService(
        OrderTableDao orderTableDao,
        TableGroupDao tableGroupDao,
        TableGroupValidator tableGroupValidator
    ) {
        this.orderTableDao = orderTableDao;
        this.tableGroupDao = tableGroupDao;
        this.tableGroupValidator = tableGroupValidator;
    }

    @Transactional
    public TableGroup create(TableGroup tableGroup) {
        TableGroup validTableGroup = tableGroupValidator.create(tableGroup);
        TableGroup savedTableGroup = tableGroupDao.save(tableGroup);

        for (OrderTable savedOrderTable : validTableGroup.getOrderTables()) {
            savedOrderTable.changeFull(savedTableGroup.getId());
            orderTableDao.save(savedOrderTable);
        }

        savedTableGroup.setOrderTables(validTableGroup.getOrderTables());

        return savedTableGroup;
    }

    @Transactional
    public void ungroup(final Long tableGroupId) {
        List<OrderTable> orderTables = tableGroupValidator.ungroup(tableGroupId);

        for (final OrderTable orderTable : orderTables) {
            orderTable.changeEmptyTable();
            orderTableDao.save(orderTable);
        }
    }
}
