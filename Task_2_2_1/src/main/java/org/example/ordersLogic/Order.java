package org.example.ordersLogic;

public class Order{
    private String pizzaName;
    private int id;
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
