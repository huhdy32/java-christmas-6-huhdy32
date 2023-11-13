package christmas.domain.enums;

import java.util.Arrays;

public enum GiftMenu {


    CHAMPAGNE(Menu.CHAMPAGNE, 120_000),
    NONE(null, 0);
    private Menu menu;
    private int minimum_Amount_For_Free_Gift;

    GiftMenu(Menu menu, int minimum_Amount_For_Free_Gift) {
        this.menu = menu;
        this.minimum_Amount_For_Free_Gift = minimum_Amount_For_Free_Gift;
    }

    int getBenefitAmount() {
        return this.menu.getCost();
    }

    public static GiftMenu getGift(int totalPayAmount) {
        return Arrays.stream(GiftMenu.values())
                .filter(giftMenu -> giftMenu.minimum_Amount_For_Free_Gift <= totalPayAmount)
                .findFirst()
                .orElse(NONE);
    }


    @Override
    public String toString() {
        return menu.toString();
    }
}
