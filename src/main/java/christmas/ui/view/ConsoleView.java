package christmas.ui.view;

import camp.nextstep.edu.missionutils.Console;

public class ConsoleView implements View {
    @Override
    public String in() {
        return Console.readLine();
    }

    @Override
    public void out(String message) {
        System.out.println(message);
    }
}
