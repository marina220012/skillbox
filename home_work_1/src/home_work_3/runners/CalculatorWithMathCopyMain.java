package home_work_3.runners;

import home_work_3.calcs.simple.CalculatorWithMathCopy;

public class CalculatorWithMathCopyMain {
    public static void main(String[] args) {
        CalculatorWithMathCopy number=new CalculatorWithMathCopy();
        double res= number.add(number.add(4.1, number.multiplication(15,7)),
                number.pow(number.division(28.0,5), 2));//140.45999999
        System.out.println("Результат "+res);
    }
}
