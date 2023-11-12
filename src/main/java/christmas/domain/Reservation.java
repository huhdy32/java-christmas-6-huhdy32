package christmas.domain;

import christmas.logic.Logic;

import java.util.List;

public class Reservation {
    private boolean eventTarget = true;
    private List<Menu> menus;
    private int date;

    public Reservation(List<Menu> menus, int date) {
        this.menus = menus;
        this.date = date;
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
        return DiscountEvent.getEvents(date);
    }

    public int getTotalDiscountAmount() {
        return getDisCountEvents().stream()
                .mapToInt(discount -> discount.getDiscount(date, menus))
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
