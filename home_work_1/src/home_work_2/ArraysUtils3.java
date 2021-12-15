package home_work_2;

import java.util.Random;
import java.util.Scanner;

public class ArraysUtils3 {
    public static void main(String[] args) {
        Scanner enter=new Scanner(System.in);
        System.out.println("Введите количество цифр в массиве ");
        int size=enter.nextInt();
        int[] container=arrayRandom(size,30);
        System.out.println("Сумма равна " + sum(container));
        System.out.println("Максимальный четный элемент массива "+max(container));
        int[] cont=elementsWhichAreSmallerThanAverage(container);
        System.out.println("Числа, которые меньше среднего");
        for (int i=1; i<=cont[0];i++){
            System.out.print(" "+cont[i]);
        }
        System.out.println("\n");
        System.out.println("Два минимальных числа "+min1Min2(container)[0]+" "+ min1Min2(container)[1]);
    }

    public static int[] arrayRandom(int size, int maxValueExclusion){
        int[] array = new int[size];
        Random rnd = new Random();
        int diff=maxValueExclusion-0;
        System.out.println("Введенный массив");
        for (int i=0; i<array.length; i++)
        {
            array[i]= rnd.nextInt(diff);
            System.out.println(array[i]);
        }
        return array;
    }

    public static int sum(int[] arr) {
        int sum=0;
        for (int i=0; i<arr.length; i++){
            if(arr[i]%2==0){
                sum+=arr[i];
            }
        }
        return sum;
    }

    public static int max(int[] arr){
        int max=arr[1];
        for (int i=3; i<arr.length; i+=2){
            if(arr[i]>max){
                max=arr[i];
            }
        }
        return max;
    }

    public static int[] elementsWhichAreSmallerThanAverage(int[] arr){
        int[] containsElements=new int[arr.length+1];
        int average=0, j=0;
        for (int i=0; i<arr.length; i++){
            average+=arr[i];
        }
        average/=arr.length;
        for (int i=0; i<arr.length; i++){
            if (average>arr[i]){
                j++;
                containsElements[j]=arr[i];
            }
        }
        containsElements[0]=j;
        return containsElements;
    }

    public static int[] min1Min2(int[] arr){
        int[] min1Min2=new int[2];
        min1Min2[0]=arr[0];
        min1Min2[1]=arr[1];
        for (int i=2; i<arr.length; i++){
            if(arr[i]<min1Min2[0] ){
                min1Min2[0]=arr[i];
            }else{
                if( arr[i]<min1Min2[1])
                    min1Min2[1]=arr[i];
            }
        }
        return min1Min2;
    }

    public static int[] delete(int[] arr, int beg, int end){
        int[] array=new int[arr.length];
        int i=0
        while (i<=arr.length-1)

    }



}
