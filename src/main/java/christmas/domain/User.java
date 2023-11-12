package christmas.domain;

import java.util.List;

public class User {
    private List<Menu> menus;
    private int reservationDate;

    public User(List<Menu> menus, int reservationDate) {
        this.menus = menus;
        this.reservationDate = reservationDate;
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
}
