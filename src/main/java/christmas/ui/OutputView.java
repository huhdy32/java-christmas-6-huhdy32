package christmas.ui;

import christmas.domain.Reservation;
import christmas.domain.enums.Menu;
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
    }

    private void printOrderAmount() {
        out("<할인 전 총주문 금액>");
        out(parseWonFormat(eventProcessedDto.totalOrderAmount()));
    }

    private void printFreeGift() {
        out("<증정 메뉴>");
        out(eventProcessedDto.gifts().getName());
    }

    private void printBenefitDetails() {
        Reservation reservation = eventProcessedDto.reservation();
        int date = reservation.getDate();
        List<Menu> menus = reservation.getMenus();
        out("<혜택 내역>");
        eventProcessedDto.discountEvents().stream()
                .forEach(discountEvent ->
                        out(discountEvent.getName() + " : " + parseWonFormat(discountEvent.getDiscount(date, menus))));
        if (eventProcessedDto.gifts() == Gift.NONE) {
            out("없음");
            return;
        }
        out("증정 이벤트 : " + parseWonFormat(eventProcessedDto.gifts().getBenefitAmount()));
    }

    private void printTotalBenefitAmount() {
        out("<총혜택 금액>");
        out(parseWonFormat(eventProcessedDto.totalBenefitAmount()));
    }

    private void printTotalAmount() {
        out("<할인 후 예상 결제 금액>");
        out(parseWonFormat(eventProcessedDto.getExpectedPaymentAmount()));
    }

    private void printEventBadge() {
        out("<12월 이벤트 배지>");
        out(eventProcessedDto.badge().getName());
    }

    public void printError(String message) {
        out("[ERROR] " + message);
    }

    private String parseWonFormat(int amount) {
        return String.format("%,d", amount) + "원";
    }
}
