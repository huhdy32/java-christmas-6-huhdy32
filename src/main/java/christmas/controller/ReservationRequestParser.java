package christmas.controller;

import christmas.domain.enums.Menu;
import christmas.ui.InputView;
import christmas.ui.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputParser {

    private final InputErrorHandler inputErrorHandler = new InputErrorHandler();
    private List<Menu> menus;
    private final InputView inputView;
    private final OutputView outputView;

    public InputParser(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public List<Menu> parseReservationOrder() {
        menus = new ArrayList<>();;
        try {
            String orders = inputView.getOrder();
            Arrays.stream(parseToSingleOrder(orders))
                    .forEach(order -> addSingleOrder(order));
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return parseReservationOrder();
        }
        return menus;
    }

    public int getReservationDate() {
        String inputDate = inputView.getDate();
        int reservationDate;
        try {
            reservationDate = Integer.parseInt(inputDate);
        } catch (NumberFormatException e) {
            return getReservationDate();
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
