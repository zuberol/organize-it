import java.util.*;
import java.util.regex.*;

class Sherlock{

public static boolean count(String s){

   Map<Character, Integer> occurences = new HashMap<>();
   for(int i=0; i<s.length(); ++i){
      if (!occurences.containsKey(s.charAt(i))){
         occurences.put(s.charAt(i), 1);
      }
      else{
         int currentVal = occurences.get(s.charAt(i));
         occurences.replace(s.charAt(i), ++currentVal);
      }
   }
   Integer [] vals = occurences.values().toArray(new Integer[0]);
   Arrays.sort(vals);
   
   for(int i=0; i<vals.length-1; ++i){
      if(vals[i]!=vals[i+1]){
         return false;
      }
   }
   return true;
}
public static void solution(String s){
   
   String cuttedStr;

   for(int rL=0; rL<s.length(); ++rL){

      if(count(s)==true){
         System.out.println("Valid str Yes");
         break;
      }

      cuttedStr = new StringBuilder(s).deleteCharAt(rL).toString();
      if(count(cuttedStr)==true){
         System.out.println("YES");
         break;
      }
      if(rL==s.length())
         System.out.println("NO");
   }

}

public static void main (String [] args){
   Sherlock.solution("caabbccddeee");
}
}
