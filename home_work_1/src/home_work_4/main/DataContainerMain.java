package home_work_4.main;

import home_work_4.dto.DataContainer;

import static java.sql.Types.NULL;

public class DataContainerMain{
    public static void main(String[] args) {
        example1();
        example2();
        example3();
        example4();
        example5();
        example6();
        example7();
        example8();
        example9();
        example10();
        example11();
        example12();
        example13();
    }
    public static void example1(){
        DataContainer<String> container = new DataContainer<String>(new String[]{" "});
        int index1 = container.add("Привет");
        int index2 = container.add("Как дела");
        int index3 = container.add("Работаю");
        int index4 = container.add("Давай потом");
        String text1 = container.get(index1);
        String text2 = container.get(index2);
        String text3 = container.get(index3);
        String text4 = container.get(index4);
        System.out.println(text1); //Привет
        System.out.println(text2); //Как дела
        System.out.println(text3); //Работаю
        System.out.println(text4); //Давай потом
        container.delete(text1);
        System.out.println("_________");
        System.out.println(container.get(index1)); //Как дела
    }
    public static void example2(){
        DataContainer<Integer> data=new DataContainer<Integer>(new Integer[]{1, 2, 3, null, null, null});
        data.add(777);
        System.out.println(data);

    }
    public static void example3(){
        DataContainer<Integer> data=new DataContainer<Integer>(new Integer[]{1, 2, 3, null, null, null});
        data.add(null);
        System.out.println(data);

    }
    public static void example4(){
        DataContainer<Integer> data=new DataContainer<Integer>(new Integer[]{1, null, 3, null, null, null});
        data.add(777);
        System.out.println(data);

    }
    public static void example5(){
        DataContainer<Integer> data=new DataContainer<Integer>(new Integer[]{1, 2, 3});
        data.add(777);
        System.out.println(data);

    }
    public static void example6(){
        DataContainer<Integer> data=new DataContainer<Integer>(new Integer[]{});
        data.add(777);
        System.out.println(data);

    }
    public static void example7(){
        DataContainer<Integer> data=new DataContainer<Integer>(new Integer[]{});
        System.out.println(data.get(data.add(999)));

    }

    public static void example8(){
        DataContainer<Integer> data=new DataContainer<Integer>(new Integer[]{999});
        System.out.println(data.get(1));

    }
    public static void example9(){
        DataContainer<Integer> data=new DataContainer<Integer>(new Integer[]{1, 2, 3, 777});
       data.delete(3);
        System.out.println(data);
    }
    public static void example10(){
        DataContainer<Integer> data=new DataContainer<Integer>(new Integer[]{1, 2, 3, 777});
        data.delete(9);
        System.out.println(data);
    }
    public static void example11(){
        DataContainer<Integer> data=new DataContainer<Integer>(new Integer[]{1, 2, 3, 777});
        data.delete(2);
        System.out.println(data);
    }
    public static void example12(){
        DataContainer<Integer> data=new DataContainer<Integer>(new Integer[]{1, 2, 3, 777});
        //Integer forDelete=777;
        data.delete((Integer)777);
        System.out.println(data);
    }
    public static void example13(){
        DataContainer<Integer> data=new DataContainer<Integer>(new Integer[]{1, 2, null, 777,56,454});
        //Integer forDelete=111;
        data.delete((Integer)2);
        System.out.println(data);
    }
}
