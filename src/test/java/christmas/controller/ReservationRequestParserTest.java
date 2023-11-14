package christmas.controller;

import christmas.domain.enums.Menu;
import christmas.ui.OutputView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ReservationRequestParserTest {


    ReservationRequestParser reservationRequestParser ;
    @BeforeEach
    void initTest() {
        reservationRequestParser = new ReservationRequestParser(new OutputView());
    }
    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-1,초코케이크-2","초코케이크-2,해산물파스타-1"})
    void parsingTest(String order) {
        assertThat(new ReservationRequestParser(new OutputView()).parseOrders(order))
                .containsAll(List.of(Menu.CHOCOLATE_CAKE, Menu.CHOCOLATE_CAKE, Menu.SEAFOOD_PASTA));
    }
}
