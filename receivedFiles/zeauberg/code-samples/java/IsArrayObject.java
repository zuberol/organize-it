import java.util.*;

class IsArrayObject{
   static public void main(String [] args){
      //int [] a_ref = new int [3];
      //ok int [] a_ref = new int [3]{0, 1, 2};
      int [] a_ref = {1, 2, 3};
      System.out.println(a_ref instanceof Object);
      //System.out.println({1,3,2,5} instanceof Object);
   }
}
