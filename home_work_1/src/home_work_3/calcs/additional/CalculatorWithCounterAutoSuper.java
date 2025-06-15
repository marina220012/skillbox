package home_work_3.calcs.additional;

import home_work_3.calcs.simple.CalculatorWithMathExtends;

public class CalculatorWithCounterAutoSuper extends CalculatorWithMathExtends {
    private int countOperation;

    public void incrementCountOperation(){
        this.countOperation++;
    }
    public long getCountOperation(){
        return countOperation;
    }


    @Override
    public int absolute(int x) {
        incrementCountOperation();
        return super.absolute(x);
    }

    @Override
    public double pow(double x, int pow) {
        incrementCountOperation();
        return super.pow(x, pow);
    }

    @Override
    public double squareRoot(int x, int pow) {
        incrementCountOperation();
        return super.squareRoot(x, pow);
    }

    @Override
    public int add(int x1, int x2) {
        incrementCountOperation();
        return super.add(x1, x2);
    }

    @Override
    public double add(double x1, double x2) {
        incrementCountOperation();
        return super.add(x1, x2);
    }

    @Override
    public int subtraction(int x1, int x2) {
        incrementCountOperation();
        return super.subtraction(x1, x2);
    }

    @Override
    public int multiplication(int x1, int x2) {
        incrementCountOperation();

        return super.multiplication(x1, x2);
    }

    @Override
    public double division(int x1, int x2) {
        incrementCountOperation();

        return super.division(x1, x2);
    }
    @Override
    public double division(double x1, int x2) {
        incrementCountOperation();

        return super.division(x1, x2);
    }
}
