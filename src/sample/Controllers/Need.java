package sample.Controllers;

public class Need {
    private String name;
    private int amount;

    public Need(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}
