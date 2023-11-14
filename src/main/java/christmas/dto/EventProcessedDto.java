package christmas.dto;

import christmas.domain.Reservation;
import christmas.domain.enums.event.Badge;
import christmas.domain.enums.event.DiscountEvent;
import christmas.domain.enums.event.Gift;

import java.util.List;

public record EventProcessedDto(
        Reservation reservation,
        int totalOrderAmount,
        Gift gifts,
        List<DiscountEvent> discountEvents,
        int totalBenefitAmount,
        int getExpectedPaymentAmount,
        Badge badge) {
}
