import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

public class CanIntHaveJustDefault {


    interface CreateLambdaDefault <E>  {
        default <E> void print(E e) {
            System.out.println(e);
        }
    }

    interface CreateLambda <E> extends Consumer  {
        <E> void print(E e);
    }

    public static void main(String[] args) {

        String [] printMe = { "hello", "hello", null, "hello"};

        Function<String, String> lambda = a -> a;
        Consumer<String> consumer = System.out::println;
        //Arrays.stream(printMe).forEach(lambda);
        Arrays.stream(printMe).forEach(consumer);

    }

}
