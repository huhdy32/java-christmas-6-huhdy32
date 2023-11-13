package christmas.service;

import christmas.domain.Reservation;
import christmas.domain.enums.Badge;
import christmas.domain.enums.DiscountEvent;
import christmas.domain.enums.Gift;

import java.util.List;

public class EventService {
    public static final int MINIMUM_ORDER_AMOUNT_FOR_EVENT = 10_000;
    private final Reservation reservation;
    private boolean eventTarget;

    protected EventService(Reservation reservation) {
        validate(reservation);
        this.reservation = reservation;
    }

    private void validate(Reservation reservation) {
        if (reservation.getTotalOrderAmount() >= MINIMUM_ORDER_AMOUNT_FOR_EVENT) {
            eventTarget = true;
        }
        eventTarget = false;
    }

    public Gift getGift() {
        return Gift.getGift(reservation.getTotalOrderAmount());
    }

    public List<DiscountEvent> getDisCountEvents() {
        return DiscountEvent.getEvents(reservation.getDate());
    }

    public int getTotalDiscountAmount() {
        return getDisCountEvents().stream()
                .mapToInt(discount -> discount.getDiscount(reservation.getDate(), reservation.getMenus()))
                .sum();
    }

    public int getTotalBenefitAmount() {
        return getGift().getBenefitAmount() + getTotalDiscountAmount();
    }

    public int getTotalPayAmount() {
        return reservation.getTotalOrderAmount() - getTotalDiscountAmount();
    }

    public String getBadge() {
        return Badge.getBadge(getTotalBenefitAmount()).name();
    }

    public EventService create(Reservation reservation) {
        return new EventService(reservation);
    }
}
