package christmas.ui;

import christmas.ui.io.Output;
import christmas.ui.view.ConsoleView;

public class ConsoleOutput extends ConsoleView implements Output {

    @Override
    public void printAllDetails() {
        this.printEventDate();
        this.printOrders();
        this.printOrderAmount();
        this.printFreeGift();
        this.printBenefitDetails();
        this.printTotalBenefitAmount();
        this.printTotalAmount();
        this.printEventBadge();
    }


    @Override
    public void printEventDate() {

    }

    @Override
    public void printOrders() {

    }
    @Override
    public void printOrderAmount() {

    }
    @Override
    public void printFreeGift() {

    }
    @Override
    public void printBenefitDetails() {

    }
    @Override
    public void printTotalBenefitAmount() {

    }
    @Override
    public void printTotalAmount() {

    }

    @Override
    public void printEventBadge() {

    }
}
