package christmas.controller;

import christmas.domain.enums.Menu;
import christmas.ui.OutputView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReservationRequestParserTest {


    ReservationRequestParser reservationRequestParser ;
    @BeforeEach
    void initTest() {
        reservationRequestParser = new ReservationRequestParser(new OutputView());
    }

    @DisplayName("정상 주문 파싱 확인")
    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-1,초코케이크-2","초코케이크-2,해산물파스타-1"})
    void parsing_Valid_Order_Test(String order) {
        assertThat(reservationRequestParser.parseOrders(order))
                .containsAll(List.of(Menu.CHOCOLATE_CAKE, Menu.CHOCOLATE_CAKE, Menu.SEAFOOD_PASTA));
    }

    @DisplayName("비정상 주문 에러처리")
    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-0ㅈㄹ", "홰솽물퐈스톼-2", "샵빠뚜비두바,홰솽물퐈스톼-2", "해산물파스타-2 ,초코케이크-1"})
    void parseing_Invalid_Order_Test(String order) {
        assertThrows(IllegalArgumentException.class, () -> {
            reservationRequestParser.parseOrders(order);
        });
    }

    @DisplayName("적절한 날짜 파싱 확인")
    @ParameterizedTest
    @MethodSource("provide_Valid_Date")
    void parsing_Valid_Date_Test(String inputDate, int date) {
        assertThat(reservationRequestParser.parseDate(inputDate))
                .isEqualTo(date);
    }

    @DisplayName("부적절한 날짜 파싱 에러처리")
    @ParameterizedTest
    @ValueSource(strings = {"0", "32", "-1", "99999999999999999999999", "asdwda", "adw", "-12"})
    void parsing_Invalid_Date_Test(String inputDate) {
        assertThrows(IllegalArgumentException.class, () -> {
            reservationRequestParser.parseDate(inputDate);
        });
    }

    static Stream<Arguments> provide_Valid_Date() {
        return Stream.of(
                Arguments.of("30", 30),
                Arguments.of("31", 31),
                Arguments.of("1", 1)
        );
    }
}
