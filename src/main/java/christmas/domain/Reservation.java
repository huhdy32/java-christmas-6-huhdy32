package christmas.domain;

import christmas.logic.Logic;
import christmas.utils.ErrorMessage;

import java.util.List;

public class Reservation {
    private boolean eventTarget = true;
    private List<Menu> menus;
    private int reservationDate;

    public Reservation(List<Menu> menus, int reservationDate) {
        this.menus = menus;
        this.reservationDate = reservationDate;
    }

    private void isEventTarget() {
        if (getTotalOrderAmount() < Logic.MINIMUM_ORDER_AMOUNT_FOR_BENEFIT) {
            eventTarget = false;
        }
    }

    public int getTotalOrderAmount() {
        return menus.stream()
                .mapToInt(menu -> menu.getCost())
                .sum();
    }

    public GiftMenu getGift() {
        return GiftMenu.getGift(getTotalOrderAmount());
    }

    public List<DiscountEvent> getDisCountEvents() {
        return DiscountEvent.getEvents(reservationDate);
    }

    public int getTotalDiscountAmount() {
        return getDisCountEvents().stream()
                .mapToInt(discount -> discount.getDiscount(reservationDate, menus))
                .sum();
    }

    public int getTotalBenefitAmount() {
        return getGift().getBenefitAmount() + getTotalDiscountAmount();
    }

    public int getTotalPayAmount() {
        return getTotalOrderAmount() - getTotalDiscountAmount();
    }

    public String getBadge() {
        return Badge.getBadge(getTotalBenefitAmount()).name();
    }
}
