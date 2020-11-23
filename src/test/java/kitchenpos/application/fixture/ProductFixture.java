package kitchenpos.application.fixture;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import kitchenpos.domain.Product;

public class ProductFixture {
    public static Product createWithOutId(BigDecimal price) {
        return new Product(null, "TEST_PRODUCT", price);
    }

    public static List<Product> getProducts(int... price) {
        return Arrays.stream(price)
            .mapToObj(p -> createWithOutId(BigDecimal.valueOf(p)))
            .collect(Collectors.toList());
    }
}
