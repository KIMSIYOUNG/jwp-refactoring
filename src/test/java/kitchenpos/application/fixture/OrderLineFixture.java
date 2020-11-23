package kitchenpos.application.fixture;

import kitchenpos.domain.Menu;
import kitchenpos.domain.OrderLineItem;

public class OrderLineFixture {

    public static OrderLineItem createWithOrderAndMenu(Menu menu, long count) {
        return OrderLineItem.create(menu.getId(), count);
    }
}
