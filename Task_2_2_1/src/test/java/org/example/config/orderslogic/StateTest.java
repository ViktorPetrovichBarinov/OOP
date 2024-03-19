package org.example.config.orderslogic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.orderslogic.State;
import org.junit.jupiter.api.Test;

/**
 * Some text.
 */
public class StateTest {
    @Test
    public void testNullState() {
        State state = State.NULL;
        assertEquals(State.NULL, state);
    }

    @Test
    public void testWaitingForCookingState() {
        State state = State.WAITING_FOR_COOKING;
        assertEquals(State.WAITING_FOR_COOKING, state);
    }

    @Test
    public void testCookingState() {
        State state = State.COOKING;
        assertEquals(State.COOKING, state);
    }

    @Test
    public void testWaitingToBeSentState() {
        State state = State.WAITING_TO_BE_SENT;
        assertEquals(State.WAITING_TO_BE_SENT, state);
    }

    @Test
    public void testDeliveredState() {
        State state = State.DELIVERED;
        assertEquals(State.DELIVERED, state);
    }
}
