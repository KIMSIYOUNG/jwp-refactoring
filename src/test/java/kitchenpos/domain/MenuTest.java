package kitchenpos.domain;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuTest {

    @DisplayName("금액이 입력되지 않으면, Menu가 생성되지 않는다.")
    @Test
    void withoutPrice() {
        assertThatThrownBy(() -> Menu.create("AAA", null, 1L, new ArrayList<>()))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("금액이 음수이면 메뉴가 생성되지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, -100, -5000})
    void withoutPrice(int money) {
        assertThatThrownBy(() -> Menu.create("AAA", BigDecimal.valueOf(money), 1L, new ArrayList<>()))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("금액이 정상적이면 메뉴가 생성된다.")
    @Test
    void create() {
        Menu menu = Menu.create(null, BigDecimal.ONE, null, null);

        assertThat(menu).isNotNull();
    }

    @DisplayName("가격 비교에서 적절한 가격이 아니면, true를 리턴한다.")
    @ParameterizedTest
    @ValueSource(ints = {2000, 1000})
    void isNotValidPrice(int price) {
        Menu menu = Menu.create(null, BigDecimal.valueOf(3000), null, null);

        assertThat(menu.isNotValidPrice(BigDecimal.valueOf(price))).isTrue();
    }

    @DisplayName("적절한 가격이면 false를 리턴한다.")
    @ParameterizedTest
    @ValueSource(ints = {3000, 4000, 5000})
    void isValidPrice(int price) {
        Menu menu = Menu.create(null, BigDecimal.valueOf(3000), null, null);

        assertThat(menu.isNotValidPrice(BigDecimal.valueOf(price))).isFalse();
    }
}