package christmas.ui;

import christmas.ui.io.Input;
import christmas.ui.view.ConsoleView;

public class ConsoleInput extends ConsoleView implements Input {
    @Override
    public String getDate() {
        out("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        return in();
    }

    @Override
    public String getOrder() {
        out("주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1");
        return in();
    }
}
