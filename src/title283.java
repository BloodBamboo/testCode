/**
 * leetCode 283题优秀答案
 * 最优秀的答案为把不是0的都按顺序放在一起，剩余全部置0
 */
public class title283 {
    public static void exchange(int[] nums, int index1, int index2) {
        if (index1 == index2) {
            return;
        }
        int temp = nums[index2];
        nums[index2] = nums[index1];
        nums[index1] = temp;
    }


    public static void moveZeroes(int[] nums) {
        int start = 0;
        int end = 0;

        while (end < nums.length) {
            if (nums[end] != 0) {//交互不是0的值与0最后一个位置
                exchange(nums, start, end);
                start++;
            }
            end++;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        int []nums = {4,2,4,0,0,3,0,5,1,0};
        moveZeroes(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
