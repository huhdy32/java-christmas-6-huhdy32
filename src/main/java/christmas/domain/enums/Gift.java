package christmas.domain.enums;

import java.util.Arrays;

public enum Gift {

    CHAMPAGNE(Menu.CHAMPAGNE, 120_000),
    NONE(Menu.NONE, 0);
    private Menu menu;
    private int minimum_Amount_For_Free_Gift;

    Gift(Menu menu, int minimum_Amount_For_Free_Gift) {
        this.menu = menu;
        this.minimum_Amount_For_Free_Gift = minimum_Amount_For_Free_Gift;
    }

    public int getBenefitAmount() {
        return this.menu.getCost();
    }

    public static Gift getGift(int totalPayAmount) {
        return Arrays.stream(Gift.values())
                .filter(giftMenu -> giftMenu.minimum_Amount_For_Free_Gift <= totalPayAmount)
                .findFirst()
                .orElse(NONE);
    }

    public Menu getMenu() {
        return this.menu;
    }

    @Override
    public String toString() {
        return menu.toString();
    }
}
