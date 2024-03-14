package org.example.ordersLogic;

public class Order {
    private final String pizzaName;
    private final int id;
    private State state;
    public Order(String pizzaName, int id, State state) {
        this.pizzaName = pizzaName;
        this.id = id;
        this.state = state;
    }

    public String pizzaName() {
        return this.pizzaName;
    }

    public int id() {
        return this.id;
    }

    public State state() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
