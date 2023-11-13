package christmas.controller;

import christmas.domain.enums.Menu;

import java.util.List;

public class InputErrorHandler {
    public static final String ILLEGAL_ORDER = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public static final String ILLEGAL_RESERVATION_DATE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    public void validateMenuName(String name) {
        try {
            Menu.valueOf(name);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ILLEGAL_ORDER);
        }
    }
    public void isDuplicatedOrder(List<Menu> menus, Menu menu) {
        if (menus.contains(menu)) {
            throw new IllegalArgumentException(ILLEGAL_ORDER);
        }
    }
    public void validateOrderCount(int orderCount) {
        if (orderCount == 0) {
            throw new IllegalArgumentException(ILLEGAL_ORDER);
        }
    }
}
