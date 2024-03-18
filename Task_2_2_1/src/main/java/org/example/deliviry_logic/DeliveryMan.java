package org.example.deliviry_logic;

/**
 * Вспомогательный рекорд.
 *
 * @param pizzasCapacity - сколько пицц может доставить
 *                       доставщик за один раз.
 * @param timeToFirstPizza - время на доставку первой пиццы.
 */
public record DeliveryMan(int pizzasCapacity, int timeToFirstPizza) {

}
