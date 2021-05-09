class ThrowingExceptions2 {
	public static void throwingEx() throws ClassNotFoundException {
		try{
			throw new ClassNotFoundException("some debug message");
		} catch(NullPointerException ex) {
			//empty
		}
		finally {
			System.out.println("1 Will it be executed?");
		}

	}
	public static void nestedException() {
		try {
			throwingEx();
		}
		finally {
			System.out.println("2 Will it print?");
		}
	}


	public static void main(String [] args) {
		try{	
			nestedException();	
		 } catch(Exception e) {
			System.out.println("Exception catched -> " + e.getMessage());
		 }

	}
}
