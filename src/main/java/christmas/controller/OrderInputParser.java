package christmas.controller;

import christmas.domain.Reservation;
import christmas.domain.enums.Menu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderInputParser {
    public static final String ILLEGAL_ORDER = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private Set<Menu> orderedMenu = new HashSet<>();

    public List<Menu> parseOrder(String orderInput) {
        List<Menu> menus = new ArrayList<>();
        String[] seperatedOrders = orderInput.split(",");
        for (int i = 0; i < seperatedOrders.length; i++) {
            Order order = parseToOrder(seperatedOrders[i]);
            menus.addAll(order.getMenus());
        }
        return Reservation.validateMenus(menus);
    }

    private Order parseToOrder(String singleOrder) {
        String[] order = singleOrder.split("-");
        if (order.length != 2) {
            throw new IllegalArgumentException(ILLEGAL_ORDER + "주문은 '-'로 구분되야 합니다.");
        }
        String orderName = order[0];
        String orderCount = order[1];
        return generateOrder(orderName, orderCount);
    }

    private Order generateOrder(String orderMenu, String orderCount) {
        Menu menu = Menu.getMenu(orderMenu);
        int count;
        try {
            count = Integer.parseInt(orderCount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ILLEGAL_ORDER + "( 주문수량은 숫자여야 합니다.)");
        }
        return new Order(menu, count);
    }

    protected class Order {

        private Menu menu;
        private int count;

        public Order(Menu menu, int count) {
            this.menu = menu;
            this.count = count;
            validateMenuName(menu);
        }

        public List<Menu> getMenus() {
            List<Menu> menus = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                menus.add(menu);
            }
            return menus;
        }

        private void validateMenuName(Menu menu) {
            if (menu == Menu.NONE || orderedMenu.contains(this.menu)) {
                throw new IllegalArgumentException(ILLEGAL_ORDER + " ( 중복 주문 )");
            }
            orderedMenu.add(this.menu);
        }
    }

}
