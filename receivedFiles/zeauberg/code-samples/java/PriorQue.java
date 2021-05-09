import java.util.*;

class PriorQue{
	public static void main(String [] args){
		PriorityQueue<Integer> koleja = new PriorityQueue<>(5);

		koleja.add(100);
		koleja.add(12);
		koleja.add(212);
		koleja.add(12);
		koleja.add(10);

		for(int i : koleja) System.out.print(i);

		koleja.remove(12);
		System.out.println("");

		for(int i : koleja) System.out.print(i);

	}
}
