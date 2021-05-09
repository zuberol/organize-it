import java.util.*;
import java.util.function.*;

class OptionalsTraining {
	class dropSpaces<T,R> implements Function<T,R> {
		@Override
		public R apply(T t) {
			return null;
		}


	}

	static {
		System.out.println("hehe");
	}
	static {
		System.out.println("hehe");
	}

	public static void main (String [] args) {
		String s = "           Drop leading trailing spaces        ";
		Optional<String> opt = Optional.ofNullable(s);



//		Function<String, String> dropSpacesAnonymousFunction = new Function<String, String>() {
//			@Override
//			public String apply(String s) {
//				return s.trim().strip();
//			}
//			@Override
//			public String toString() {
//				return "dropSpacesAnonymousFunction";
//			}
//
//		};
//		opt.map(dropSpacesAnonymousFunction);
//
//		opt.map(new Function<String, String>() {
//			@Override
//			public String apply(String s) {
//				return s.trim().strip();
//			}
//		});


		int [] v = {1,2,3};

//		static {
//			System.out.println("hehe");
//		}



	}
}
