package home_work_2;

public class SortsUtils {
    public static void main(String[] args) {
        int[] container1 =new int[] {2,3,6,4,1,5};
        int[] container2 =new int[] {1,1,1,1};
        int[] container3 =new int[] {9,1,5,99,9,9};
        whatToDo(container1);
        whatToDo(container2);
        whatToDo(container3);
    }

    public static void whatToDo(int[] arr){
        System.out.println("\nВведенный массива");
        show(arr);
        arr =bubbleSort(arr);
        System.out.println("\nОтсортированный массив");
        show(arr);
    }

    public static void show(int[] arr){
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i]+" ");
        }
    }

    public static int[] bubbleSort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length-1; j++) {
                int max;
                if(arr[j]>arr[j+1]){
                    max=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=max;
                }

            }
        }
        return arr;
    }


}
