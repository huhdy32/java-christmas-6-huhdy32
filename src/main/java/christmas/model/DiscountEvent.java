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
            return 1000 + getDiscountAmount() * reservationDate;
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
            int dessertCount = 0;
            for (Menu menu : menus) {
                if (menu.getCategory() == Menu.Category.디저트) {
                    dessertCount++;
                }
            }
            return dessertCount * getDiscountAmount();
        }
    },
    WEEKEND_DISCOUNT(Set.of(
            1, 2, 8, 9, 15
            , 16, 22, 23, 29, 30),
            Menu.Category.메인,
            2023) {
        @Override
        int getDiscount(int reservationDate, List<Menu> menus) {
            int mainCount = 0;
            for (Menu menu : menus) {
                if (menu.getCategory() == Menu.Category.메인) {
                    mainCount++;
                }
            }
            return mainCount * getDiscountAmount();
        }
    },
    SPECIAL_DISCOUNT(Set.of(
            3, 10, 17, 24, 25, 31),
            null,
            1000) {
        @Override
        int getDiscount(int reservationDate, List<Menu> menus) {
            return getDiscountAmount();
        }
    };
    private Set<Integer> applyDate;
    private Menu.Category targetCategory;
    private int discountAmount;

    abstract int getDiscount(int reservationDate, List<Menu> menus);

    DiscountEvent(Set<Integer> applyDate, Menu.Category targetCategory, int discountAmount) {
        this.applyDate = applyDate;
        this.targetCategory = targetCategory;
        this.discountAmount = discountAmount;
    }

    int getDiscountAmount() {
        return this.discountAmount;
    }

    public static List<DiscountEvent> getDiscount(int reservationDate) {
        return Arrays.stream(DiscountEvent.values())
                .filter(discount -> discount.applyDate.contains(reservationDate))
                .toList();
    }
}
