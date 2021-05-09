import java.util.HashMap;

class HashTableKeys {

	public static void main (String [] args) {
		HashMap<String, String> mp = new HashMap<>();
		String s1 = new String("sameKeyString");
		String s2 = new String("sameKeyString");
		String val = "val to store";
		mp.put(s1, val);
		System.out.println(mp.get(s2));
	}
}
