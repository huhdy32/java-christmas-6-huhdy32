package christmas.model;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public enum DiscountEvent {
    CHRISTMAS_DISCOUNT(Set.of(
            1, 2, 3, 4, 5,
            6, 7, 8, 9, 10,
            11, 12, 13, 14, 15,
            16, 17, 18, 19, 20,
            21, 22, 23, 24, 25),
            null,
            100) {
        @Override
        int getDiscount(int reservationDate, List<Menu> menus) {
            return 1000 + getUnitAmount() * reservationDate;
        }
    },
    WEEKDAY_DISCOUNT(Set.of(
            3, 4, 5, 6, 7,
            11, 12, 13, 14, 18,
            19, 20, 21, 25, 26,
            27, 28, 31),
            Menu.Category.디저트,
            2023) {
        @Override
        int getDiscount(int reservationDate, List<Menu> menus) {
            return (int) menus.stream()
                    .filter(menu -> menu.getCategory() == Menu.Category.디저트)
                    .count() * getUnitAmount();
        }
    },
    WEEKEND_DISCOUNT(Set.of(
            1, 2, 8, 9, 15
            , 16, 22, 23, 29, 30),
            Menu.Category.메인,
            2023) {
        @Override
        int getDiscount(int reservationDate, List<Menu> menus) {
            return (int) menus.stream()
                    .filter(menu -> menu.getCategory() == Menu.Category.메인)
                    .count() * getUnitAmount();
        }
    },
    SPECIAL_DISCOUNT(Set.of(
            3, 10, 17, 24, 25, 31),
            null,
            1000) {
        @Override
        int getDiscount(int reservationDate, List<Menu> menus) {
            return getUnitAmount();
        }
    };
    private Set<Integer> applyDate;
    private Menu.Category targetCategory;
    private int unitAmount;


    DiscountEvent(Set<Integer> applyDate, Menu.Category targetCategory, int discountAmount) {
        this.applyDate = applyDate;
        this.targetCategory = targetCategory;
        this.unitAmount = discountAmount;
    }

    abstract int getDiscount(int reservationDate, List<Menu> menus);

    int getUnitAmount() {
        return this.unitAmount;
    }

    public static List<DiscountEvent> getEvents(int reservationDate) {
        return Arrays.stream(DiscountEvent.values())
                .filter(discount -> discount.applyDate.contains(reservationDate))
                .toList();
    }
}
