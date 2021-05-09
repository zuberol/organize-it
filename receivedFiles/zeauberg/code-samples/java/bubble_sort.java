import java.util.*;

class bubble_sort{

   void sort(int [] data){
      

      
      int len = data.length;
      boolean is_sorted = false;
      int swap;
      Integer ite = 1;
      while(!is_sorted){
         for(int i=0; i<len-1; ++i){
            if(data[i]<=data[i+1]){
               continue;
            }
            else{
               swap = data[i];
               data[i] = data[i+1];
               data[i+1] = swap;
            }
         
         }
         
         for(int i=0; i<len-1; ++i){
           is_sorted = data[i]<=data[i+1] ? true : false;
         }
         
         System.out.println("Iteration "+ite.toString());
         ite +=1;
      };
   }
   void print(int [] data){

      for (int i=0; i<data.length; ++i){
         System.out.println(data[i]);
      }
   }

public static void main(String [] args){

   int [] data = {1,100,2,3,21,3,5,7,4,10};
   
   bubble_sort b = new bubble_sort();
   b.sort(data);
   b.print(data);




}
}

