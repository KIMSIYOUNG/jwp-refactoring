package kitchenpos.application.fixture;

import kitchenpos.domain.MenuGroup;

public class MenuGroupFixture {

    public static MenuGroup createWithoutId() {
        return new MenuGroup(null, "TEST_MENU_GROUP_NAME");
    }
}