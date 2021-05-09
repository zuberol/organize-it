import java.util.stream.Stream;

class UsedStream {

	public static void main(String [] args) {
		Stream<Integer> s = Stream.of(1,2,3,4,5);
		s.forEach(System.out::println);
		System.out.println(s.count());
	}
}
