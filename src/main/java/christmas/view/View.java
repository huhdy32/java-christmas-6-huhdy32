package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class View {
    protected String in(){
        return Console.readLine();
    }
    protected void out(String message){
        System.out.println(message);
    }
}
