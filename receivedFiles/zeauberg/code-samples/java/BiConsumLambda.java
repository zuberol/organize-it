import java.util.*;

class BiConsumLambda{
    static void printMap(HashMap<Integer,Integer> map){
        //forEach(BiConsumer<? super K,? super V> action)
        map.forEach((Integer k, Integer v) -> System.out.println(String.format("Key: %d Val: %d",k,v)));
    }

public static void main(String [] args){
	HashMap<Integer,Integer> mapa = new HashMap<>();
	mapa.put(1,1);
	mapa.put(2,2);
	mapa.put(3,3);

	printMap(mapa);
}
}
