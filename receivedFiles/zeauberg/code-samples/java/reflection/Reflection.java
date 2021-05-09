import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


class Reflection {



	public static void main(String [] args) {
		Object challenge = new reflection.Challenge("Send me song challenge", 1337);
		Field [] fields = challenge.getClass().getDeclaredFields();

		List<String> fieldNames = Arrays.stream(fields)
										.map(f -> f.getName())
										.collect(Collectors.toList());

		System.out.println(fieldNames);
	}
}
