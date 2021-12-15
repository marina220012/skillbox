package home_work_2;

import java.util.Scanner;

public class MultiplyRecursion {
    public static void main(String[] args) {
        Scanner enter= new Scanner(System.in);
        System.out.println("Введите количество чисел");
        int number = enter.nextInt();
        int result=Multiplaction(1, number);
        System.out.println("Result is " + result);
    }
    public static int Multiplaction(int mult, int i){
        Scanner enter= new Scanner(System.in);
        System.out.println("Введите число " );
        mult=enter.nextInt();
        if(i==1 ){
            return mult;
        }return mult*Multiplaction(mult,i-1);

    }
}


