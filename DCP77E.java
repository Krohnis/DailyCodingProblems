package DCP;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//Interval class that is used to store the start and ends of intervals
class Interval
{
	int start, end;
	Interval(int start, int end)
	{
		this.start = start;
		this.end = end;
	}
}

/*
 * Simplify the intervals, larger intervals will absorb smaller intervals
 */
public class DCP77E 
{
	public static void main(String[] args)
	{
		DCP77E cp = new DCP77E();
		int N = 10;
		Interval arr[] = new Interval[N];
		//Interval factory generates the intervals that will be operated on
		arr = cp.intervalFactory(N);
		cp.printIntervals(arr, N);
		arr = cp.mergeIntervals(arr, N);
		N = arr.length;
		cp.printIntervals(arr, N);
	}
	
	/*
	 * Runtime O(NlogN) && Space O(N). The iteration itself only takes O(N) time, while the sorting takes O(NlogN) time. 
	 * A second array is created to replace the old array, in the worse case this second array has Space of O(N).
	 */
	public Interval[] mergeIntervals(Interval[] arr, int N)
	{
		//Base case, if the array is empty then return null
		if ((arr.length < 1) || (N < 1)) { return null; }
		
		//O(NlogN) sorting, this sorts by the starting element. If the previous end is less than the current end,
		//then it cannot be contained within that interval
		Arrays.sort(arr, new Comparator<Interval>() {
			public int compare(Interval o1, Interval o2) 
			{
				return o1.start - o2.start;
			}
		});
		
		//Prints the newly sorted Intervals
		printIntervals(arr, N);
		
		//Set the previous to the first element in the array, validIntervals determines the size of the second array
		Interval prev = arr[0];
		int validIntervals = 0;
		
		//Iteration with runtime O(N)
		for (int i = 1; i < N; i++)
		{
			Interval curr = arr[i];
			//If the previous ending value is greater-than or equal-to the current end, then we can consider it inside of the previous interval
			if (prev.end >= curr.end)
			{
				arr[i] = arr[i - 1];
				validIntervals++;
			}
			//Otherwise we move previous to point to the current element
			prev = curr;
		}
		
		//Creation of the new array
		Interval[] newArr = new Interval[N - validIntervals];
		newArr[0] = arr[0];
		prev = arr[0];
		
		//Iteration with runtime O(N), inserts all of the new intervals into the new array
		for (int i = 1, j = 1; i < arr.length; i++)
		{
			Interval curr = arr[i];
			//The interval objects were copied over previously, so if the object reference values are the same then they are the same interval
			if ((curr != prev))
			{
				//I iterates over the first array, J iterates over the new array
				newArr[j] = curr;
				j++;
			}
			prev = curr;
		}
		
		return newArr;
	}
	
	//Interval factory, generates all of the values of the intervals at runtime
	public Interval[] intervalFactory(int N)
	{
		Interval arr[] = new Interval[N];
		Random rand = new Random();
		//Sets the random reed to the current time, making each run as random as the algorithm can manage
		rand.setSeed(System.currentTimeMillis());
		//Determines the range of the values, in this case ranging from lower (0, 20) and upper (0, 40)
		int range = 20;
		
		for (int i = 0; i < N; i++)
		{
			//Determines the random value of start from (0, range)
			int start = rand.nextInt(range);
			//Determines the random value of end from (0, range), then adds the start determined above
			int end = rand.nextInt(range) + start;
			arr[i] = new Interval(start, end);
		}
		
		return arr;
	}
	
	//Prints the intervals to see the outcome of the IntervalFactory, sorting and MergeIntervals
	public void printIntervals(Interval[] arr, int N)
	{
		System.out.print("{{" + arr[0].start + ", " + arr[0].end + "}");
		for (int i = 1; i < N; i++)
		{
			System.out.print(", {" + arr[i].start + ", " + arr[i].end + "}");
		}
		System.out.print("}\n");
	}
}