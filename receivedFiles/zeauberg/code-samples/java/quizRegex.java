import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.regex.*;

class quizRegex {
public static void printData(HashMap<String,String> data){
   for(Map.Entry<String,String> i : data.entrySet()){
      System.out.println(String.format("Que: %s \nAns: %s",i.getKey(),i.getValue()));
   }
}
public static void startQuiz(HashMap<String,String> data){
   for(Map.Entry<String,String> i : data.entrySet()){
      System.out.print("\033[H\033[2J");
      System.out.println(String.format("Question: %s",i.getKey()));
      try{
         System.in.read();
         System.out.print("\033[H\033[2J");
      } catch (Exception e){
         System.out.println(e); 
      }
      System.out.println(String.format("Question: %s",i.getKey()));
      System.out.println(String.format("Answer: %s",i.getValue()));
      try{
         System.in.read();
      } catch (Exception e){
         System.out.println(e); 
      }

   }
}

public static void main(String [] args) throws Exception{
   if(args.length!=0){
      
      HashMap<String,String> data = new HashMap<>();
      String path = args[0];
      Scanner sc = new Scanner(new File(path));
      sc.useDelimiter(Pattern.compile("\n"));
      Pattern ans = Pattern.compile("^[^\\?]*$", Pattern.MULTILINE);
      Pattern que = Pattern.compile("^.*\\?$", Pattern.MULTILINE);
      
      String question = "";
      String oldAns = "";
      while(sc.hasNext()){
         if(sc.hasNext(que)){
            question = sc.next(que);
            data.put(question,"");
         }   
         else if (sc.hasNext(ans)){
            oldAns=data.get(question);
            data.replace(question,oldAns + "\n" +sc.next(ans).trim()); 
         }
         else System.out.print("Zły regexp. Pominieto: "+sc.next());
      }
      startQuiz(data);
   }
}
}
