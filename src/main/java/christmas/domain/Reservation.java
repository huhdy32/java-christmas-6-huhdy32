package christmas.domain;

import christmas.controller.InputErrorHandler;
import christmas.domain.enums.Menu;

import java.util.List;

public class Reservation {
    public static final int MAX_ORDER_SIZE = 20;
    public static final Menu.Category BANNED_SINGLE_CATEGORY = Menu.Category.DRINK;
    private final List<Menu> menus;
    private final int date;

    public Reservation(List<Menu> menus, int date) {
        this.menus = this.validateMenus(menus);
        this.date = this.validateDate(date);
    }

    private List<Menu> validateMenus(List<Menu> menus) {
        if (validateOrder(menus) || validateOrderSize(menus)) {
            throw new IllegalArgumentException(InputErrorHandler.ILLEGAL_ORDER);
        }
        return menus;
    }

    private int validateDate(int date) {
        if (validateRange(date)) {
            throw new IllegalArgumentException(InputErrorHandler.ILLEGAL_RESERVATION_DATE);
        }
            return date;
    }

    private boolean validateRange(int date) {
        if (date > 31 || date < 0) {
            return true;
        }
        return false;
    }

    // 음료만 주문했는지 확인
    private boolean validateOrder(List<Menu> menus) {
        if (menus.stream()
                .allMatch(menu -> menu.getCategory() == BANNED_SINGLE_CATEGORY)) {
            return true;
        }
        return false;
    }
    // 주문이 20개 이상인지
    private boolean validateOrderSize(List<Menu> menus) {
        if (menus.size() > MAX_ORDER_SIZE) {
            return true;
        }
        return false;
    }

    public static Reservation create(List<Menu> menu, int date) {
        return new Reservation(menu, date);
    }

    ///////////////////////////////////////////////////////////

    public int getTotalOrderAmount() {
        return menus.stream()
                .mapToInt(menu -> menu.getCost())
                .sum();
    }
}
