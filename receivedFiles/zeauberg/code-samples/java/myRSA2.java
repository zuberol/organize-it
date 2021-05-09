import java.util.*;
class myRSA2{
	public static void main(String [] args){
		
		int e = 31;
		int n = 3599;
		int nSmall = 58 * 60;
		int d = 1;

		while(e * d  % nSmall != 1){
			System.out.println(d);
			++d;
		}
		System.out.println(String.format("\n d == %d", d));
		System.out.println(e*d % nSmall);
	}
}
