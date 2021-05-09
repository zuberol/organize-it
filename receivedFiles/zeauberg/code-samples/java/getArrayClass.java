import java.util.*;
import java.lang.reflect.Array;

class getArrayClass{
	public static void main(String [] args){
		int [] arr = {1,2,3,4,5,6,7,8,9};
		System.out.println(Array.get(arr, 0));
		System.out.println(arr.getClass());
	}
}
