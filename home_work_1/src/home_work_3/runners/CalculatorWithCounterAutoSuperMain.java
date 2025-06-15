package home_work_3.runners;

import home_work_3.calcs.additional.CalculatorWithCounterAutoSuper;

public class CalculatorWithCounterAutoSuperMain {
    public static void main(String[] args) {
        CalculatorWithCounterAutoSuper number=new CalculatorWithCounterAutoSuper();
        double res= number.add(number.add(4.1, number.multiplication(15,7)),
                number.pow(number.division(28.0,5), 2));//134.1
        System.out.println("Результат "+res);
        System.out.println("Количество операций "+number.getCountOperation());
    }
}
