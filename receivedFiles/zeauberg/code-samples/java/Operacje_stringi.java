import java.util.Scanner;
//import.java.util.*;

class Operacje_stringi{
        
public static void main(String [] args){
        
	Scanner sc = new Scanner(System.in);
	String wyraz = sc.next("[A-Z]");
	
	//System.out.print("nana" + wyraz);

	//Porownywanie Stringow
	String s = "aa";
	String sub1 = s.substring(0);
	
	System.out.println(s + " " + sub1);
	System.out.println(s.equals(sub1));

}
}
