package ru.nsu.chudinov;


import java.util.Arrays;

/**
 * some text.
 */
public class Polynomial implements Comparable<Polynomial>{

    /**
     * Коэффициенты полинома от младшего к старшему.
     */
    private final Double[] coefficients;

    /**
     * Для тестов нужен.
     *
     * @return  массив коэффициентов
     */
    public Double[] getCoefficients() {
        return coefficients;
    }

    //сильно больше скорее всего не надо и это оптимальный эпсилон для x^5
    //за жизнь наверно раз 10 не больше сталкивался с полиномами такого типа
    //если пытаться оптимизировать для x больших степеней,
    // то надо увеличивать эпсилон -> точность начинает ощутимо хромать
    private static final double E = 0.001;

    /**
     * Конструктор заполняет одно поле(coefficients) в экземпляре.
     *
     * @param arr       массив с коэффициентами от младшего к старшему
     * @param <T>       любой числовой тип
     */
    public <T extends Number> Polynomial(T[] arr) {
        //вызывает исключение, чтобы нельзя было передать пустой массив

        if (arr.length == 0) {
            throw new IllegalArgumentException("Array must be non empty!");
        }
        //стираем старшие незначащие нули
        int maxNonZeroIndex = arr.length - 1;
        while (maxNonZeroIndex >= 1) {
            if (!doubleEqual(arr[maxNonZeroIndex].doubleValue(), 0.0)) {
                break;
            }
            maxNonZeroIndex--;
        }

        //создаем массив коэффициентов с максимальной значащеё степенью
        this.coefficients = new Double[maxNonZeroIndex + 1];

        //неявно приводя любой тип к "Double", заполняем массив коэффициентов
        for (int i = 0; i <= maxNonZeroIndex; i++) {
            this.coefficients[i] = arr[i].doubleValue();
        }
    }

    /**
     * Переопределяю метод toString, унаследованный от супер-класса Object.
     * Override - директива, которая позволяет переопределять метод унаследованный от супер-класса
     *
     * @return строка вида полинома от старшего к младшему.
     */
    @Override
    public String toString() {
        //класс в java предназначенный для построения строк
        StringBuilder result = new StringBuilder();
        //спросить про то, можно ли не использовать this(вроде можно) и
        //почему компилятор понимает, что именно эти коэффициенты хочу поменять
        for (int i = this.coefficients.length - 1; i >= 0; i--) {
            if (!doubleEqual(this.coefficients[i], 0.0)) {
                if (this.coefficients[i] > 0) {
                    result.append(" + ");
                } else {
                    result.append(" - ");
                }
                result.append(Math.abs(this.coefficients[i]));
                if (i > 1) {
                    result.append("x^").append(i);
                }
                if (i == 1) {
                    result.append("x");
                }
            }
        }
        // Удалить первый" + ", если он есть.
        if (result.length() >= 3 && result.substring(0, 3).equals(" + ")) {
            result.delete(0, 3);
        }
        if (result.length() >= 3 && result.substring(0, 3).equals(" - ")) {
            result.delete(0, 1);
        }
        if (this.coefficients.length == 1 && this.coefficients[0].equals(0.0)) {
            result.append("0");
        }
        return result.toString();
    }

    /**
     * <0, если объект меньше переданного
     * >0, если объект больше переданного
     * =0, если объект равен переданному
     *
     * @param other the object to be compared.
     * @return выше
     */
    @Override
    public int compareTo(Polynomial other) {
        if (this.coefficients.length > other.coefficients.length) {
            return 1;
        } else if (this.coefficients.length < other.coefficients.length) {
            return -1;
        } else {
            for (int i = this.coefficients.length - 1; i >= 0; i--) {
                if (!doubleEqual(this.coefficients[i], other.coefficients[i])) {
                    if (this.coefficients[i] > other.coefficients[i]) {
                        return 1;
                    }
                    else {
                        return -1;
                    }
                }
            }
        }
        return 0;
    }

    /**
     *  Реализация сравнения через Equals из класса Object.
     *  Вернёт true, если полиномы идентичны.
     *  Вернёт false в ином случае.
     *
     * @param obj - объект с которым надо провести сравнение
     * @return  - true/false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        // Проверяем на null и то что классы идентичны
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        // Приводим obj к типу Polynomial
        Polynomial other = (Polynomial) obj;
        return this.compareTo(other) == 0;
    }

    /**
     * Сравнение чисел с плавающей точкой через эпсилон-окрестность.
     *
     * @param a - первое число
     * @param b - второе число
     * @return text
     */
    public static boolean doubleEqual(double a, double b) {
        return Math.abs(a - b) <= E;
    }

    /**
     * Метод складывает два полинома.
     *
     * @param other     другой полином, который будет прибавлен к основному
     * @return          Ссылка на результат
     */
    public Polynomial plus(Polynomial other) {
        Integer maxLength = Math.max(this.coefficients.length, other.coefficients.length);
        Integer minLength = Math.min(this.coefficients.length, other.coefficients.length);
        Double[] resultCoefficients = new Double[maxLength];
        //получаем сумму до индекса массива минимальной длинны
        for (int i = 0; i < minLength; i++) {
            resultCoefficients[i] = this.coefficients[i] + other.coefficients[i];
        }

        if (maxLength.equals(this.coefficients.length)) {
            System.arraycopy(this.coefficients, minLength,
                    resultCoefficients, minLength, maxLength - minLength);
        } else {
            System.arraycopy(other.coefficients, minLength,
                    resultCoefficients, minLength, maxLength - minLength);
        }

        //если у нас полиномы идентичной длинны, то в старших коэффициентах может быть ноль
        if (minLength.equals(maxLength)) {
            int lastNonZeroIndex = minLength - 1;
            while (lastNonZeroIndex >= 1) {
                if (resultCoefficients[lastNonZeroIndex].equals(0.0)) {
                    lastNonZeroIndex--;
                } else {
                    break;
                }
            }
            if (lastNonZeroIndex == 0) {
                return new Polynomial(new Double[]{0.0});
            }
            Double[] resultCoefficientsEqualLength = new Double[lastNonZeroIndex + 1];
            System.arraycopy(resultCoefficients, 0,
                    resultCoefficientsEqualLength, 0, lastNonZeroIndex + 1);
            return new Polynomial(resultCoefficientsEqualLength);
        }
        return new Polynomial(resultCoefficients);
    }

    /**
     * Аналогично plus.
     *
     * @param other   - description
     * @return        - description
     */
    public Polynomial minus(Polynomial other) {
        for (int i = 0; i < other.coefficients.length; i++) {
            other.coefficients[i] = -other.coefficients[i];
        }
        return this.plus(other);
    }

    /**
     * Метод находит произведение двух полиномов.
     *
     * @param other - description
     * @return      - description
     */
    public Polynomial multiplication(Polynomial other) {
        int maxLength = Math.max(this.coefficients.length, other.coefficients.length);
        int minLength = Math.min(this.coefficients.length, other.coefficients.length);
        Double[] result = new Double[maxLength + minLength - 1];
        Arrays.fill(result, 0.0);
        for (int i = 0; i < maxLength; i++) {
            for (int j = 0; j < minLength; j++) {
                if (this.coefficients.length == maxLength) {
                    result[i + j] += this.coefficients[i] * other.coefficients[j];
                } else {
                    result[i + j] += this.coefficients[j] * other.coefficients[i];
                }
            }
        }
        return new Polynomial(result);
    }

    /**
     * Метод находит значение полинома в точке.
     *
     * @param x     - точка
     * @param <T>   - x задаётся любым типом
     * @return      - значение в точку
     */
    public <T extends Number>  Double value(T x) {
        double returnValue = 0.0;
        for (int i = 0; i < this.coefficients.length; i++) {
            returnValue += Math.pow(x.doubleValue(), i) * this.coefficients[i];
        }
        return returnValue;
    }

    /**
     * Метод находит вид полинома после i-ого дифференцирования.
     * Если параметр i меньше 0, тогда ошибка
     *
     * @param i     i-ая производная
     * @return      возвращает полином
     */
    public Polynomial differentialI(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("x must be positive!");
        }
        if (i >= this.coefficients.length) {
            return new Polynomial(new Double[]{0.0});
        } else {
            Double[] returnArr = new Double[this.coefficients.length - i];
            Arrays.fill(returnArr, 0.0);
            for (int j = 0; j < returnArr.length; j++) {
                returnArr[j] = this.coefficients[i + j];
                int tmp = i;
                while (tmp > 0) {
                    returnArr[j] *= (tmp + j);
                    tmp--;
                }
            }
            return new Polynomial(returnArr);
        }
    }

}