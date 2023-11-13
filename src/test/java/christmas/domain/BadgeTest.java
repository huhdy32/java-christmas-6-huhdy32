package christmas.domain;

import christmas.domain.enums.Badge;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class BadgeTest {

    @DisplayName("산타 출력 테스트")
    @ParameterizedTest
    @ValueSource(ints = {30_000, 40_000, 50_000, 20_000})
    void provide_Santa_Amount(int benefitAmount) {
        assertThat(Badge.getBadge(benefitAmount))
                .isEqualTo(Badge.SANTA);
    }

    @DisplayName("트리 반환 테스트")
    @ParameterizedTest
    @ValueSource(ints = {10_000, 19_999})
    void provide_Tree_Amount(int benefitAmount) {
        assertThat(Badge.getBadge(benefitAmount))
                .isEqualTo(Badge.TREE);
    }

    @DisplayName("별 반환 테스트")
    @ParameterizedTest
    @ValueSource(ints = {5_000, 9_999})
    void provide_Star_Amount(int benefitAmount) {
        assertThat(Badge.getBadge(benefitAmount))
                .isEqualTo(Badge.STAR);
    }

    @DisplayName("없음 반환 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, 4999, -500})
    void provide_None_Amount(int benefitAmount) {
        assertThat(Badge.getBadge(benefitAmount))
                .isEqualTo(Badge.NONE);
    }
}
