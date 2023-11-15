package christmas.domain;

import christmas.domain.enums.Menu;

import java.util.List;

import static christmas.controller.DateInputParser.ILLEGAL_RESERVATION_DATE;
import static christmas.controller.OrderInputParser.ILLEGAL_ORDER;

public class Reservation {
    public static final int MAX_ORDER_SIZE = 20;
    public static final Menu.Category BANNED_SINGLE_CATEGORY = Menu.Category.DRINK;
    private final List<Menu> menus;
    private final int date;

    protected Reservation(List<Menu> menus, int date) {
        this.menus = this.validateMenus(menus);
        this.date = this.validateDate(date);
    }

    public static List<Menu> validateMenus(List<Menu> menus) {
        if (validateOrder(menus) || validateOrderSize(menus)) {
            throw new IllegalArgumentException(ILLEGAL_ORDER);
        }
        return menus;
    }

    public static int validateDate(int date) {
        if (validateRange(date)) {
            throw new IllegalArgumentException(ILLEGAL_RESERVATION_DATE);
        }
        return date;
    }

    private static boolean validateRange(int date) {
        if (date > 31 || date < 1) {
            return true;
        }
        return false;
    }

    // 음료만 주문했는지 확인
    private static boolean validateOrder(List<Menu> menus) {
        if (menus.stream()
                .allMatch(menu -> menu.getCategory() == BANNED_SINGLE_CATEGORY)) {
            return true;
        }
        return false;
    }

    // 주문이 20개 이상인지
    private static boolean validateOrderSize(List<Menu> menus) {
        if (menus.size() > MAX_ORDER_SIZE) {
            return true;
        }
        return false;
    }

    public int getTotalOrderAmount() {
        return menus.stream()
                .mapToInt(menu -> menu.getCost())
                .sum();
    }

    public int getDate() {
        return this.date;
    }

    public List<Menu> getMenus() {
        return this.menus;
    }

    public static Reservation create(List<Menu> menu, int date) {
        return new Reservation(menu, date);
    }
}
