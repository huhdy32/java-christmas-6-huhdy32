package christmas.controller;

import christmas.domain.Reservation;

public class DateInputParser {
    public static final String ILLEGAL_RESERVATION_DATE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    public int parseDate(String inputDate) {
        int reservationDate;
        try {
            reservationDate = Integer.parseInt(inputDate);
            return Reservation.validateDate(reservationDate);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ILLEGAL_RESERVATION_DATE);
        }
    }
}
