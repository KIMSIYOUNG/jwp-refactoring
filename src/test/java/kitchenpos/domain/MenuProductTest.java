package kitchenpos.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuProductTest {

    @DisplayName("static method로 생성된 경우, seq과 menuId는 null로 할당된다.")
    @Test
    void create() {
        MenuProduct menuProduct = MenuProduct.create(1L, 100);

        assertAll(
            () -> assertThat(menuProduct.getSeq()).isNull(),
            () -> assertThat(menuProduct.getMenuId()).isNull()
        );
    }
}