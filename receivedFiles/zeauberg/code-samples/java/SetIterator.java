import java.util.*;

class SetIterator{

   public static void main (String [] args){
      HashSet<Integer> data = new HashSet<>();
      data.add(1);
      data.add(2);
      data.add(3);

      for(int i : data) System.out.print(i);
   }
}
