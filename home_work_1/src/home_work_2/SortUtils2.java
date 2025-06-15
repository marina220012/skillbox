package home_work_2;

import java.util.Random;
import java.util.Scanner;

public class SortUtils2 {
    public static void main(String[] args) {
        Scanner enter=new Scanner(System.in);
        System.out.println("Введите количество цифр в массиве ");
        int size=enter.nextInt();
        int[] container=arrayRandom(size,30);
        whatToDo(container);

    }

    public static void whatToDo(int[] arr){
        System.out.println("\nВведенный массива");
        show(arr);
        arr =shakerSort(arr);
        System.out.println("\nОтсортированный массив");
        show(arr);
    }

    public static void show(int[] arr){
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i]+" ");
        }
    }

    public static int[] arrayRandom(int size, int maxValueExclusion){
        int[] array = new int[size];
        Random rnd = new Random();
        int diff=maxValueExclusion-0;
        for (int i=0; i<array.length; i++)
        {
            array[i]= rnd.nextInt(diff);
            // System.out.println(array[i]);
        }
        return array;
    }

    public static int[] shakerSort(int[] arr){
        int left=0, right=arr.length-1;
        while(left<=right){
            for(int i=left; i<right;i++) {
                if (arr[i] > arr[i + 1]) {
                    int max;
                    max=arr[i];
                    arr[i]=arr[i+1];
                    arr[i+1]=max;
                }
            }
            right--;
            for(int i=right; i>left;i--) {
                if (arr[i-1] > arr[i]) {
                    int max;
                    max=arr[i];
                    arr[i]=arr[i-1];
                    arr[i-1]=max;
                }
            }
            left++;
        }
        return arr;
    }

}
