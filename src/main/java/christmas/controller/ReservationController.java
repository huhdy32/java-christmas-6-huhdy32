package christmas.controller;

import christmas.domain.Reservation;
import christmas.domain.enums.Menu;
import christmas.dto.EventProcessedDto;
import christmas.service.EventService;
import christmas.ui.InputView;
import christmas.ui.OutputView;

import java.util.List;

public class ReservationController {
    private final DateInputParser dateInputParser;
    private final OrderInputParser orderInputParser;
    private final InputView inputView;
    private final OutputView outputView;

    private EventService eventService;

    public ReservationController(DateInputParser dateInputParser,
                                 OrderInputParser orderInputParser,
                                 InputView inputView,
                                 OutputView outputView) {
        this.dateInputParser = dateInputParser;
        this.orderInputParser = orderInputParser;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void getReservation() {
        // 예약 일자 입력 받기
        int date = getReservationDate();
        // 주문 목록 입력 받기
        List<Menu> menus = getReservationMenus();
        try {
            // 예약 생성
            Reservation reservation = Reservation.create(menus, date);
            // 이벤트 적용
            eventService = EventService.create(reservation);
            // 적용 결과
            EventProcessedDto eventProcessedDto = eventService.createProcessedReservationDto();
            // 결과 출력
            outputView.printDetails(eventProcessedDto);
        } catch (IllegalStateException e) {
            outputView.printError(e.getMessage());
            getReservation();
        }
    }

    // 예약 메뉴 요청
    private List<Menu> getReservationMenus() {
        try {
            String reservationOrder = inputView.getOrder();
            return orderInputParser.parseOrder(reservationOrder);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return getReservationMenus();
        }
    }

    // 예약 일자 요청
    private int getReservationDate() {
        try {
            String reservationDate = inputView.getDate();
            return dateInputParser.parseDate(reservationDate);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return getReservationDate();
        }
    }
}
