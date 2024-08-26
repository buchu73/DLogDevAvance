package org.example;

public class Calculator {
    public int add(int a, int b){
        return a + b;
    }

    public int div(int a, int b) throws ArithmeticException{
        return a / b;
    }

    public String divString(int a, int b) {
        try {
            int result = a/b;
            return String.valueOf(result);
        }
        catch (ArithmeticException e)
        {
            return "Cannot divide by zero";
        }
    }

    public double divDouble(double a, double b) throws ArithmeticException{
        return a / b;
    }
}
