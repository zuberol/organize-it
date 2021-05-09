class Sort{
    static boolean isSorted(int [] arr){
        for(int i=0; i+1<arr.length; ++i){
            if(arr[i] > arr[i+1]) return false;
        }
        return true;
    }
public static void main(String [] args){
	int [] arr = {1,2,3,4};

	System.out.println(isSorted(arr));
}
}
