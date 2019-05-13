package DCP;

import java.util.HashMap;
import java.util.Map;

/*
 * Programmer string, find the number of characters between two "Programmer" strings
 * Avoids the cost of creating additional Strings in the String Pool and concatenation from the original submission
 */
public class DCP00H 
{
	//Maps could be created locally, but this was arguably simpler.
	private Map<Character, Integer> mapA = new HashMap();
	private Map<Character, Integer> mapB = new HashMap();
	
	public static void main(String[] args)
	{
		DCP00H cp = new DCP00H();
		String s = "regraazwuutmmpdaroerxyzprogramiiiiikklmerrpo";
		String str = "programmer";
		//String s = "yarggaryxywyargy";
		//String str = "gary";
		/*
		 * There may have been a simpler solution without the use of data structures, but I think this solution is reasonable
		 * The reason for using HashMaps as opposed to another structure like an Array or ArrayList is that iteration over those
		 * structures would be necessary to determine if the String was exhausted, meaning a O(S * N) Runtime as opposed to O(N)
		 * 
		 * As HashMaps have constant access time, and resizing is amortized it seems like the best data structure for this problem
		 */
		System.out.println("For the String " + s + " there is a gap of " + cp.programmerString(s, str));
	}
	
	/*
	 * Iterative solution; Runtime O(N) && Space O(S). Can be considered Runtime O(S + N), but we know that N is the dominant term as at most
	 * S equal to N/2.
	 */
	public int programmerString(String s, String str)
	{
		//Check if the String is null, if it is, then there is nothing we can do so we return
		if ((s == null) || (s.length() < 1)) { return 0; }
		//Declare both pointers, one points to the head and the other points to the tail
		int l = 0, r = s.length() - 1;
		
		//Runtime O(S), Space Complexity O(2 * S) simplified to O(S) in worse-case
		//Maps could have been created here as they are not used throughout the class
		mapFactory(str);
		
		//Runtime O(N), iterates over the length of the String s. When l exceeds r, or once both data structures are empty we know
		//that we can terminate the loop
		while ((l < r) && !(mapA.isEmpty()) || !(mapB.isEmpty()))
		{
			//Place both values as temporary character for ease of use
			char cA = s.charAt(l);
			char cB = s.charAt(r);
			//Check if the map is empty, and if it contains the character
			if (!(mapA.isEmpty()) && mapA.containsKey(cA))
			{
				//If the number of character occurrences is 0, meaning that we have exhausted that character, remove it from the map
				if ((mapA.get(cA) - 1) <= 0) { mapA.remove(cA); }
				//Else, decrement the value and put it back in
				else { mapA.put(cA, mapA.getOrDefault(cA, 0) - 1); }
			}
			//The same logic applies for this second half
			if (!(mapB.isEmpty()) && mapB.containsKey(cB))
			{
				if ((mapB.get(cB) - 1) <= 0) { mapB.remove(cB); }
				else { mapB.put(cB, mapB.getOrDefault(cB, 0) - 1); }
			}
			//Finally, if the map is not empty continue searching for the remaining characters, else do remain inplace
			l += (!mapA.isEmpty()) ? 1 : 0;
			r -= (!mapB.isEmpty()) ? 1 : 0;
		}	
		
		return r - l - 1;
	}
	
	public void mapFactory(String s)
	{
		//If the dictionary String is empty, then we return as there are no values to insert
		if ((s == null) || (s.length() < 1)) { return; }
		
		//Iterate through the dictionary String, put the values into their respective hashMaps for later use
		for (int i = 0; i < s.length(); i++)
		{
			mapA.put(s.charAt(i), mapA.getOrDefault(s.charAt(i), 0) + 1);
			mapB.put(s.charAt(i), mapB.getOrDefault(s.charAt(i), 0) + 1);
		}
	}
}
