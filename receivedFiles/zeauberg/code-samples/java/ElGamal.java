import java.util.*;
class ElGamal{
	public static void main(String [] args){
		
		int k = 1;

		while(59 != (int)Math.pow(7,k) % 71){
			System.out.println(k);
			++k;
		}
		System.out.println(String.format("\n k == %d", k));
	}
}
