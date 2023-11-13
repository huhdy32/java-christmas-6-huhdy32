package christmas.controller;

import christmas.domain.enums.Menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class InputParser {

    InputErrorHandler inputErrorHandler = new InputErrorHandler();
    List<Menu> menus = new ArrayList<>();

    public List<Menu> convertInputToOrder(String orders) {
        try {
            Arrays.stream(parseToSingleOrder(orders))
                    .forEach(order -> addSingleOrder(order));
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException(InputErrorHandler.ILLEGAL_ORDER);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(InputErrorHandler.ILLEGAL_ORDER);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(InputErrorHandler.ILLEGAL_ORDER);
        }
        inputErrorHandler.validateMenus(menus);
        return menus;
    }

    public int convertInputToReservationDate(String inputDate) {
        int reservationDate;
        try {
            reservationDate = Integer.parseInt(inputDate);
            inputErrorHandler.validateDate(reservationDate);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(InputErrorHandler.ILLEGAL_RESERVATION_DATE);
        }
        return reservationDate;
    }

    private String[] parseToSingleOrder(String orders) {
        return orders.split(",");
    }

    private void addSingleOrder(String singleOrder) {
        String[] order = singleOrder.split("-");
        int orderCount = Integer.parseInt(order[1]);
        inputErrorHandler.validateOrderCount(orderCount);
        addMenu(order[0], orderCount);
    }

    private void addMenu(String name, int orderCount) {
        inputErrorHandler.validateMenuName(name);
        Menu menu = Menu.valueOf(name);
        inputErrorHandler.isDuplicatedOrder(menus, menu);
        for (int i = 0; i < orderCount; i++) {
            menus.add(menu);
        }
    }

}
