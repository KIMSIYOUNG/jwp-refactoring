package kitchenpos.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Menu {
    private final Long id;
    private final String name;
    private final BigDecimal price;
    private final Long menuGroupId;
    private List<MenuProduct> menuProducts;

    public Menu(
        Long id,
        String name,
        BigDecimal price,
        Long menuGroupId,
        List<MenuProduct> menuProducts
    ) {
        if (Objects.isNull(price) || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.name = name;
        this.price = price;
        this.menuGroupId = menuGroupId;
        this.menuProducts = menuProducts;
    }

    public static Menu create(String name, BigDecimal price, Long menuGroupId, List<MenuProduct> menuProducts) {
        return new Menu(null, name, price, menuGroupId, menuProducts);
    }

    public boolean isNotValidPrice(BigDecimal price) {
        return this.price.compareTo(price) > 0;
    }

    public void setMenuProducts(List<MenuProduct> menuProducts) {
        this.menuProducts = menuProducts;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getMenuGroupId() {
        return menuGroupId;
    }

    public List<MenuProduct> getMenuProducts() {
        return menuProducts;
    }
}
