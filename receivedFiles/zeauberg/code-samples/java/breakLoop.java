import java.util.*;

class breakLoop{





	public static void main(String [] args){


		outer: for(int i=0; i<10; ++i){
			for(int j=0; j<10; ++j){
				System.out.println(i);
				if(j == 5) break outer;
			}
		}

	}


}
