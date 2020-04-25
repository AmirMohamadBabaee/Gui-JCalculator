/**
 * CalculatorFunction
 *
 * This class define calculus function in this calculator
 *
 * @author Amir01
 * @version v0.1 (25 Apr 2020)
 */
public class CalculatorFunction {

    // Method

    /**
     * this method calculate summation of two number
     *
     * @param x first integer number
     * @param y second integer number
     * @return summation of two number
     */
    public static double sum(int x, int y) {

        return x + y;

    }

    /**
     * this method calculate summation of two number
     *
     * @param x first double number
     * @param y second double number
     * @return summation of two number
     */
    public static double sum(double x, double y) {

        return x + y;

    }

    /**
     * this method calculate subtraction of two number
     *
     * @param x first integer number
     * @param y second integer number
     * @return subtraction of two number
     */
    public static double subtract(int x , int y) {

        return x - y;

    }

    /**
     * this method calculate subtraction of two number
     *
     * @param x first double number
     * @param y second double number
     * @return subtraction of two number
     */
    public static double subtract(double x , double y) {

        return x - y;

    }

    /**
     * this method calculate multiplication of two number
     *
     * @param x first integer number
     * @param y second integer number
     * @return multiplication of two number
     */
    public static double multiply(int x , int y) {

        return x*y;

    }

    /**
     * this method calculate multiplication of two number
     *
     * @param x first double number
     * @param y second double number
     * @return multiplication of two number
     */
    public static double multiply(double x , double y) {

        return x*y;

    }

    /**
     * this method calculate division of two number
     *
     * @param x first integer number
     * @param y second integer number
     * @return division of two number
     */
    public static double division(int x, int y) {

        try {

            return x/y;

        } catch (ArithmeticException e) {

            e.printStackTrace();
            return Integer.MAX_VALUE;

        }

    }

    /**
     * this method calculate division of two number
     *
     * @param x first double number
     * @param y second double number
     * @return division of two number
     */
    public static double division(double x, double y) {

        try {

            return x/y;

        } catch (ArithmeticException e) {

            e.printStackTrace();
            return Integer.MAX_VALUE;

        }

    }

    /**
     * this method calculate square of number
     *
     * @param x expected number
     * @return square of number
     */
    public static double square(double x) {

        return Math.pow(x, 2);

    }

    /**
     * this method calculate square roor of number
     *
     * @param x expected number
     * @return square root of number
     */
    public static double squareRoot(double x) {

        if(x>=0) {

            return Math.sqrt(x);

        }

        return Integer.MAX_VALUE;

    }

}
