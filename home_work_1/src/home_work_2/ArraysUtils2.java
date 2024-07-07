package home_work_2;

import java.util.Scanner;

public class ArraysUtils2 {
    public static void main(String[] args) {
        int[] container = arrayFromConsole();
        System.out.println("Введенный массив");
        DoWhile(container);
        System.out.println();
        While(container);
        System.out.println();
        For(container);
        System.out.println();

        Foreach(container);
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
    public static void DoWhile(int[] arr){
        int i=0;
        do{
            System.out.print(arr[i]+" ");
            i++;
        } while (i<arr.length);
    }
    public static void While(int[] arr){
        int i=0;
        while (i<arr.length){
            if(i%2!=0){
                 System.out.print(arr[i]+" ");}
            i++;
        }
    }
    public static void For(int[] arr){
        for (int i=arr.length-1; i>=0; i--) {
            System.out.print(arr[i] + " ");
        }
    }
    public static void Foreach(int[] arr){

        for (int consist:arr) {
            System.out.print(consist+" ");
        }
    }
}
