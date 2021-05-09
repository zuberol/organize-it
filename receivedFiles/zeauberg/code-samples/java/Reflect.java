import java.lang.reflect.*;

class Reflect {
	public static void main(String [] args) {

	    Man man = new Man();
		System.out.println(man.getClass().getName());
		Person p = man;
		System.out.println(p.getClass().getName());


	}
}

class Man extends Person {

}
