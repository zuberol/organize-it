public class VariableCapture {
    interface SomeInt {
        int func(int arg);
    }

    public static void main(String[] args) {

        int dont_change_or_it_breaks = 1337;
        SomeInt foo = a -> a+dont_change_or_it_breaks;
        System.out.println(foo.func(3));
        // ++dont_change_or_it_breaks; java: local variables referenced from a lambda expression must be final or effectively final
    }

}
