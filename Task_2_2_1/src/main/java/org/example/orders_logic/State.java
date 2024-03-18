package org.example.orders_logic;

/**
 * Енам для явного указания состояния пиццы.
 */
public enum State {
    NULL,
    WAITING_FOR_COOKING,
    COOKING,
    WAITING_TO_BE_SENT,
    DELIVERED
}
