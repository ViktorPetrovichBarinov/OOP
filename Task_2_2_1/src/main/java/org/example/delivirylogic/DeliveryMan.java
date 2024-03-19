package org.example.delivirylogic;

/**
 * Вспомогательный рекорд.
 *
 * @param pizzasCapacity - сколько пицц может доставить
 *                       доставщик за один раз.
 * @param timeToFirstPizza - время на доставку первой пиццы.
 */
public record DeliveryMan(int pizzasCapacity, int timeToFirstPizza) {

}
