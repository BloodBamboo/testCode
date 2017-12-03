
public class tt {


	public static int removeDuplicates(int[] nums) {
		int start = 0;
		int end = 0;
		int i = 0;
		while (end < nums.length) {
			if (nums[start] == nums[end]) {
				i++;
				end++;
				if (i == 2) {
					start++;
					nums[start] = nums[start - 1];
				}
			} else {
				i = 0;
				start++;
				nums[start] = nums[end];
			}
		}

		return start + 1;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int []p = {1, 1, 1, 2, 2,2,2,2,2,2, 3,4,4, 5};

		System.out.println(removeDuplicates(p));
		for(int i : p) {
			System.out.print(i);
		}
	}
}
