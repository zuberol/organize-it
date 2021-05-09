import java.util.*;

class NY2{
   static void fill(int [] array){
      for(int i=0; i<array.length; ++i) array[i] = i+1;
   }

   static int canBeBribed(int indx, int [] data, int [] mix, int bribeRange){
      int k = 0;
      int maxIdx = data.length-1;
      while(k!=bribeRange){
         if(indx+k>maxIdx) break;
         if(data[indx]==mix[indx+k]) return k;
         k+=1;
      }
      return -1;
   }

   static int bribe(int [] mix, int indx, int bribing, int bribes){
      ++bribes;
      
      int last = mix[indx+bribing];
      for(int i=0; i<bribing; ++i){
         mix[indx+i+1] = mix[indx+i]; 
      }
      mix[indx] = last;
      for(int i:mix) System.out.println(i);

      return bribes;
   }

   public static void main(String [] args){
      int [] data = {1, 2, 5, 3, 7, 8, 6, 4};
      int [] mix = new int [data.length];
      fill(mix);
      
      int bribes = 0;

      //check for all nums starting from left
      for(int i=0; i<mix.length; ++i){
         int bribing = canBeBribed(i, data, mix, 22);
         if(bribing > 0) bribes = bribe(mix, i, bribing, bribes);
         else if(bribing == 0) System.out.println("dsa");
         else{
            System.out.println("Too chaotic" + bribing);
            break;
         }
      }
      for(int i: mix) System.out.print(i+" ");
      System.out.println("\n" + bribes);
   }
}
