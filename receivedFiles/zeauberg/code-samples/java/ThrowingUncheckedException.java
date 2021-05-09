import java.io.*;

public class ThrowingUncheckedException {
    public static void foo(String text){
        if (text.length() == 0) {
            throw new UncheckedException();
        }
    }
    public static void main(String[] args) {
        foo("some debug msg");
    }
}

class UncheckedException extends RuntimeException {
	@Override
	public void printStackTrace(PrintStream s){
		System.out.println("print stack can be overriden because it is not finall method");
	}
}


