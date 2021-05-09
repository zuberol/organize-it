import java.util.*;

class NYQue{
   static int bribe(int [] data, int bribed, int bribing, int bribes){
      if(data[bribed]>data[bribing]){
         bribes++;
         int handler = data[bribing];
         data[bribing] = data[bribed];
         data[bribed] = handler;
      }
      return bribes;
   }
   static int find(int [] data, int num){
      for(int i=0; i<data.length; ++i){
         if(data[i] == num) return i;
      }
      return num;
   }
   static boolean isChaotic(int [] data){
      for(int i=1; i<data.length; ++i){
         if(data[i-1]>data[i]) return true;
      }
      return false;
   }

public static void main(String [] args) throws Exception{
   int [] data = {10,2,1,5,3,4,6,8,7};
   
   Set<Integer> values = new HashSet<>();
   for(int i : data){
      if (!values.add(i)){
         throw new Exception("No distinc values in the data array!");   
      }
   }
   
   int bribes=0;

   for(int i : data){
      int indx = NYQue.find(data,i);
      if(indx==0) continue;
      else bribes = bribe(data,indx-1,indx,bribes);
   }
   
   for(int i: data) System.out.println(i);
   
   if(NYQue.isChaotic(data)) System.out.println("Too chaotic!");
   else System.out.println(String.format("Number of bribes = %d",bribes));

}
}
