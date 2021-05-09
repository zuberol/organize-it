class usingContinue{
public static void main(String [] args){
   outer : for (int i=0; i<10; ++i){
      inner : for(int j=0; j<100; ++j){
         System.out.println("j = " + Integer.toString(j));
         if(j==i) continue outer;
      }
   }

   System.out.println("wat1");
   System.out.println("wat2");
   System.out.println("wat3");
   System.out.println("wat4");


}
}
