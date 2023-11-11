package christmas.model;

import java.util.Arrays;

public enum Badge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NONE(null, 0);

    private String badge;
    private int minimumAmount;

    Badge(String badge, int minimumAmount) {
        this.badge = badge;
        this.minimumAmount = minimumAmount;
    }

    public static Badge getBadge(int benefitAmount) {
        return Arrays.stream(Badge.values())
                .filter(badge -> badge.minimumAmount <= benefitAmount)
                .findFirst()
                .orElse(NONE);
    }
}
