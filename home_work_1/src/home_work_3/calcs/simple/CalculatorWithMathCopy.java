package home_work_3.calcs.simple;

import home_work_3.calcs.api.ICalculator;

public class CalculatorWithMathCopy implements ICalculator {
    public int add(int x1, int x2) {
        return x1+x2;
    }
    public double add(double x1, double x2) {
        return x1+x2;
    }
    public int subtraction(int x1, int x2){
        return x1-x2;
    }

    public int multiplication(int x1, int x2){
        return x1*x2;
    }
    public double division(int x1, int x2){
        return (x2==0)? 0: x1/x2;
    }
    public double division(double x1, int x2){
        return (x2==0)? 0: x1/x2;
    }
    public double pow(double x, int pow){
        return Math.pow(x,pow);
    }
    public int absolute(int x){
        return Math.abs(x);
    }
    public double squareRoot(int x, int rootValue){
        return Math.pow(x, 1/rootValue);
    }

}
