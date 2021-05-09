import java.util.*;

class Interval implements Comparable<Interval>{
	public final int leftBound, rightBound;
	Interval(int A, int B){
		this.leftBound = Math.min(A,B);
		this.rightBound = Math.max(A,B);
	}
	@Override
	public int compareTo(Interval b){
		return this.leftBound - b.leftBound;
	}
	public void print(){
		System.out.print(String.format("[%d, %d]  ", this.leftBound, this.rightBound));
	}
}

class Union{
	public static void printAll(Interval [] arr){
		for(Interval i : arr) if(i != null) i.print();
	}
	
	public static boolean mergeOverlappingInts(int a, int b, Interval [] arr){
		if(arr[a] == null || arr[b] == null) return false;
		else if(arr[a].rightBound >= arr[b].leftBound){

			arr[a].print();
			arr[b].print();
			System.out.println("-->merge");

			// merge updating first interval
			arr[a] = new Interval(Math.min(arr[a].leftBound, arr[b].leftBound),
									Math.max(arr[a].rightBound, arr[b].rightBound));
			// delete second interval
			arr[b] = null;

			return true;
		}
		return false;
	}

	public static int Solution(int [] A, int [] B){
		
		if(A.length != B.length) return -1;
		
		Interval [] ints = new Interval [A.length];
		
		// create array of Intervals from A and B
		for(int i=0; i<A.length; ++i){
			ints[i] = new Interval(A[i], B[i]);
		}
		
		Arrays.parallelSort(ints);
		Union.printAll(ints);
		System.out.println("");

		int disjoints = A.length;
		// merge intervals
		Outer : for(int i=0; i+1<ints.length; ++i){
			
			//merge with next neighbour
			for(int j=1; i+j<ints.length; ++j){
				if(ints[i] == null) continue Outer;
				else if(mergeOverlappingInts(i, i+j, ints)) --disjoints;
			}
		}

		System.out.print("\n");
		Union.printAll(ints);
		System.out.print("\n");

		return disjoints;
	}


	public static void main(String [] args){
		int [] A = {1, 12, 42, 70, 36, -4, 43, 15};
		int [] B = {5, 15, 44, 72, 36, 2, 69, 24};
	
		System.out.println(String.format("You can counstruct Union with %d disjoint intervals.",Solution(A,B)));
	}
}
