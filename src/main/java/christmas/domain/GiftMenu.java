package christmas.domain;

import java.util.Arrays;

public enum GiftMenu {

    샴페인(Menu.샴페인, 120_000),
    없음(null, 0);
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
                .orElse(없음);
    }
}
