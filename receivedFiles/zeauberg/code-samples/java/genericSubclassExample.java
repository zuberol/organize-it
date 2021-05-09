class genericSubclassExample {

}


class genericSubclass <T> extends genericSubclassExample {
	public void print(T a){
		System.out.println(a);
	}
}

class Exa {
	public static void main(String [] args){
		genericSubclass<Integer> gen = new genericSubclass<>();
	}
}



