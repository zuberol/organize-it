class ThrowingExceptions {
	public static void throwingEx() throws NullPointerException, ArrayIndexOutOfBoundsException, Exception {
		throw new Exception("some debug message");
	}

	public static void main(String [] args) {
		// throwingEx();
	}
}
