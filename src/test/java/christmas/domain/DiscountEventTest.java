package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
public class DiscountEventTest {

    @ParameterizedTest
    @DisplayName("예약 일자를 바탕으로 적용되는 이벤트 확인하기")
    @MethodSource("provide_Reservation_Date")
    void 적절한_이벤트_적용_확인(int date, List<DiscountEvent> events) {
        assertThat(DiscountEvent.getEvents(date))
                .containsAll(events);
    }

    @ParameterizedTest
    @DisplayName("할인 적용 확인")
    @MethodSource("provide_Menus")
    void 할인율_적절한지_테스트(int reservationDate, List<Menu> menus, int result) {
        assertThat(
                DiscountEvent.getEvents(reservationDate).stream()
                        .mapToInt(event -> event.getDiscount(reservationDate, menus))
                        .sum())
                .isEqualTo(result);
    }

    static Stream<Arguments> provide_Reservation_Date() {
        return Stream.of(
                Arguments.of(
                        3,
                        List.of(DiscountEvent.CHRISTMAS_DISCOUNT,
                                DiscountEvent.WEEKDAY_DISCOUNT,
                                DiscountEvent.SPECIAL_DISCOUNT)
                ),
                Arguments.of(
                        31,
                        List.of(DiscountEvent.WEEKDAY_DISCOUNT,
                                DiscountEvent.SPECIAL_DISCOUNT)),
                Arguments.of(
                        26,
                        List.of(DiscountEvent.WEEKDAY_DISCOUNT)
                ),
                Arguments.of(
                        6,
                        List.of(DiscountEvent.CHRISTMAS_DISCOUNT,
                                DiscountEvent.WEEKDAY_DISCOUNT)
                ),
                Arguments.of(
                        22,
                        List.of(DiscountEvent.WEEKEND_DISCOUNT,
                                DiscountEvent.CHRISTMAS_DISCOUNT)
                )

        );
    }
    static Stream<Arguments> provide_Menus() {
        return Stream.of(
                Arguments.of(
                        26,
                        List.of(Menu.ICE_CREAM,
                                Menu.CHOCOLATE_CAKE,
                                Menu.ZERO_COKE),
                        4046
                ),
                Arguments.of(
                        3,
                        List.of(Menu.ICE_CREAM,
                                Menu.CHOCOLATE_CAKE,
                                Menu.BARBECUE_RIBS),
                        6346
                ),
                Arguments.of(
                        22,
                        List.of(Menu.T_BONE_STEAK,
                                Menu.WHITE_MUSHROOM_SOUP),
                        5223
                )
        );
    }
}
