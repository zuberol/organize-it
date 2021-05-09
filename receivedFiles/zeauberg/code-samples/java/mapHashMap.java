import java.util.*;
class mapHashMap{
public static void main(String [] args){
   //wont compile, there is no such method in Map interface
   Map<Integer,Integer> mpBroken = new HashMap<>();
   //mpBroken.clone();

   HashMap<Integer,Integer> mp = new HashMap<>();
   mp.clone();  



}
}
