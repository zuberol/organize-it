import java.util.*;
import java.io.*;

class FillArray{
public static void main(String [] args){
   int [] a = new int [10];
   for(int i : a){
      i=1;        //wont work!
   }
   System.out.print(a[0]);
}
}
