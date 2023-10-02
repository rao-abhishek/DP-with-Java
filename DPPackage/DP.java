package DPPackage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

interface IDP 
{
    boolean canConstruct(int targetNum, int[] nums);
    boolean canConstruct(int targetNum, int[] nums, HashMap<Integer, Boolean> memo);
    int howManyConstruct(int targetNum, int[] nums);
    int howManyConstruct(int targetNum, int[] nums,int index, HashMap<String, Integer> memo);
    List<List<Integer>> getAllConstructs(int targetNum, int[] nums);
    List<List<Integer>> getAllConstructs(int targetNum, int[] nums, int index, HashMap<String, List<List<Integer>>> memo);
    List<Integer> getBestConstructs(int targetNum, int[] nums);
    List<Integer> getBestConstructs(int targetNum, int[] nums, int index, HashMap<String, List<Integer>> memo);
}

public class DP implements IDP{

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
			return new ArrayList<>();
		}
		
		List<Integer> bestRes = null;
	
		for(int i = index; i<nums.length; i++)
		{
            List<Integer> curRes = getBestConstructs(targetNum-nums[i], nums, i, memo);
            List<Integer> newRes = new ArrayList<>(curRes);
            newRes.add(nums[i]);
			if(bestRes==null || bestRes.size()>newRes.size())
            {
                bestRes = newRes;
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
        return canConstruct(targetNum, nums, new HashMap<Integer, Boolean>());
    }

    @Override
    public boolean canConstruct(int targetNum, int[] nums, HashMap<Integer, Boolean> memo)
    {
       if(targetNum==0)
       {
        memo.put(targetNum, true);
        return true;
       }

       for(int i : nums)
       {
        if(canConstruct(targetNum - i, nums, memo)==true)
        {
            memo.put(targetNum, true);
            return true;
        }
       }
       memo.put(targetNum, false);
       return false;
    }
}
