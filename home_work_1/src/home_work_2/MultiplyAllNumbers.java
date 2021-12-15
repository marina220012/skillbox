package home_work_2;

import java.util.Random;
import java.util.Scanner;

public class MultiplyAllNumbers {
    public static void main(String[] args) {
        int number=0, multiplication=1;
        Scanner enter= new Scanner(System.in);
        System.out.println("Введите числа подряд без пробела");
        String s = enter.nextLine();
      //  if(s.contains("1") ||s.contains("2")||s.contains("3") ||s.contains("4")||s.contains("5")||s.contains("6")
              //  ||s.contains("7")||s.contains("8")||s.contains("9")||s.contains("0")){
            int forDividing= Integer.parseInt(s);
            while (forDividing/1!=0){
                //number++;
                multiplication*=number%10;
                forDividing/=10;
            };
        //}else System.out.println("Вы ввели не число");
        System.out.println(multiplication);
     //   System.out.println("Result is " + multiplication);
    }
}
