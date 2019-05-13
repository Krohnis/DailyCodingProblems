package DCP;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class Interval
{
	int start, end;
	Interval(int start, int end)
	{
		this.start = start;
		this.end = end;
	}
}

public class DCP77E 
{
	public static void main(String[] args)
	{
		DCP77E cp = new DCP77E();
		int N = 100;
		Interval arr[] = new Interval[N];
		arr = cp.intervalFactory(N);
		//cp.printIntervals(arr, N);
		Arrays.sort(arr, new Comparator<Interval>() {
			public int compare(Interval o1, Interval o2) 
			{
				return o1.start - o2.start;
			}
			
		});
		//cp.printIntervals(arr, N);
		arr = cp.mergeIntervals(arr, N);
		N = arr.length;
		//cp.printIntervals(arr, N);
		System.out.println(cp.test());
	}
	
	//Runtime O(NlogN) && Space O(N)
	public Interval[] mergeIntervals(Interval[] arr, int N)
	{
		if ((arr.length < 1) || (N < 1)) { return null; }
		Interval prev = arr[0];
		int validIntervals = 0;
		
		for (int i = 1; i < N; i++)
		{
			Interval curr = arr[i];
			if (prev.end >= curr.end)
			{
				arr[i] = arr[i - 1];
				validIntervals++;
			}
			else
			{
				prev = curr;
			}
		}
		
		Interval[] newArr = new Interval[N - validIntervals];
		newArr[0] = arr[0];
		prev = arr[0];
		
		for (int i = 1, j = 1; i < arr.length; i++)
		{
			Interval curr = arr[i];
			if ((curr != prev))
			{
				newArr[j] = curr;
				j++;
			}
			prev = curr;
		}
		
		return newArr;
	}
	
	public Interval[] intervalFactory(int N)
	{
		Interval arr[] = new Interval[N];
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		
		for (int i = 0; i < N; i++)
		{
			int start = rand.nextInt(20);
			int end = rand.nextInt(20) + start;
			arr[i] = new Interval(start, end);
		}
		
		return arr;
	}
	
	public void printIntervals(Interval[] arr, int N)
	{
		System.out.print("{{" + arr[0].start + ", " + arr[0].end + "}");
		for (int i = 1; i < N; i++)
		{
			System.out.print(", {" + arr[i].start + ", " + arr[i].end + "}");
		}
		System.out.print("}\n");
	}
	
	public int test()
	{
		Map<Integer, Integer> map = new HashMap();
		map.put(0, 10);
		return map.get(0);
	}
}