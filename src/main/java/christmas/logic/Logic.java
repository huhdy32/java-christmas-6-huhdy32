package christmas.logic;

import christmas.controller.InputParser;
import christmas.domain.Menu;
import christmas.domain.Reservation;
import christmas.ui.InputView;
import christmas.ui.OutputView;

import java.util.List;

public class Logic {
    public static final int MAX_ORDER_SIZE = 20;
    public static final int MINIMUM_ORDER_AMOUNT_FOR_BENEFIT = 10_000;
    public static final Menu.Category BANNED_SINGLE_CATEGORY = Menu.Category.음료;

    private final InputView inputView;
    private final OutputView outputView;
    private InputParser inputParser = new InputParser();

    public Logic(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Reservation reservation = getReservation();
        outputView.printDetails(reservation);
    }

    private Reservation getReservation() {
        return new Reservation(getOrders(),getReservationDate());
    }

    private List<Menu> getOrders() {
        try {
            String inputOrder = inputView.getOrder();
            return inputParser.convertInputToOrder(inputOrder);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return getOrders();
        }
    }

    private int getReservationDate() {
        try {
            String date = inputView.getDate();
            return inputParser.convertInputToReservationDate(date);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return getReservationDate();
        }
    }
}
