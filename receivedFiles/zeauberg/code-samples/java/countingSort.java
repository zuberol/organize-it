import java.util.*;

class countingSort{

static int [] sort(int [] data, int minBoundry, int maxBoundry){
   int [] sortedData = new int [data.length];
   int [] freq = new int [maxBoundry-minBoundry+1];

   //get frequency from data table
   for(int i : data){
      freq[i-minBoundry] = ++freq[i-minBoundry];
   }
   //add neighbour freq
   for(int i=1; i<freq.length; ++i){
      freq[i] += freq[i-1];
   }
   //insert values and update freq table
   for(int i : data){
      sortedData[ freq[i-minBoundry] - 1 ] = i;
      freq[i-minBoundry] -= 1;
   }
   return sortedData;
}

public static void main (String [] args){
   int [] data = {5, 4, 2, 9, 3, 2, 2, 8, 7, 6, 5, 3, 2, 1, 3, 2, 5, 2, 8, 5, 4};
   
   long start = System.nanoTime();
   countingSort.sort(data,0,20);
   long time = System.nanoTime() - start;
   System.out.println(time);
   
   for(int i : data) System.out.print(String.format("%d ",i));
   System.out.println("");
}
}
