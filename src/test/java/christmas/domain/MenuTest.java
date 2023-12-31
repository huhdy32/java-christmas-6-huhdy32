package christmas.domain;

import christmas.domain.enums.Menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class MenuTest {

    @DisplayName("메뉴 확인")
    @ParameterizedTest
    @ValueSource(strings = {"양송이스프", "타파스", "시저샐러드", "티본스테이크", "바비큐립",
            "해산물파스타", "크리스마스파스타", "초코케이크", "아이스크림", "제로콜라",
            "레드와인", "샴페인"})
    void provide_Menu_Name(String menu) {
        assertThat(Menu.getMenu(menu))
                .isInstanceOf(Menu.class);
    }

    @DisplayName("NONE 리턴 확인")
    @ParameterizedTest
    @ValueSource(strings = {"달달구리양송이", "초코케이크 두개에 아케리카노 얹어주세요"})
    void accessToCategory(String menuName) {
        assertThat(Menu.getMenu(menuName))
                .isEqualTo(Menu.NONE);
    }
}
