package christmas.service;

import christmas.domain.Reservation;
import christmas.domain.enums.Menu;
import christmas.domain.enums.event.DiscountEvent;
import jdk.jfr.Event;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class EventServiceTest {

    @ParameterizedTest
    @MethodSource("provide_Reservation")
    @DisplayName("이벤트 적용되는지 확인")
    void checkEvent(Reservation reservation, List<DiscountEvent> discountEvents) {
        assertThat(new EventService(reservation).getDisCountEvents()
                .containsAll(discountEvents));
    }

    static Stream<Arguments> provide_Reservation() {
        return Stream.of(
                Arguments.of(Reservation.create(
                        List.of(Menu.CHOCOLATE_CAKE, Menu.CHAMPAGNE),
                        1),
                        List.of(DiscountEvent.CHRISTMAS_DISCOUNT, DiscountEvent.WEEKEND_DISCOUNT)
                ));
    }

}
