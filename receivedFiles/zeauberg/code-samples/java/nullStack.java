import java.util.*;

class nullStack{
	public static void main(String [] args){
		Stack<Integer> st = new Stack<>();

		System.out.println(st.empty());
		st.push(null);
		System.out.println(st.empty());
	}
}
