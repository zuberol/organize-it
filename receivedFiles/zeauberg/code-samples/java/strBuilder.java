import java.lang.*;

class strBuilder{

public static void main(String [] args){
   String s = "abcdefgh";
   StringBuilder sb = new StringBuilder(s);
	
	char c = '\0';
	System.out.println(c);
	char arr [] = {'a','b','0','d','\0','e'};
	System.out.println(new String(arr));

   //System.out.println(sb.length());
   //sb.deleteCharAt(1); 
   //System.out.println(sb.length());
   //System.out.println(sb);
}
}
