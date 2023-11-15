package christmas.ui;

import christmas.domain.Reservation;
import christmas.domain.enums.Menu;
import christmas.domain.enums.event.DiscountEvent;
import christmas.domain.enums.event.Gift;
import christmas.dto.EventProcessedDto;
import christmas.ui.view.ConsoleView;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OutputView extends ConsoleView {

    private EventProcessedDto eventProcessedDto;

    public void printDetails(EventProcessedDto eventProcessedDto) {
        this.eventProcessedDto = eventProcessedDto;
        printReservationInfo();
        printOrderAmount();
        printFreeGift();
        printBenefitDetails();
        printTotalBenefitAmount();
        printTotalAmount();
        printEventBadge();
    }

    private void printReservationInfo() {
        Reservation reservation = eventProcessedDto.reservation();
        out("12월 " + reservation.getDate() + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        out("");
        Map<Menu, Long> itemCounts = reservation.getMenus().stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        out("<주문 메뉴>");
        itemCounts.forEach((menu, count) -> out(menu.getMenuName() + " " + itemCounts.get(menu) + "개"));
        out("");
    }

    private void printOrderAmount() {
        out("<할인 전 총주문 금액>");
        out(parsePositiveWonFormat(eventProcessedDto.totalOrderAmount()));
        out("");
    }

    private void printFreeGift() {
        out("<증정 메뉴>");
        out(eventProcessedDto.gifts().getName());
        out("");
    }

    private void printBenefitDetails() {
        Reservation reservation = eventProcessedDto.reservation();
        int date = reservation.getDate();
        List<Menu> menus = reservation.getMenus();
        out("<혜택 내역>");
        if (isNoneBenefit()) {
            return;
        }
        eventProcessedDto.discountEvents().stream()
                .forEach(discountEvent ->
                        out(discountEvent.getName()
                                + ": "
                                + parseNegativeWonFormat(discountEvent.getDiscount(date, menus))));
        if (eventProcessedDto.gifts() != Gift.NONE) {
            out("증정 이벤트: " + parseNegativeWonFormat(eventProcessedDto.gifts().getBenefitAmount()));
        }
        out("");
    }

    private boolean isNoneBenefit() {
        if (eventProcessedDto.discountEvents().contains(DiscountEvent.NONE)
                && eventProcessedDto.gifts() == Gift.NONE) {
            out("없음");
            out("");
            return true;
        }
        return false;
    }

    private void printTotalBenefitAmount() {
        out("<총혜택 금액>");
        out(parseNegativeWonFormat(eventProcessedDto.totalBenefitAmount()));
        out("");
    }

    private void printTotalAmount() {
        out("<할인 후 예상 결제 금액>");
        out(parsePositiveWonFormat(eventProcessedDto.getExpectedPaymentAmount()));
        out("");
    }

    private void printEventBadge() {
        out("<12월 이벤트 배지>");
        out(eventProcessedDto.badge().getName());
    }

    public void printError(String message) {
        out("[ERROR] " + message);
    }

    private String parsePositiveWonFormat(int amount) {
        return String.format("%,d", amount) + "원";
    }

    private String parseNegativeWonFormat(int amount) {
        return String.format("%,d", -amount) + "원";
    }
}
