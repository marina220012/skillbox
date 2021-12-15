package home_work_1;

public class Number {
    public static void main(String[] args) {
        int a=2,b=2;
        if(a%2!=0 && b%0!=0){
            System.out.println("Оба числа являются нечетными");
        }else{
            if (a%2!=0) System.out.println(a+" - нечетное");
            else {
                if (b%2!=0) System.out.println(b+ " - нечетное ");
                else System.out.println("ни одно из чисел не является нечетным");
            }
        }
    }
}
