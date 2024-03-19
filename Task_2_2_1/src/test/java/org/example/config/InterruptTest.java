package org.example.config;

import org.example.Interrupt;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterruptTest {
    @Test
    public void testInterrupt() {
        Interrupt interrupt = Interrupt.INTERRUPT;
        assertEquals(Interrupt.INTERRUPT, interrupt);
    }

    @Test
    public void testNotInterrupt() {
        Interrupt notInterrupt = Interrupt.NOT_INTERRUPT;
        assertEquals(Interrupt.NOT_INTERRUPT, notInterrupt);
    }
}
