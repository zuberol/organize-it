class TryFinally {
	
	static void parse_data() {};
	static void compute_something() throws NullPointerException {
		throw new NullPointerException("no data provided - can't compute");
	};
	static void business_logic() {};

	static void connect_db() {};
	static void disconnect_db() {};

	public static void throwingEx() throws Exception {
		try {
			connect_db();
			parse_data();
			compute_something();
			business_logic();
		}	
		finally {
			disconnect_db();
		}

	}

	public static void main(String [] args) {
		try {	
			throwingEx();	
		 } catch(Exception e) {
			System.out.println("Exception catched -> " + e.getMessage());
		 }

	}
}
