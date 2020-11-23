package kitchenpos.application.fixture;

import java.util.List;
import java.util.stream.Collectors;

import kitchenpos.domain.MenuProduct;
import kitchenpos.domain.Product;

public class MenuProductFixture {

    public static MenuProduct createNotExistsProduct() {
        return MenuProduct.create(10000L, 0);
    }

    public static List<MenuProduct> create(List<Product> price) {
        return price.stream()
            .map(pro -> MenuProduct.create(pro.getId(), 1))
            .collect(Collectors.toList());
    }
}
