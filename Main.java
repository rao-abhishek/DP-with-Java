import DPPackage.*;
public class Main {
    public static void main(String args[])
    {
        NumberConstruct nc = new NumberConstruct();
        
        int targetNum = 10;
        int[] nums = new int[]{2, 3, 5, 6};

        System.out.println("Can Construct- " + nc.canConstruct(targetNum, nums));
        System.out.println("How Many Construct- " + nc.howManyConstruct(targetNum, nums));
        System.out.println("Get All Constructs- " + nc.getAllConstructs(targetNum, nums));
        System.out.println("Get Best Constructs- " + nc.getBestConstructs(targetNum,nums));
    }
}
