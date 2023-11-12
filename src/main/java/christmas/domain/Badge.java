package christmas.domain;

import java.util.Arrays;

public enum Badge {
    산타( 20_000),
    트리( 10_000),
    별(5_000),
    없음( 0);

    private int minimumAmount;

    Badge(int minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    public static Badge getBadge(int benefitAmount) {
        return Arrays.stream(Badge.values())
                .filter(badge -> badge.minimumAmount <= benefitAmount)
                .findFirst()
                .orElse(없음);
    }
}
