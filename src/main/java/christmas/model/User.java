package christmas.model;

import java.util.List;

public class User {
    private List<Menu> menus;
    private int reservationDate;

    public User(List<Menu> menus, int reservationDate) {
        this.menus = menus;
        this.reservationDate = reservationDate;
    }

    public int getTotalAmount() {
        return menus.stream()
                .mapToInt(menu -> menu.getCost())
                .sum();
    }

    public GiftMenu getGiftMenu() {
        return GiftMenu.getGift(getTotalAmount());
    }
}
