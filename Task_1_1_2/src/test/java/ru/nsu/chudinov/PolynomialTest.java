package ru.nsu.chudinov;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.nsu.chudinov.Polynomial.doubleEqual;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Класс для тестирования всех методов класса "Polinomial".
 */
public class PolynomialTest {

    /**
     * Some text.
     */
    @Test
    void checkExampleTest1() {
        Polynomial p1 = new Polynomial(new Integer[] {4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new Integer[] {3, 2, 8});
        assertEquals(p1.plus(p2.differentialI(1)).toString(), "7.0x^3 + 6.0x^2 + 19.0x + 6.0");
    }

    /**
     * Some text.
     */
    @Test
    void checkExampleTest2() {
        Polynomial p1 = new Polynomial(new Integer[] {4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new Integer[] {3, 2, 8});
        assertEquals(p1.multiplication(p2).value(2.0), 3510.0);
    }



    /**
     * Тестирует задание полинома.
     *
     * @param input     входные данные
     * @param expected  выходные данные
     * @param <T>       рандомный параметр
     */
    @ParameterizedTest
    @MethodSource("constructorPolynomialTest")
    <T extends Number> void compareArrays(T[] input, Double[] expected) {
        assertArrayEquals(new Polynomial(input).getCoefficients(), expected);
    }

    private static Stream<Arguments> constructorPolynomialTest() {
        return Stream.of(
                Arguments.of(
                        new Byte[]{0, 1, 2, 3},
                        new Double[]{0.0, 1.0, 2.0, 3.0}
                ),
                Arguments.of(
                        new Byte[]{0, 1, 2, 3, 0, 0},
                        new Double[]{0.0, 1.0, 2.0, 3.0}
                ),
                Arguments.of(
                        new Byte[]{0, 0, 0, 0, 0},
                        new Double[]{0.0}
                ),
                Arguments.of(
                        new Short[]{0, -1, -2, -3},
                        new Double[]{0.0, -1.0, -2.0, -3.0}
                ),
                Arguments.of(
                        new Integer[]{123, 123, 123, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        new Double[]{123.0, 123.0, 123.0}
                ),
                Arguments.of(
                        new Long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L},
                        new Double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0}
                ),
                Arguments.of(
                        new Float[]{5.0F, 4.0F, 3.0F, 2.0F, 1.0F, 0.0F},
                        new Double[]{5.0, 4.0, 3.0, 2.0, 1.0}
                )
        );
    }


    /**
     * Тестирует вывод полинома.
     *
     * @param poly      входные
     * @param expected  выходные
     */
    @ParameterizedTest
    @MethodSource("createToStringPolynomialTest")
    void compareStrings(Polynomial poly, String expected) {
        assertEquals(poly.toString(), expected);
    }

    private static Stream<Arguments> createToStringPolynomialTest() {
        return Stream.of(
                Arguments.of(
                        new Polynomial(new Double[]{1.0, 2.0, 3.0}),
                        "3.0x^2 + 2.0x + 1.0"
                ),
                Arguments.of(
                        new Polynomial(new Byte[]{0, 0, 0, 0, 0, 0, 0}),
                        "0"
                ),
                Arguments.of(
                        new Polynomial(new Short[]{1, 0, 0, 0, 5}),
                        "5.0x^4 + 1.0"
                ),
                Arguments.of(
                        new Polynomial(new Integer[]{-1, 4, 12321, -21}),
                        "- 21.0x^3 + 12321.0x^2 + 4.0x - 1.0"
                )
        );
    }


    @ParameterizedTest
    @MethodSource("compareToPolynomialTest")
    void createCompareToPolynomialTest(Polynomial p1, Polynomial p2, int res) {
        assertEquals(p1.compareTo(p2), res);
    }

    private static Stream<Arguments> compareToPolynomialTest() {
        return Stream.of(
                Arguments.of(
                        new Polynomial(new Integer[]{1, 2, 3, 4}),
                        new Polynomial(new Double[]{1.0, 2.0, 3.0, 4.0}),
                        0
                ),
                Arguments.of(
                        new Polynomial(new Byte[]{0}),
                        new Polynomial(new Short[]{0}),
                        0
                ),
                Arguments.of(
                        new Polynomial(new Integer[]{0, 0, 0, 0, 0}),
                        new Polynomial(new Integer[]{0}),
                        0
                ),
                Arguments.of(
                        new Polynomial(new Integer[]{1, 2, 3})
                                .plus(new Polynomial(new Double[]{1.5, 1.5, 1.5})),
                        new Polynomial(new Double[]{2.5, 3.5, 4.5}),
                        0
                ),
                Arguments.of(
                        new Polynomial(new Integer[]{1, 2, 3}),
                        new Polynomial(new Byte[]{1, 2, 2}),
                        1
                ),
                Arguments.of(
                        new Polynomial(new Double[]{5.0, 4.0, 3.0}),
                        new Polynomial(new Double[]{0.0, 2.3, 7.6}),
                        -1
                )
        );
    }

    @ParameterizedTest
    @MethodSource("equalsPolynomialTest")
    void createEqualsPolynomialTest(Polynomial p1, Object p2, boolean res) {
        assertEquals(p1.equals(p2), res);
    }

    private static Stream<Arguments> equalsPolynomialTest() {
        return Stream.of(
                Arguments.of(
                        new Polynomial(new Double[]{1.0, 3.0}),
                        new Polynomial(new Integer[]{1, 3}),
                        true
                ),
                Arguments.of(
                        new Polynomial(new Integer[]{1, 3}),
                        new Polynomial(new Integer[]{3, 1}),
                        false
                ),
                Arguments.of(
                        new Polynomial(new Integer[]{1, 2}),
                        new Integer[]{1,2},
                        false
                )
        );
    }



    /**
     * Some text.
     *
     * @param a - первое число
     * @param b - второе число
     */
    @ParameterizedTest
    @MethodSource("doubleEqualTests")
    void compareDoubles(Double a, Double b) {
        assertTrue(doubleEqual(a, b));
    }

    private static Stream<Arguments> doubleEqualTests() {
        return Stream.of(
                Arguments.of(
                        1.0,
                        1.00000000000000000000001
                ),
                Arguments.of(
                        2.2,
                        2.199999999999999999
                ),
                Arguments.of(
                        0.3 + 0.6,
                        0.9
                ),
                Arguments.of(
                        0.0,
                        0.0
                )
        );
    }



    /**
     * Some text.
     *
     * @param poly1     - первый аргумент
     * @param poly2     - второй аргумент
     * @param expected  - ожидаемый массив
     */
    @ParameterizedTest
    @MethodSource("plusPolynomialTest")
    void comparePolynomialsPlus(Polynomial poly1, Polynomial poly2, Double[] expected) {
        assertArrayEquals(poly1.plus(poly2).getCoefficients(), expected);
    }

    private static Stream<Arguments> plusPolynomialTest() {
        return Stream.of(
                Arguments.of(
                        new Polynomial(new Integer[]{1, 2, 3}),
                        new Polynomial(new Integer[]{1, 2, 3}),
                        new Double[]{2.0, 4.0, 6.0}
                ),
                Arguments.of(
                        new Polynomial(new Integer[]{10, 5, -10}),
                        new Polynomial(new Integer[]{-10, -5, 10}),
                        new Double[]{0.0}
                ),
                Arguments.of(
                        new Polynomial(new Integer[]{1, 2, 3, 4}),
                        new Polynomial(new Integer[]{1, 2, 3}),
                        new Double[]{2.0, 4.0, 6.0, 4.0}
                ),
                Arguments.of(
                        new Polynomial(new Integer[]{1, 2, 3}),
                        new Polynomial(new Integer[]{1, 2, 3, 4}),
                        new Double[]{2.0, 4.0, 6.0, 4.0}
                )
        );
    }



    /**
     * Some text.
     *
     * @param poly1     - some text
     * @param poly2     - some text
     * @param expected  - some text
     */
    @ParameterizedTest
    @MethodSource("minusPolynomialTest")
    void comparePolynomialsMinus(Polynomial poly1, Polynomial poly2, Double[] expected) {
        assertArrayEquals(poly1.minus(poly2).getCoefficients(), expected);
    }

    private static Stream<Arguments> minusPolynomialTest() {
        return Stream.of(
                Arguments.of(
                        new Polynomial(new Integer[]{1, 2, 3}),
                        new Polynomial(new Integer[]{1, 2, 3}),
                        new Double[]{0.0}
                ),
                Arguments.of(
                        new Polynomial(new Integer[]{10, 5, -10}),
                        new Polynomial(new Integer[]{-10, -5, 10}),
                        new Double[]{20.0, 10.0, -20.0}
                ),
                Arguments.of(
                        new Polynomial(new Byte[]{1, 2, 3}),
                        new Polynomial(new Byte[]{1, 2, 3, -5, -5}),
                        new Double[]{0.0, 0.0, 0.0, 5.0, 5.0}
                ),
                Arguments.of(
                        new Polynomial(new Byte[]{0, 0, 3, 4, 5}),
                        new Polynomial(new Byte[]{1, 2, 3, 4}),
                        new Double[]{-1.0, -2.0, 0.0, 0.0, 5.0}
                )
        );
    }


    /**
     * Some text.
     *
     * @param poly1     - some text
     * @param poly2     - some text
     * @param expected  - some text
     */
    @ParameterizedTest
    @MethodSource("multiplicationPolynomialTest")
    void comparePolynomialsMultiplication(Polynomial poly1, Polynomial poly2, Double[] expected) {
        assertArrayEquals(poly1.multiplication(poly2).getCoefficients(), expected);
    }

    private static Stream<Arguments> multiplicationPolynomialTest() {
        return Stream.of(
                Arguments.of(
                        new Polynomial(new Integer[]{1, 1}),
                        new Polynomial(new Integer[]{1, 1}),
                        new Double[]{1.0, 2.0, 1.0}
                ),
                Arguments.of(
                        new Polynomial(new Integer[]{1, 2, 1}),
                        new Polynomial(new Integer[]{1, 1}),
                        new Double[]{1.0, 3.0, 3.0, 1.0}
                ),
                Arguments.of(
                        new Polynomial(new Integer[]{1, 1, 1}),
                        new Polynomial(new Integer[]{0}),
                        new Double[]{0.0}
                )
        );
    }


    /**
     * Some text.
     *
     * @param poly1     - some text
     * @param x         - some text
     * @param a         - some text
     */
    @ParameterizedTest
    @MethodSource("valuePolynomialTest")
    void valueOfPolynomial(Polynomial poly1, Double x, Double a) {
        assertTrue(doubleEqual(poly1.value(x), a));
    }

    private static Stream<Arguments> valuePolynomialTest() {
        return Stream.of(
                Arguments.of(
                        new Polynomial(new Double[]{1.0}),
                        1234.0,
                        1.0
                ),
                Arguments.of(
                        new Polynomial(new Integer[]{-1, 100, -200, 0, 123, 1}),
                        0.728,
                        0.556
                ),
                Arguments.of(
                        new Polynomial(new Integer[]{1, 2, 1}),
                        0.0,
                        1.0
                )
        );
    }

    /**
     * Some text.
     *
     * @param poly1     - some text
     * @param i         - some text
     * @param expected  - some text
     */
    @ParameterizedTest
    @MethodSource("differentialPolynomialsTest")
    void valueOfPolynomial(Polynomial poly1, Integer i, Double[] expected) {
        assertArrayEquals(poly1.differentialI(i).getCoefficients(), expected);
    }

    private static Stream<Arguments> differentialPolynomialsTest() {
        return Stream.of(
                Arguments.of(
                        new Polynomial(new Double[]{4.0, 3.0}),
                        3,
                        new Double[]{0.0}
                ),
                Arguments.of(
                        new Polynomial(new Double[]{5.9, 13.4, 12.5, 32.0}),
                        1,
                        new Double[]{13.4, 25.0, 96.0}
                ),
                Arguments.of(
                        new Polynomial(new Double[]{13.4, 25.0, 96.0}),
                        1,
                        new Double[]{25.0, 192.0}
                ),
                Arguments.of(
                        new Polynomial(new Double[]{25.0, 192.0}),
                        1,
                        new Double[]{192.0}
                ),
                Arguments.of(
                        new Polynomial(new Double[]{5.9, 13.4, 12.5, 32.0}),
                        3,
                        new Double[]{192.0}
                )
        );
    }

}