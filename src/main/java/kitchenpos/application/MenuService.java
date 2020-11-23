package kitchenpos.application;

import kitchenpos.dao.MenuDao;
import kitchenpos.dao.MenuGroupDao;
import kitchenpos.dao.MenuProductDao;
import kitchenpos.dao.ProductDao;
import kitchenpos.domain.Menu;
import kitchenpos.domain.MenuProduct;
import kitchenpos.domain.Product;
import kitchenpos.validation.MenuValidator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MenuService {
    private final MenuDao menuDao;
    private final MenuProductDao menuProductDao;
    private final MenuValidator menuValidator;

    public MenuService(
        MenuDao menuDao,
        MenuProductDao menuProductDao,
        MenuValidator menuValidator
    ) {
        this.menuDao = menuDao;
        this.menuProductDao = menuProductDao;
        this.menuValidator = menuValidator;
    }

    @Transactional
    public Menu create(final Menu menu) {
        Menu validatedMenu = menuValidator.create(menu);
        Menu savedMenu = menuDao.save(validatedMenu);

        Long menuId = savedMenu.getId();
        List<MenuProduct> savedMenuProducts = new ArrayList<>();
        for (final MenuProduct menuProduct : validatedMenu.getMenuProducts()) {
            menuProduct.setMenuId(menuId);
            savedMenuProducts.add(menuProductDao.save(menuProduct));
        }
        savedMenu.setMenuProducts(savedMenuProducts);

        return savedMenu;
    }

    public List<Menu> list() {
        List<Menu> menus = menuDao.findAll();

        for (Menu menu : menus) {
            menu.setMenuProducts(menuProductDao.findAllByMenuId(menu.getId()));
        }

        return menus;
    }
}
