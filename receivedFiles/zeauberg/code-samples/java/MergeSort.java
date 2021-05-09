import java.util.*;

class MergeSort{
	
	// merge two sorted arrays
	public static int [] merge(int [] a, int [] b){
		int [] m = new int[a.length + b.length];

		int ia=0;
		int ib=0;
		int i=0;
		while(ia < a.length && ib < b.length){
			if(a[ia] < b[ib]){
				m[i] = a[ia];
				++ia;
			}
			else{
				m[i] = b[ib];
				++ib;
			}
			++i;
		}
		// copy whats left from one array
		while(ia < a.length){
			m[i] = a[ia];
			++ia;
			++i;
		}

		while(ib < b.length){
			m[i] = b[ib];
			++ib;
			++i;
		}

		return m;
	}
	
	public static int [] mergeSort(int [] arr){
		//base case
		if(arr.length == 1) return arr;
		
		int middle = arr.length/2;

		int [] left = new int[middle];
		int	[] right = new int[arr.length - middle];

		
		for(int i=0; i<middle; ++i){
			left[i] = arr[i];
		}
		for(int i=middle; i<arr.length; ++i){
			right[i-middle] = arr[i];
		}
		
		left = mergeSort(left);
		right = mergeSort(right);
		
		return merge(left, right);
	}


	public static void main(String [] args){
	
		int [] data = {5, 20, 3, 14, 100};
		data = mergeSort(data);
		
		for(int i : data) System.out.print(i+" ");
		System.out.println();

	}
}
