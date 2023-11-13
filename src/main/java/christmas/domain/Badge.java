package christmas.domain;

import java.util.Arrays;

public enum Badge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NONE("없음", 0);

    private String name;
    private int minimumAmount;

    Badge(String name, int minimumAmount) {
        this.name = name;
        this.minimumAmount = minimumAmount;
    }

    public static Badge getBadge(int benefitAmount) {
        return Arrays.stream(Badge.values())
                .filter(badge -> badge.minimumAmount <= benefitAmount)
                .findFirst()
                .orElse(NONE);
    }

    String getName() {
        return this.name;
    }
}
