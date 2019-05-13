package DCP;

public class DCP74M 
{
	/*
	 * Given integers N and X, write a function that returns the number of times X appears as a value in an N by N multiplication table.
	 */
	public static void main(String[] args)
	{
		DCP74M cp = new DCP74M();
		System.out.println(cp.multiplicationTable(12, 12));
	}
	
	/*
	 * Runtime O(N) && Space O(1). Could be done instead with a doubly-nested loop, and looking for the target
	 * X value throughout the multiplication table however that would have an O(N^2) Runtime
	 */
	public int multiplicationTable(int N, int X)
	{
		int ret = 0;
		
		for (int i = 1; i <= N; i++)
		{
			if (((i * N) >= X) && ((X % i) == 0)) { ret++; }
		}
		return ret;
	}
}
