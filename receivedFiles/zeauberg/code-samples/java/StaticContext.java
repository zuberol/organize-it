class StaticContext {
	public String classProperty;
	static void thisDoesntHaveAnyContext(){
		System.out.println("This method is taken from class definition.");
		//System.out.println("That's why you can't use this: " + classProperty); //what that would even be when you used it?
	}

}
