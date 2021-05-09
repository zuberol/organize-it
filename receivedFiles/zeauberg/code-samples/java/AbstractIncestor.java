class AbstractIncestor {
	public static void main(String [] args) {
		Incestor i = new Incestor();
		Ancestor a = i;
	}
}


class Incestor extends Ancestor {
	
	@Override
	void someMethod(){
		
	}

}

abstract class Ancestor {

	Long id;

	abstract void someMethod();
	void nonAbstract(){

	}

}

