package christmas;

import christmas.controller.ReservationController;
import christmas.controller.ReservationRequestParser;
import christmas.ui.InputView;
import christmas.ui.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ReservationController reservationController = new ReservationController(
                new ReservationRequestParser(outputView),
                inputView,
                outputView);
        reservationController.getReservation();
    }
}
