package christmas.domain;

public enum Menu {

    양송이스프(6_000, Category.에피타이저),
    타파스(5_500, Category.에피타이저),
    시저샐러드(8_000, Category.에피타이저),

    티본스테이크(55_000, Category.메인),
    바비큐립(54_000, Category.메인),
    해산물파스타(35_000, Category.메인),
    크리스마스파스타(25_000, Category.메인),

    초코케이크(15_000, Category.디저트),
    아이스크림(5_000, Category.디저트),

    제로콜라(3_000, Category.음료),
    레드와인(60_000, Category.음료),
    샴페인(25_000, Category.음료);

    private int cost;
    private Category category;

    Menu(int cost, Category category) {
        this.cost = cost;
        this.category = category;
    }

    public int getCost() {
        return cost;
    }

    public Category getCategory() {
        return category;
    }

    enum Category {
        에피타이저,
        메인,
        디저트,
        음료
    }
}
