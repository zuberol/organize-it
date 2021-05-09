import java.util.*;

class SortArrayOfArrays{

	public static void main(String [] args){
		int[][] twoDim = { {1, 2}, {3, 7}, {8, 9}, {4, 2}, {5, 3} };	
		
		Arrays.sort(twoDim, Comparator.comparingInt(a -> a[0] ));
		
		System.out.println(Arrays.deepToString(twoDim));
	}
}
