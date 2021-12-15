package home_work_2;

public class MultiplicationTable {
    public static void main(String[] args) {
        for (int i=2;i<=9;i+=5){
            for (int j=1; j<=10;j++){
                for (int k=i; k<i+4; k++){
                    System.out.printf(k+"*"+j+"="+k*j+"  ");
                //System.out.print(k+"*"+j+"="+k*j+" **  ");
                }
                System.out.print("\n");
            }
            System.out.println("***************************************");
        }
    }
}
