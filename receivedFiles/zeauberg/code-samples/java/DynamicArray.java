import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class DynamicArray {
    public static List<Integer> answer(int n) {
      List<Integer> lastAnswer = new ArrayList<>(n);
      ArrayList<ArrayList<Integer>> seqList = new ArrayList<ArrayList<Integer>>();
      
      for (int i=0; i<n; ++i){
         seqList.add(new ArrayList<Integer>());
         for(int j=0; j<n; ++j){
            seqList.get(i).add(0);
         }
      }
      System.out.println(seqList.get(0).size()); 
      System.out.println(seqList.get(0).get(0));
      return lastAnswer;
    }
    public static void main(String[] args) throws IOException {
      

      DynamicArray.answer(2);
      System.out.println(9^1);

    }
}

