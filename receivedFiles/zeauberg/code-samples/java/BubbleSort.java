class BubbleSort {
	
	public static void main(String [] args) {
		int [] arr = {1,5,2,3,2,75,32,32,6,8,6,45,19,90};

		for(int i=1; i<arr.length; ++i) {
			for(int j=arr.length-1; j-1>i; --j) {
				if(arr[j] < arr[j-1]) {
					int v = arr[j];
					arr[j] = arr[j-1];
					arr[j-1] = v;
				}
			}
		}

		for(int i : arr) System.out.println(i);

	}
}
