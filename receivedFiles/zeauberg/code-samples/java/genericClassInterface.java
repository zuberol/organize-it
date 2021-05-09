import java.util.*;



interface canClassExtendInterface{}

interface oneGeneric<T> {}
interface oneGenericSpecificType<I> {}

interface twoGeneric<T, V> {}

interface MinMax<T extends Comparable<T>> {
	T min(T a);
	T max(T b);

}

interface extendedInterface {}
interface canInterfaceExtendInterface extends extendedInterface {}

class genericClassInterface<T extends Comparable<T>> implements MinMax<T> {
		public T max(T b) { return b;} 
		public T min(T a) { return a;}
}

/*	Compilation error. Nie potrzebnie zdefiniowany typ w genericu interfaceu, juz byl wczesniej okreslony w definicji
class genericClassInterface<T extends Comparable<T>> implements MinMax<T extends <Comparable<T>>> {
		public T max(T b) { return b;} 
		public T min(T a) { return a;}
}
*/

class genericKnown implements MinMax<Integer> {
	public Integer min(Integer i) { return i;}
	public Integer max(Integer i) { return i;}

}


interface genericExa2<T extends Comparable<T>> {}
class genericExa2Impl {}


//interface genericExa3<? extends Comparable<T>> {}		//wont compile,  initializers not allowed in interfaces
//class genericExa3Impl {}



class someType {}
interface implementingGenericPrerequisite<T extends Comparable<T>>{
	T doSth(T t);
}
//class MyClass implements implementingGenericPrerequisite {
	
//}




// class implComparable implements Comparable<T>{}

// GENERIC CONSTRUCTOR
class genConstructor1 {
	// generic constructor
	<T extends Comparable> genConstructor1(T p){
		System.out.println(p); // public void println(Object x)	
	}

}

/*
Will it compile?
class genConstructor2 {
	T prop;
	// generic constructor
	<T extends Comparable> genConstructor(T param){
		this.prop = param;
	}

}
Nope.
*/



















