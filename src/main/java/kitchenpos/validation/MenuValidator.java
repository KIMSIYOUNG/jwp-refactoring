package kitchenpos.validation;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import kitchenpos.dao.MenuGroupDao;
import kitchenpos.dao.ProductDao;
import kitchenpos.domain.Menu;
import kitchenpos.domain.MenuProduct;
import kitchenpos.domain.Product;

@Component
public class MenuValidator {
    private final MenuGroupDao menuGroupDao;
    private final ProductDao productDao;

    public MenuValidator(
        final MenuGroupDao menuGroupDao,
        final ProductDao productDao
    ) {
        this.menuGroupDao = menuGroupDao;
        this.productDao = productDao;
    }

    public Menu create(Menu menu) {
        if (!menuGroupDao.existsById(menu.getMenuGroupId())) {
            throw new IllegalArgumentException();
        }

        final List<MenuProduct> menuProducts = menu.getMenuProducts();

        BigDecimal sum = BigDecimal.ZERO;
        for (final MenuProduct menuProduct : menuProducts) {
            final Product product = productDao.findById(menuProduct.getProductId())
                .orElseThrow(IllegalArgumentException::new);
            sum = sum.add(product.getPrice().multiply(BigDecimal.valueOf(menuProduct.getQuantity())));
        }

        if (menu.isNotValidPrice(sum)) {
            throw new IllegalArgumentException();
        }

        return menu;
    }

}
