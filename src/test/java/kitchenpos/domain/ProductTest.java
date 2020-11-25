package kitchenpos.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ProductTest {

    @DisplayName("가격이 없으면 Product를 생성할 수 없다.")
    @Test
    void nullPrice() {
        assertThatThrownBy(() -> new Product(null, "PRODUCT", null))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Product의 가격은 0 초과의 수여야 한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, -1000})
    void minusPrice(int money) {
        assertThatThrownBy(() -> new Product(null, "PRODUCT", BigDecimal.valueOf(money)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("정상적으로 Product를 생성할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1000, 2000, 3000})
    void create(int money) {
        Product product = new Product(null, "KKK", BigDecimal.valueOf(money));

        assertThat(product.getPrice().intValue()).isEqualTo(money);
    }
}