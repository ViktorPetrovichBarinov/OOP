package org.example.config.orderslogic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.orderslogic.Order;
import org.example.orderslogic.State;
import org.junit.jupiter.api.Test;

/**
 * Тесты для класса Order
 */
public class OrderTest {

    @Test
    public void testConstructorAndGetters() {
        String pizzaName = "Pepperoni";
        int id = 12345;
        State state = State.NULL;
        Order order = new Order(pizzaName, id, state);

        assertEquals(pizzaName, order.pizzaName());
        assertEquals(id, order.id());
        assertEquals(state, order.state());
    }

    @Test
    public void testSetState() {
        Order order = new Order("Hawaiian", 54321, State.NULL);

        order.setState(State.WAITING_FOR_COOKING);
        assertEquals(State.WAITING_FOR_COOKING, order.state());

        order.setState(State.COOKING);
        assertEquals(State.COOKING, order.state());

        order.setState(State.WAITING_TO_BE_SENT);
        assertEquals(State.WAITING_TO_BE_SENT, order.state());

        order.setState(State.DELIVERED);
        assertEquals(State.DELIVERED, order.state());
    }


}
