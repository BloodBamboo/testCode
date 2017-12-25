import java.util.Arrays;

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



    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return pre(root, 0);
    }

    public static int pre(TreeNode root, int l) {
        if (root == null) {
            return l;
        }
        l++;
        if (root.left == null && root.right == null) {
            return l;
        }
        int left = pre(root.left, l);
        int right = pre(root.right, l);

        if (root.left != null && root.right != null) {
            return left < right ? left : right;
        } else if (root.left != null) {
            return left;
        } else {
            return right;
        }
    }

    /**
     * @param args
     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//        root.left.left= new TreeNode(4);
//        root.left.right= new TreeNode(5);
        print(minDepth(root));
    }





    public static void print(Object o) {
        System.out.print(o.toString());
    }

    public static void println(Object o) {
        System.out.println(o.toString());
    }
}
