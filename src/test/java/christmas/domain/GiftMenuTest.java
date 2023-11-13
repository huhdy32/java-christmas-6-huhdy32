package christmas.domain;


import christmas.domain.enums.GiftMenu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class GiftMenuTest {
    @ParameterizedTest
    @DisplayName("증정품 제공 테스트")
    @ValueSource(ints = {120_000, 130_000})
    void provide_Payment(int totalPayAmount) {
        assertThat(GiftMenu.getGift(totalPayAmount))
                .isEqualTo(GiftMenu.CHAMPAGNE);
    }

    @ParameterizedTest
    @DisplayName("없음 처리 테스트")
    @ValueSource(ints = {1, -12, 0})
    void provide_Invalid_Payment(int totalPayAmount) {
        assertThat(GiftMenu.getGift(totalPayAmount))
                .isEqualTo(GiftMenu.NONE);
    }

}
