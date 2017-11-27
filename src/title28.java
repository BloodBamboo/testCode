
public class title28 {

	/**
	 * 数组03 - 从有序数组中删除重复元素 - 简单 - 26

给定一个有序数组，原地删除重复元素使得数组中的元素只保留一个，并且返回新长度。
禁止申请额外空间，确保空间复杂度为O(1)。

比如：
给定nums = [1, 1, 2]，

你的函数应该返回length = 2，nums = [1, 2]。
不用考虑超出新长度之后的空间遗留。
	 */
	
	
	 public static int removeDuplicates(int[] nums) {
        int start = 0;
        int end = 0;
        while (end < nums.length) {
        	if (nums[start] == nums[end]) {
        		end++;
        	} else {
        		start++;
        		nums[start] = nums[end];
			}
		}
        
        return start + 1;
    }
	
	 /**
	  * 无序数组去重复算法
	  */
	 
//	 public int removeDuplicates(int[] nums) {
//	        int start = 0;
//			int end = nums.length;
//			while (start < end) {
//				int val = nums[start];
//				int i = start + 1;
//				while (i < end) {
//					if (nums[i] == val) {
//						int index = i;
//						while (index < end) {
//							if (index + 1 < end) {
//								int temp = nums[index + 1];
//								nums[index + 1] = nums[index];
//								nums[index] = temp;
//							}
//							index++;
//						}
//						end--;
//					} else {
//						i++;
//					}
//				}
//				start++;
//			}
//
//
//			return start;
//	    }
	 
	 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] p = {1, 1, 2, 3, 3, 3, 4, 6, 6,7, 9};
		System.out.println(removeDuplicates(p));
		
		for (int i : p) {
			System.out.print(i);
		}
		
	}

}
