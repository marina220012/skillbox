package home_work_2;

import java.util.Random;
import java.util.Scanner;

public class MultiplyCycle {
    public static void main(String[] args) {
        int multiplication=1;//
        Random rnd=new Random();
        Scanner enter= new Scanner(System.in);
        System.out.println("Введите количество чисел");
        int number = enter.nextInt();
        for (int i=1; i<=number; i++){
            System.out.println("Введите число "+i);
            multiplication*=enter.nextInt();
        }
        System.out.println("Result is " + multiplication);
    }
}
