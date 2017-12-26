import java.util.Arrays;
import java.util.Stack;

public class tt {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    /**
     * @param args
     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left= new TreeNode(1);
        root.left.right= new TreeNode(3);
        root.right.left= new TreeNode(6);
        root.right.right= new TreeNode(9);
        
        
    }





    public static void print(Object o) {
        System.out.print(o.toString());
    }

    public static void println(Object o) {
        System.out.println(o.toString());
    }
}
