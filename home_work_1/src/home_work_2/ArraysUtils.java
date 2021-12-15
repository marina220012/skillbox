package home_work_2;

import java.util.Random;
import java.util.Scanner;

public class ArraysUtils {
    public static void main(String[] args) {
        int[] container = arrayFromConsole();
        System.out.println("Введенный массив");
        for (int i=0; i<container.length;i++)
            System.out.print(container[i]+" ");
        System.out.println();
        int[] container2 = arrayRandom(5,100);
        System.out.println("Рандомный массив");
        for (int i=0; i<container2.length;i++)
            System.out.print(container2[i]+" ");
    }
    public static int[] arrayFromConsole(){
        Scanner enter=new Scanner(System.in);
        System.out.println("Введите размер массива ");
        int size=enter.nextInt();
        int[] array=new int[size];
        for (int i=0; i<array.length;i++){
           System.out.println("Введите "+i+" элемент массива");
           array[i]=enter.nextInt();
        }
        return array;
    }
    public static int[] arrayRandom(int size, int maxValueExclusion){
        int[] array = new int[size];
        Random rnd = new Random();
        int diff=maxValueExclusion-0;
        for (int i=0; i<array.length; i++)
        {
            array[i]= rnd.nextInt(diff);
        }
        return array;
    }


}
