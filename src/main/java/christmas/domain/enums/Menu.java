package christmas.domain.enums;

import java.util.Arrays;

public enum Menu {

    WHITE_MUSHROOM_SOUP("양송이스프", 6_000, Category.APPETIZER),
    TAPAS("타파스", 5_500, Category.APPETIZER),

    CAESAR_SALAD("시저샐러드", 8_000, Category.APPETIZER),


    T_BONE_STEAK("티본스테이크", 55_000, Category.MAIN),
    BARBECUE_RIBS("바비큐립", 54_000, Category.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35_000, Category.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, Category.MAIN),

    CHOCOLATE_CAKE("초코케이크", 15_000, Category.DESSERT),
    ICE_CREAM("아이스크림", 5_000, Category.DESSERT),

    ZERO_COKE("제로콜라", 3_000, Category.DRINK),
    RED_WINE("레드와인", 60_000, Category.DRINK),
    CHAMPAGNE("샴페인", 25_000, Category.DRINK),

    NONE("없음", 0, Category.NONE);

    private String menuName;
    private int cost;
    private Category category;

    Menu(String menuName, int cost, Category category) {
        this.menuName = menuName;
        this.cost = cost;
        this.category = category;
    }
    public static Menu getMenu(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getMenuName().equals(menuName))
                .findFirst()
                .orElse(NONE);
    }

    public String getMenuName() {
        return this.menuName;
    }

    public int getCost() {
        return cost;
    }

    public Category getCategory() {
        return category;
    }

    public enum Category {
        APPETIZER,
        MAIN,
        DESSERT,
        DRINK,
        NONE
    }
}
