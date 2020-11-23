package kitchenpos.application.fixture;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import kitchenpos.domain.Menu;
import kitchenpos.domain.Product;

public class MenuFixture {

    public static Menu createWithPrice(BigDecimal price) {
        return Menu.create(null, price, null, null);
    }

    public static Menu createWithNotExistProductId(Long menuGroupId) {
        return Menu.create(
            null,
            BigDecimal.valueOf(10000L),
            menuGroupId,
            Arrays.asList(MenuProductFixture.createNotExistsProduct())
        );
    }

    public static Menu createWithMenuPriceAndProducts(int menuPrice, List<Product> products, Long menuGroupId) {
        return Menu.create(
            "후라이드 치킨",
            BigDecimal.valueOf(menuPrice),
            menuGroupId,
            MenuProductFixture.create(products)
        );
    }

    public static Menu createWithMenuPriceAndProducts(int menuPrice, Long menuGroupId, List<Product> products) {
        return Menu.create(
            "TEST_MENU_NAME",
            BigDecimal.valueOf(menuPrice),
            menuGroupId,
            MenuProductFixture.create(products)
        );
    }
}
