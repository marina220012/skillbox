package home_work_3.runners;

import home_work_3.calcs.additional.CalculatorWithCounterClassic;

public class CalculatorWithCounterClassicMain {
    public static void main(String[] args) {
        CalculatorWithCounterClassic number=new CalculatorWithCounterClassic();
        int resultInt=number.multiplication(15,7);
        number.incrementCountOperation();
        double resultDouble2 = number.division(28.0,5);
        number.incrementCountOperation();
        resultDouble2= number.pow(resultDouble2, 2);
        number.incrementCountOperation();
        double resultDouble1 = number.add(4.1, resultInt);
        number.incrementCountOperation();
        resultDouble1= number.add(resultDouble1, resultDouble2);
        //number.add(number.add(4.1, number.multiplication(15,7)),
        //                number.pow(number.division(28,5), 2))
        // 4.1 + 15 * 7 + (28.0 / 5) ^ 2=140.45999999
        number.incrementCountOperation();
        System.out.println("Результат "+ resultDouble1);
        System.out.println("Количество операций "+number.getCountOperation());
    }
}
