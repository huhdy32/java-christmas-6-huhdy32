package christmas.dto;

import christmas.domain.enums.Menu;
import christmas.domain.enums.event.Badge;
import christmas.domain.enums.event.DiscountEvent;
import christmas.domain.enums.event.Gift;

import java.util.List;

public class ReservationProcessedResponseDto {
    private final List<Menu> orders;
    private final int totalOrderAmount;
    private final Gift gifts;
    private final List<DiscountEvent> discountEvents;
    private final int totalBenefitAmount;
    private final int getExpectedPaymentAmount;
    private final Badge badge;

    public ReservationProcessedResponseDto(
            List<Menu> orders,
            int totalOrderAmount,
            Gift gifts,
            List<DiscountEvent> discountEvents,
            int totalBenefitAmount,
            int getExpectedPaymentAmount,
            Badge badge) {
        this.orders = orders;
        this.totalOrderAmount = totalOrderAmount;
        this.gifts = gifts;
        this.discountEvents = discountEvents;
        this.totalBenefitAmount = totalBenefitAmount;
        this.getExpectedPaymentAmount = getExpectedPaymentAmount;
        this.badge = badge;
    }

    public List<Menu> getOrders() {
        return orders;
    }

    public int getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public Gift getGifts() {
        return gifts;
    }

    public List<DiscountEvent> getDiscountEvents() {
        return discountEvents;
    }

    public int getTotalBenefitAmount() {
        return totalBenefitAmount;
    }

    public int getGetExpectedPaymentAmount() {
        return getExpectedPaymentAmount;
    }

    public Badge getBadge() {
        return badge;
    }

    public static ReservationProcessedResponseDto create(
            List<Menu> orders,
            int totalOrderAmount,
            Gift gifts,
            List<DiscountEvent> discountEvents,
            int totalBenefitAmount,
            int getExpectedPaymentAmount,
            Badge badge){
        return new ReservationProcessedResponseDto(
                orders,
                totalOrderAmount,
                gifts, discountEvents,
                totalBenefitAmount,
                getExpectedPaymentAmount,
                badge);
    }
}
