class BinSearch {

static int binarySearch1(int[] nums, int target){
  if(nums == null || nums.length == 0)
    return -1;

  int left = 0, right = nums.length - 1;
  while(left <= right){
    // Prevent (left + right) overflow
    int mid = left + (right - left) / 2; 
	System.out.println(String.format("%d %d", mid, mid+1));
	if(nums[mid] == target){ return mid; }
    else if(nums[mid] < target) { left = mid + 1; }
    else { right = mid - 1; }
  }

  // End Condition: left > right
  return -1;
}


static int binarySearch2(int[] nums, int target){
  if(nums == null || nums.length == 0)
    return -1;

  int left = 0, right = nums.length;
  while(left < right){
    // Prevent (left + right) overflow
    int mid = left + (right - left) / 2;
	System.out.println(String.format("%d %d", mid, mid+1));
    if(nums[mid] == target){ return mid; }
    else if(nums[mid] < target) { left = mid + 1; }
    else { right = mid; }
  }

  // Post-processing:
  // End Condition: left == right
  if(left != nums.length && nums[left] == target) return left;
  return -1;
}



public static void main(String [] args) {
	int [] data = {0,1,2,3,4,5,6,7,8,9};
	int target = 9;
	System.out.println("1");
	binarySearch1(data, target);
	System.out.println("2");
	binarySearch2(data, target);

}


}
