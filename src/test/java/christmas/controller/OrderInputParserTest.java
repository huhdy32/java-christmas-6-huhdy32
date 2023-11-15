package christmas.controller;

import christmas.domain.enums.Menu;
import christmas.ui.OutputView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderInputParserTest {

    private OrderInputParser orderInputParser;

    @BeforeEach
    void initTest() {
        orderInputParser = new OrderInputParser();
    }

    @DisplayName("정상 주문 파싱 확인")
    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-1,초코케이크-2","초코케이크-2,해산물파스타-1"})
    void parsing_Valid_Order_Test(String order) {
        assertThat(orderInputParser.parseOrders(order))
                .containsAll(List.of(Menu.CHOCOLATE_CAKE, Menu.CHOCOLATE_CAKE, Menu.SEAFOOD_PASTA));
    }

    @DisplayName("비정상 주문 에러처리")
    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-0ㅈㄹ", "홰솽물퐈스톼-2", "샵빠뚜비두바,홰솽물퐈스톼-2", "해산물파스타-2 ,초코케이크-1"})
    void parseing_Invalid_Order_Test(String order) {
        assertThrows(IllegalArgumentException.class, () -> {
            orderInputParser.parseOrders(order);
        });
    }
}
