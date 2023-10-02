import DPPackage.*;
public class Main {
    public static void main(String args[])
    {
        DP dp = new DP();
        System.out.println("Can Construct- " + dp.canConstruct(10, new int[]{1, 2, 3, 5, 6}));
        System.out.println("How Many Construct- " + dp.howManyConstruct(10, new int[]{ 2,3, 5,6}));
        System.out.println("Get All Constructs- " + dp.getAllConstructs(10, new int[]{ 2,3, 5, 6}));
        System.out.println("Get Best Constructs- " + dp.getBestConstructs(100, new int[]{2 ,3, 5, 6}));
    }
}
