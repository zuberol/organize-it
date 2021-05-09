import java.util.HashMap;
	class Key {
		public String val;
		Key(String val) {
			this.val = val;
		}
	}


class HashTableKeys2 {
	public static void main (String [] args) {
		HashMap<Key, String> mp = new HashMap<>();
		Key k1 = new Key("sameKeyString");
		Key k2 = new Key("sameKeyString");
		String val = "val to store";
		mp.put(k1, val);
		System.out.println(mp.get(k1));
		System.out.println(mp.get(k2));
	}
}
