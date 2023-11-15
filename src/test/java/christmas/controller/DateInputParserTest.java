package christmas.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

public class DateInputParserTest {

    DateInputParser dateInputParser;
    @BeforeEach
    void initTest() {
        dateInputParser = new DateInputParser();
    }


    @DisplayName("적절한 날짜 파싱 확인")
    @ParameterizedTest
    @MethodSource("provide_Valid_Date")
    void parsing_Valid_Date_Test(String inputDate, int date) {
        assertThat(dateInputParser.parseDate(inputDate))
                .isEqualTo(date);
    }

    @DisplayName("부적절한 날짜 파싱 에러처리")
    @ParameterizedTest
    @ValueSource(strings = {"0", "32", "-1", "99999999999999999999999", "asdwda", "adw", "-12"})
    void parsing_Invalid_Date_Test(String inputDate) {
        assertThrows(IllegalArgumentException.class, () -> {
            dateInputParser.parseDate(inputDate);
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
