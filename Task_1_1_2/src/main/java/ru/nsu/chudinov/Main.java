package ru.nsu.chudinov;

/**
 * some text.
 */
public class Main {
    public static void main(String[] args) {
        Polynomial p1 = new Polynomial(new Integer[] {4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new Integer[] {3, 2, 8});
        System.out.println(p1.plus(p2.differentialI(1)).toString());
        System.out.println(p1.multiplication(p2).value(2));

        Polynomial p3 = new Polynomial(new Double[]{0.0, 2.3, 7.6});
        Polynomial p4 = new Polynomial(new Double[]{5.0, 4.0, 3.0});
        System.out.println(p3);
        System.out.println(p4);
        System.out.println(p3.compareTo(p4));

    }
}