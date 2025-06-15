package home_work_3.calcs.api;

public interface ICalculator {
    public int add(int x1, int x2) ;
    public double add(double x1, double x2);
    public int subtraction(int x1, int x2);

    public int multiplication(int x1, int x2);
    public double division(int x1, int x2);
    public double pow(double x, int pow);
    public int absolute(int x);
    public double squareRoot(int x, int pow);
}
