package home_work_3.calcs.simple;

import home_work_3.calcs.api.ICalculator;

public class CalculatorWithOperator implements ICalculator {
    public CalculatorWithOperator() {
    }

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
        double result=1;
        if(pow<0) {
            x=1/x;
        }
        for(int i=0; i<pow; i++){
            result*=x;
        }
        return result;
    }
    public int absolute(int x){
        return (x<0)? x*(-1) : x;
    }
    public double squareRoot(int x, int pow){
       double squareRoot=0;
       while(squareRoot*squareRoot< x){
           squareRoot+=0.1;
       }
        return squareRoot;
    }


}
