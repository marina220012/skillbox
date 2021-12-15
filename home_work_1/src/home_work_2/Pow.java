package home_work_2;

import java.util.Scanner;

public class Pow {
    public static void main(String[] args) {
        int degree;
        double number;
        Scanner enter= new Scanner(System.in);
        System.out.println("Введите число, которое надо возвести в степень");
        number=enter.nextDouble();
        System.out.println("Введите степень числа");
        degree=enter.nextInt();
        System.out.println(Pow(number,degree));
    }
    public static double Pow(double num, int deg){
        if(deg==1)
            return num;
        return num*Pow(num, deg-1);
    }
}
