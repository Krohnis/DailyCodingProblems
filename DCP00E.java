package DCP;

import java.util.HashMap;
import java.util.Map;

/*
 * Removal of chocolates; Fails due to integer overflow after N = 55
 */
public class DCP00E 
{
	/*
	 * TLDR; I used a map because it was the first data structure that came to mind.
	 * A map is unnecessary in this case as we know that the array will be of size N. Because of this we can make a fixed array.
	 * A map can also be detrimental as resizing the map will have a runtime cost, however due to amortization for sufficiently large
	 * N this becomes negligible and be simplified to O(1) runtime. 
	 */
	private static Map<Integer, Integer> map = new HashMap();
	
	public static void main(String[] args)
	{
		DCP00E cp = new DCP00E();
		int n = 8;
		System.out.println("Recursive Chocolates: " + n + " has " + cp.chocolateBoxA(n) + " solutions");
		map.clear();
		System.out.println("Iterative Chocolates: " + n + " has " + cp.chocolateBoxB(n) + " solutions");
		System.out.println("Array Itr Chocolates: " + n + " has " + cp.chocolateBoxC(n) + " solutions");
	}
	
	/*
	 * Recursive solution; Runtime O(N) && Space O(N). Uses Memoization to prevent exploration of previously explored permutations.
	 * e.g. (4 - 3) = 1 && (4 - 1 - 1 -1) = 1 will both have the same result, regardless of how it got there.
	 * Primary issues are overflows, either primitive int cannot hold large values, or N is sufficiently large to cause a stack overflow
	 */
	public int chocolateBoxA(int n)
	{
		//Checks if the route has been explored previously, if it has, just return the result
		if (map.containsKey(n)) { return map.get(n); }
		//Base case, if the value is at 0 it is a valid solution, if it is less than 0, it is an invalid solution.
		if ((n == 0)) { return 1; }
		if ((n <= -1)) { return 0; }
		//Recursive calls to lower N values, return the results of these recursive calls to the parent
		int sum = chocolateBoxA(n - 3) + chocolateBoxA(n - 1);
		map.put(n, sum);
		return sum;
	}
	
	/*
	 * Iterative solution; Runtime O(N) && Space O(N). Uses Memoization to remember previous values and determine final value
	 * Unlike the recursive solution, it does not use any additional stack space, and so should not suffer from a stack overflow for large N
	 * Still suffers from what I believe to be int overflow, as the numbers fluctuate between positive and negative values after N = 55
	 */
	public int chocolateBoxB(int n)
	{
		//Base case, if both values are less than three, there is no other solution other than -1 to N.
		if ((n == 1) || (n == 2)) { return 1; }
		//Place the base case values into the map. 
		map.put(0, 1); map.put(1, 1); map.put(2,  1); 
		int i = 3, ret = 0;
		
		//Iterate from 3 to N, starting at 3 as if N were equal to 0, 1, or 2 it would have been caught by the base case return statement
		while (i <= n)
		{
			//Check the map for previous values, then set/add them to ret before putting them into the map.
			ret = map.get(i - 3) + map.get(i - 1);
			map.put(i, ret);
			i++;
		}
		
		return map.get(n);
	}
	
	/*
	 * Iterative solution; Runtime O(N) && Space O(N). Again, similar to the above solution however instead of a map it makes use of a fixed array
	 * Just another way to approach the same problem with a different data structure
	 */
	public int chocolateBoxC(int n)
	{
		//Same rules apply as the above method;
		if ((n == 1) || (n == 2)) { return 1; }
		int[] arr = new int[n + 1];
		arr[0] = 1; arr[1] = 1; arr[2] = 1;
		int i = 3, ret = 0;
		
		while (i <= n)
		{
			ret = arr[i - 3] + arr[i - 1];
			arr[i] = ret;
			i++;
		}
		return arr[n];
	}
}