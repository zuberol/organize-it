import java.util.*;
class myRSA{
	public static void main(String [] args){
		//int C = 10;
		//int e = 5;
		//int n = 35;

		int C = 10, e = 5, n = 35;
		int M = 1;

		while(C != (int)Math.pow(M,e) % n ){
			System.out.println(M);
			++M;
		}
		System.out.println(String.format("\n M == %d",M));
	}
}
