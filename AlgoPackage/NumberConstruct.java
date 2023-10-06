package AlgoPackage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

interface INumberConstruct
{
    boolean canConstruct(int targetNum, int[] nums);
    boolean canConstruct(int targetNum, int[] nums, HashMap<String, Boolean> memo, int index);
    int howManyConstruct(int targetNum, int[] nums);
    int howManyConstruct(int targetNum, int[] nums,int index, HashMap<String, Integer> memo);
    List<List<Integer>> getAllConstructs(int targetNum, int[] nums);
    List<List<Integer>> getAllConstructs(int targetNum, int[] nums, int index, HashMap<String, List<List<Integer>>> memo);
    List<Integer> getBestConstructs(int targetNum, int[] nums);
    List<Integer> getBestConstructs(int targetNum, int[] nums, int index, HashMap<String, List<Integer>> memo);
}

public class NumberConstruct implements INumberConstruct {

    public static void run() {
        NumberConstruct nc = new NumberConstruct();
        for(int i = 0; i<=10; i++)
        {
            int targetNum = i;
            int[] nums = new int[]{2, 3, 5, 6};

            System.out.println("Can Construct- " + nc.canConstruct(targetNum, nums));
            System.out.println("How Many Construct- " + nc.howManyConstruct(targetNum, nums));
            System.out.println("Get All Constructs- " + nc.getAllConstructs(targetNum, nums));
            System.out.println("Get Best Constructs- " + nc.getBestConstructs(targetNum,nums));
        }
    }

    @Override
    public List<Integer> getBestConstructs(int targetNum, int[] nums)
    {
        return getBestConstructs(targetNum, nums, 0, new HashMap<String, List<Integer>>());
    }

	@Override
	public List<Integer> getBestConstructs(int targetNum, int[] nums, int index, HashMap<String, List<Integer>> memo)
	{
        if(memo.containsKey(targetNum+"_"+index))
        {
            return memo.get(targetNum+"_"+index);
        }

		if(targetNum==0)
		{
			return new ArrayList<Integer>();
		}
		
		if(targetNum<0)
		{
			return null;
		}
		
		List<Integer> bestRes = null;
	
		for(int i = index; i<nums.length; i++)
		{
            List<Integer> curRes = getBestConstructs(targetNum-nums[i], nums, i, memo);
            if(curRes!=null)
            {
                List<Integer> newRes = new ArrayList<>(curRes);
                newRes.add(nums[i]);
                if(bestRes==null || bestRes.size()>=newRes.size())
                {
                    bestRes = newRes;
                }
            }
		}

        memo.put(targetNum+"_"+index, bestRes);
		return bestRes;
	}

    @Override
    public List<List<Integer>> getAllConstructs(int targetNum, int[] nums){
        return getAllConstructs(targetNum, nums, 0, new HashMap<String, List<List<Integer>>>());
    }

    @Override
    public List<List<Integer>> getAllConstructs(int targetNum, int[] nums, int index, HashMap<String, List<List<Integer>>> memo) {
        
        if(memo.containsKey(targetNum+"_"+index))
        {
            return memo.get(targetNum+"_"+index);
        }
        
        if(targetNum==0)
        {
            List<List<Integer>> res = new ArrayList<>();
            res.add(new ArrayList<>()); 

            memo.put(targetNum+"_"+index, res);
            return res;
        }

        if(targetNum<0)
        {
            List<List<Integer>> res = new ArrayList<>();
            memo.put(targetNum+"_"+index, res);
            return res;
        }

         List<List<Integer>> res  = new ArrayList<>();

         for(int i = index; i<nums.length; i++)
         {
            List<List<Integer>> curRes = getAllConstructs(targetNum - nums[i], nums, i, memo);
            for (List<Integer> subCombination : curRes) {
                List<Integer> newCombination = new ArrayList<>(subCombination);
                newCombination.add(nums[i]);
                res.add(newCombination);
            }
         }

       memo.put(targetNum+"_"+index, res);
       return res;
    }

    @Override
    public int howManyConstruct(int targetNum, int[] nums) {
        return howManyConstruct(targetNum, nums, 0, new HashMap<String,Integer>());
    }

    @Override
    public int howManyConstruct(int targetNum, int[] nums, int index, HashMap<String, Integer> memo) {
        if (memo.containsKey(targetNum+"_"+index)) {
            return memo.get(targetNum+"_"+index);
        }
    
        if (targetNum == 0) {
            memo.put(targetNum+"_"+index, 1);
            return 1;
        }
    
        if (targetNum < 0) {
            memo.put(targetNum+"_"+index, 0);
            return 0;
        }
    
        int totalWays = 0;
    
        for (int i = index; i < nums.length; i++) {
            totalWays += howManyConstruct(targetNum - nums[i], nums, i, memo);
        }
    
        memo.put(targetNum+"_"+index, totalWays);
        return totalWays;
    }
    
    @Override
    public boolean canConstruct(int targetNum, int[] nums)
    {
        return canConstruct(targetNum, nums, new HashMap<String, Boolean>(), 0);
    }

    @Override
    public boolean canConstruct(int targetNum, int[] nums, HashMap<String, Boolean> memo, int index)
    {
       if(memo.containsKey(targetNum+"_"+index))
       {
        return memo.get(targetNum+"_"+index);
       }   
       if(targetNum==0)
       {
        memo.put(targetNum+"_"+index, true);
        return true;
       }

       if(targetNum<0)
       {
        memo.put(targetNum+"_"+index, false);
        return false;
       }

       for(int i = index; i<nums.length; i++)
       {
        if(canConstruct(targetNum - nums[i], nums, memo, i)==true)
        {
            memo.put(targetNum+"_"+index, true);
            return true;
        }
       }
       memo.put(targetNum+"_"+index, false);
       return false;
    }
}
