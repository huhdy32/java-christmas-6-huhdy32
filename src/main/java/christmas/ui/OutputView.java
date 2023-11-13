package christmas.ui;

import christmas.domain.Reservation;
import christmas.ui.view.ConsoleView;

public class OutputView extends ConsoleView {

    private Reservation reservation;

    public void printDetails(Reservation reservation) {
        this.reservation = reservation;
        printEventDate();
        printOrders();
        printOrderAmount();
        printFreeGift();
        printBenefitDetails();
        printTotalBenefitAmount();
        printTotalAmount();
        printEventBadge();
    }

    private void printEventDate() {
        out("12월 " + reservation.getDate() + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    private void printOrders() {
        out("<주문 메뉴>");
    }

    private void printOrderAmount() {
        out("<할인 전 총주문 금액>");
        out(String.format("%,d", reservation.getTotalOrderAmount()) + "원");
    }

    private void printFreeGift() {
        out("<증정 메뉴>");
        out(reservation.getGift());
    }

    private void printBenefitDetails() {
        out("<혜택 내역>");
    }

    private void printTotalBenefitAmount() {
        out("<총혜택 금액>");
        out(parseWonFormat(reservation.getTotalBenefitAmount()));
    }

    private void printTotalAmount() {
        out("<할인 후 예상 결제 금액>");
        out(parseWonFormat(reservation.getTotalPayAmount()));
    }

    private void printEventBadge() {
        out("<12월 이벤트 배지>");
    }

    public void printError(String message) {
        out("[ERROR] " + message);
    }

    private String parseWonFormat(int amount) {
        return String.format("%,d", amount);
    }
}
