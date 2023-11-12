package christmas.controller;

import christmas.domain.Menu;
import christmas.logic.Logic;
import christmas.utils.ErrorMessage;

import java.util.List;

public class InputErrorHandler {
    public void validateMenuName(String name) {
        try {
            Menu.valueOf(name);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessage.ILLEGAL_ORDER);
        }
    }

    public void isDuplicatedOrder(List<Menu> menus, Menu menu) {
        if (menus.contains(menu)) {
            throw new IllegalArgumentException(ErrorMessage.ILLEGAL_ORDER);
        }
    }

    public void validateOrderCount(int orderCount) {
        if (orderCount == 0) {
            throw new IllegalArgumentException(ErrorMessage.ILLEGAL_ORDER);
        }
    }

    // 1~ 31 사이의 숫자인지 확인
    public void validateDate(int date) {
        if (date > 31 || date < 0) {
            throw new IllegalArgumentException(ErrorMessage.ILLEGAL_RESERVATION_DATE);
        }
    }
    // 주문된 최종 메뉴 형식이 바람직한지 확인
    public void validateMenus(List<Menu> menus) {
        if (validateSize(menus) || validateOrder(menus)) {
            throw new IllegalArgumentException(ErrorMessage.ILLEGAL_ORDER);
        }
    }

    // 주문이 20개 이상인지
    public boolean validateSize(List<Menu> menus) {
        if (menus.size() > Logic.MAX_ORDER_SIZE)
            return true;
        return false;
    }

    // 음료만 주문했는지 확인
    public boolean validateOrder(List<Menu> menus) {
        if (menus.stream()
                .allMatch(menu -> menu.getCategory() == Logic.BANNED_SINGLE_CATEGORY)) {
            return true;
        }
        return false;
    }
}