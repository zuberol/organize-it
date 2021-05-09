import java.util.*;

class SB {

public static void main (String [] args){
   String s = "abcdefg";
   String sc;
   
   for(int i=0; i<s.length(); ++i){
      System.out.println(new StringBuilder(s).deleteCharAt(i).toString());
   }
   
}
}
