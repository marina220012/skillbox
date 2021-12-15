package home_work_1;

import java.util.Scanner;

public class Symbol {
    public static void main(String[] args) {
        Scanner enter=new Scanner(System.in);
        System.out.print("Введите код ");
        int kod=enter.nextInt();
        if((kod>=65 && kod <=90) || (kod>=97 && kod <=122)) System.out.println("Код буквы");
        else System.out.println("Введен код символа");
    }
}