package DCP;

import java.util.Arrays;

/*
 * badNumbers; Return the intervals of numbers between provided boundaries and set badNumbers
 * 
 * Solving the problem without sorting the array would mean a doubly-nested loop, wherein the programmer must check for the smallest difference in range
 * as that would mean the two values have no badNumbers between them. Then that difference value would have to be compared with a maximum to determine if 
 * the value was the largest.
 * 
 * Alternatively, you could iterate from l to r, and increment a counter with every step. When encountering a bad value, count is reset and the iteration continues.
 * E.g. l = 0, r = 1,000, arr = { 1 } would mean 1,000 iterations with the if statement triggering once.
 * This would be a poor solution.
 */
public class DCP00M 
{
	public static void main(String[] args)
	{
		DCP00M cp = new DCP00M();
		int[] arr = { -10, -4, 8, 2, 16, 32, 27, 18 };
		int l = 0, r = 30;
		cp.arrayToString(arr, l, r);
		System.out.println("Largest Interval is " + cp.badNumbersA(arr, l, r));
	}
	
	/*
	 * Iterative solution; Runtime O(NlogN) && Space O(1). The solution has a runtime of NlogN as it has to make use of sorting, and a space that is constant
	 * due to the fact that it does not utilize any data structures, therefore the space complexity never scales based on the size of any input
	 */
	public int badNumbersA(int[] badNumbers, int l, int r)
	{
		//Base case, if the array provided is empty then it returns 0
		if ((badNumbers == null) || (badNumbers.length < 1)) { return 0; }
		//The return is set to the minimum value as we're looking for the largest interval
		int ret = Integer.MIN_VALUE, size = badNumbers.length - 1;
		
		//Sorting of the array, this has a runtime of O(NlogN) and is necessary for the simple solution to work
		Arrays.sort(badNumbers);
		
		//Set the previous value to either the lowest badNumber, or to the lower bound if 
		int previous = Math.max(badNumbers[0] + 1, l), current = 0;
		//Iteration of the problem, has Runtime O(N)
		for (int i = 1; i <= size; i++)
		{
			//Set the variable current to the current (badNumber - 1). The reason for the minus is because we can't include the badNumber in the range of valid values
			current = badNumbers[i] - 1;
			//If current falls outside of the range, then it is reset to fall between. This assumes that proper bounds are provided, and l <= r
			if (current <= l) { current = l; }
			if (current >= r) { current = r; }
			//Check if the max value is less than this new interval, if it is, then replace it, if not, then do not change anything
			ret = Math.max(current - previous + 1, ret);
			previous = Math.max(badNumbers[i] + 1, l);
		}
		//This checks the final interval, from current to r. If current is less than r we check the interval to see if it is larger than previous intervals
		current = Math.max(badNumbers[size], l);
		if ((current) < r) { ret = Math.max(r - current, ret); }
		
		return ret;
	}
	
	//Simple print statement to show what the array, and the ranges are.
	public void arrayToString(int[] arr, int l, int r)
	{
		//Error check, if the array is empty simply return as no operations can be performed
		if ((arr == null) || (arr.length < 1)) { return; }
		System.out.print("For Array : {" + arr[0]);
		for (int i = 1; i < arr.length; i++)
		{
			System.out.print(", " + arr[i]);
		}
		System.out.print("} with bounds " + l + ", and " + r + "\n");
	}
}
