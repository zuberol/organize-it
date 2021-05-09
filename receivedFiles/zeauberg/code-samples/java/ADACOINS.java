import java.util.ArrayList;
import java.util.*;	//List, Scanner
//import java.util.LinkedList<E>;
import java.util.Collections;
import java.lang.String;

class ADACOINS{

public static void main(String [] args){
	
	Scanner sc = new Scanner(System.in);
	

	System.out.println("N:");
	int N = sc.nextInt();
	System.out.println("Q:");
	int Q = sc.nextInt();
	int Chances = 0;
	int Coins_ok = 0;
	
		//czego to dziala? List<Integer> Coins = new LinkedList<>();
	ArrayList<Integer> Coins = new ArrayList<>();
	
	for(int j=0; j<N; ++j){
		System.out.println("N coins:");
		Coins.add(sc.nextInt());
	}
	
	for(int i=0; i<Q; ++i){
		System.out.println("B:");
		int B = sc.nextInt();
		System.out.println("E:");
		int E = sc.nextInt();
		ListIterator<Integer> Coins_i = Coins.listIterator();
		while(Coins_i.hasNext()){
			int some_coin = Coins_i.next();
			if( B < some_coin && some_coin < E){
				++Coins_ok;
			}
			else{
				continue;
			}
			
		}
		
		System.out.println("Coins ok: " + String.valueOf(Coins_ok));
		//System.out.print();
	}	




}
}
