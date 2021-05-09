import java.util.*;

class Swap{
   class Man{
      String name;
      Man(String name){
         this.name = name;
      }
   }

   static void swap(int a, int b){
      int handler = a;
      a = b;
      b = handler;

      a=100;
      b=21322;
   
   }
   void swap2(Man a, Man b){
      Man handler = a;
      a = b;
      b = handler;
   }

   public static void main(String [] args){
      int [] ar = {3, 1};
//      swap(ar[0],ar[1]);
//
//      //try to swap two man's
//      Man john = new Swap.Man("John");
//      Man wania = new Swap.Man("Wania");
//      Swap s = new Swap();
//      s.swap2(john,wania);
//      System.out.println(String.format("John is %s and Wania is %s." ,john.name, wania.name));
   }
}
