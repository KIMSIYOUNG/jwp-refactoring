package kitchenpos.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import kitchenpos.application.fixture.OrderFixture;
import kitchenpos.application.fixture.OrderTableFixture;
import kitchenpos.dao.OrderDao;
import kitchenpos.dao.OrderTableDao;
import kitchenpos.domain.OrderTable;
import kitchenpos.validation.OrderTableValidator;

@ExtendWith(MockitoExtension.class)
class TableServiceTest {

    private TableService tableService;

    @Mock
    private OrderDao orderDao;

    @Mock
    private OrderTableDao orderTableDao;

    @Mock
    private OrderTableValidator orderTableValidator;

    private List<OrderTable> tables;

    @BeforeEach
    void setUp() {
        tableService = new TableService(orderTableDao, orderTableValidator);

        OrderTable orderTable1 = new OrderTable(1L, 1L, 0, true);
        OrderTable orderTable2 = new OrderTable(2L, null, 0, true);
        OrderTable orderTable3 = new OrderTable(3L, null, 0, true);

        tables = Arrays.asList(orderTable1, orderTable2, orderTable3);
    }

    @DisplayName("테이블을 정상적으로 생성한다.")
    @Test
    void create() {
        OrderTable expected = tables.get(0);
        when(orderTableDao.save(any(OrderTable.class))).thenReturn(expected);
        OrderTable orderTable = tableService.create(OrderTableFixture.createEmptyFieldOrderTable());

        assertThat(orderTable).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("모든 테이블을 조회한다.")
    @Test
    void list() {
        when(orderTableDao.findAll()).thenReturn(tables);
        List<OrderTable> actual = tableService.list();

        assertThat(actual).usingRecursiveComparison().isEqualTo(tables);
    }

    @DisplayName("Empty상태를 변경한다.")
    @Test
    void changeEmptyHappy() {
        OrderTable table = tables.get(0);
        when(orderTableDao.save(any(OrderTable.class))).thenReturn(table);
        when(orderTableValidator.changeEmpty(anyLong())).thenReturn(table);
        OrderTable actual = tableService.changeEmpty(table.getId());

        assertThat(actual.isEmpty()).isTrue();
    }

    @DisplayName("OrderTable이 DB에 없는 경우 예외를 반환한다.")
    @Test
    void changeEmptyNotFound() {
        OrderTable orderTable = tables.get(0);
        when(orderTableValidator.changeEmpty(anyLong())).thenThrow(IllegalArgumentException.class);

        assertThatThrownBy(() -> tableService.changeEmpty(orderTable.getId()))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("식사중이거나, 조리중인 경우에 빈 테이블로 만들 수 없다.")
    @Test
    void changeEmptyAlreadyDoingSomething() {
        OrderTable orderTable = tables.get(0);
        when(orderTableValidator.changeEmpty(anyLong())).thenThrow(IllegalArgumentException.class);

        assertThatThrownBy(() -> tableService.changeEmpty(orderTable.getId()))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("손님의 수를 수정한다.")
    @Test
    void changeNumberOfGuests() {
        OrderTable expected = tables.get(0);
        expected.setNumberOfGuests(18);
        expected.changeFull(expected.getId());
        when(orderTableDao.save(any(OrderTable.class))).thenReturn(expected);
        when(orderTableValidator.changeNumberOfGuests(anyLong(), any())).thenReturn(expected);

        OrderTable actual = tableService.changeNumberOfGuests(expected.getId(), expected);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("손님의 수가 음수인 경우 예외를 반환한다.")
    @Test
    void changeNumberOfGuestsNegativeGuestNumber() {
        OrderTable expected = tables.get(0);
        expected.setNumberOfGuests(-1);
        when(orderTableValidator.changeNumberOfGuests(anyLong(), any())).thenThrow(IllegalArgumentException.class);

        assertThatThrownBy(() -> tableService.changeNumberOfGuests(expected.getId(), expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("해당하는 OrderTable이 없으면 예외를 반환한다.")
    @Test
    void changeNumberOfGuestsNoOrderTable() {
        OrderTable expected = tables.get(0);
        when(orderTableValidator.changeNumberOfGuests(anyLong(), any())).thenThrow(IllegalArgumentException.class);

        assertThatThrownBy(() -> tableService.changeNumberOfGuests(expected.getId(), expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이미 없는 테이블에, 손님의 수를 추가할 수 없다.")
    @Test
    void changeNumberOfGuestsEmptyNumber() {
        OrderTable expected = tables.get(0);
        when(orderTableValidator.changeNumberOfGuests(anyLong(), any())).thenThrow(IllegalArgumentException.class);

        assertThatThrownBy(() -> tableService.changeNumberOfGuests(expected.getId(), expected))
            .isInstanceOf(IllegalArgumentException.class);
    }
}