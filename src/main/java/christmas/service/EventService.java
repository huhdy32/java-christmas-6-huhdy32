package christmas.service;

import christmas.domain.Reservation;
import christmas.domain.enums.event.Badge;
import christmas.domain.enums.event.DiscountEvent;
import christmas.domain.enums.event.Gift;
import christmas.dto.EventProcessedDto;

import java.util.List;

public class EventService {
    public static final int MINIMUM_ORDER_AMOUNT_FOR_EVENT = 10_000;
    private final Reservation reservation;
    private boolean eventTarget;

    protected EventService(Reservation reservation) {
        validate(reservation);
        this.reservation = reservation;
    }

    public EventProcessedDto createDto() {
        return EventProcessedDto.create(
                reservation.getMenus(),
                getTotalOrderAmountBeforeDiscount(),
                getGift(),
                getDisCountEvents(),
                getTotalBenefitAmount(),
                getExpectedPaymentAmount(),
                getBadge()
        );
    }

    private void validate(Reservation reservation) {
        if (reservation.getTotalOrderAmount() >= MINIMUM_ORDER_AMOUNT_FOR_EVENT) {
            eventTarget = true;
        }
        eventTarget = false;
    }

    public int getTotalOrderAmountBeforeDiscount() {
        return this.reservation.getTotalOrderAmount();
    }

    public Gift getGift() {
        if (eventTarget == false) {
            return Gift.NONE;
        }
        return Gift.getGift(reservation.getTotalOrderAmount());
    }

    public List<DiscountEvent> getDisCountEvents() {
        if (eventTarget == false) {
            return List.of(DiscountEvent.NONE);
        }
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

    public int getExpectedPaymentAmount() {
        return reservation.getTotalOrderAmount() - getTotalDiscountAmount();
    }

    public Badge getBadge() {
        if (eventTarget == false) {
            return Badge.NONE;
        }
        return Badge.getBadge(getTotalBenefitAmount());
    }

    public static EventService create(Reservation reservation) {
        return new EventService(reservation);
    }
}
