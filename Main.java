import DPPackage.*;
public class Main {
    public static void main(String args[])
    {
        NumberConstruct nc = new NumberConstruct();
        System.out.println("Can Construct- " + nc.canConstruct(10, new int[]{1, 2, 3, 5, 6}));
        System.out.println("How Many Construct- " + nc.howManyConstruct(10, new int[]{ 2,3, 5,6}));
        System.out.println("Get All Constructs- " + nc.getAllConstructs(10, new int[]{ 2,3, 5, 6}));
        System.out.println("Get Best Constructs- " + nc.getBestConstructs(100, new int[]{2 ,3, 5, 6}));
    }
}
