package christmas.domain.enums.event;

import christmas.domain.enums.Menu;

import java.util.Arrays;

public enum Gift {

    CHAMPAGNE("샴페인 1개", Menu.CHAMPAGNE, 120_000),
    NONE("없음", Menu.NONE, 0);
    private String name;
    private Menu menu;
    private int minimum_Amount_For_Free_Gift;

    Gift(String name, Menu menu, int minimum_Amount_For_Free_Gift) {
        this.name = name;
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

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return menu.toString();
    }
}
