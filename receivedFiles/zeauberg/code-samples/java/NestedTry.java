class NestedTry {
	public static void main(String [] args) {
		
		int i = 0;
		try{
			try {
				 //error
			} catch(ArrayIndexOutOfBoundsException e) {
				i = i/0;
			}
		} catch(Exception ex) {
			System.out.println(ex);
		}
		finally{
			System.out.println("wat");
		}
	}
}
