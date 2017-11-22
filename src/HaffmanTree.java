import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


public class HaffmanTree<T> {
	
	public static class TreeNode<T> implements Comparable<TreeNode<T>> {

		public T t;
		public int weight;
		public TreeNode<T> left;
		public TreeNode<T> right;
		public TreeNode<T> parent;
		
		public TreeNode(T t, int weight) {
			this.t = t;
			this.weight = weight;
		}
		
		public int compareTo(TreeNode<T> node) {
			if (node == null) {
				return 1;
			}
			
			if (weight > node.weight) {
				return 1;
			} else if (weight < node.weight) {
				return -1;
			}
			
			return 0;
		}
	}
	
	public TreeNode<T> root;
	public T p;
	public HaffmanTree(T p) {
		this.p = p;
	}
	
	public TreeNode<T> createHaffManTree(List<TreeNode<T>> list) {
		if (list == null) {
			return null;
		}
		while (list.size() > 1) {
			sort4Min(list);
			TreeNode<T> left = list.remove(0);
			TreeNode<T> right = list.remove(0);
			TreeNode<T> parent = new TreeNode<T>(p, left.weight + right.weight);
			parent.left = left;
			parent.right = right;
			left.parent = parent;
			right.parent = parent;
			list.add(parent);
		}
		root = list.get(0);
		return list.get(0);
	}
	
	private <T extends Comparable<? super T>> void sort4Min(List<T> list) {
		for (int i = 0, count = list.size(); i < count; i++) {
			for (int j = i; j < count; j++) {
				Comparable t1 = list.get(i);
				Comparable t2 = (j + 1) < count ? list.get(j + 1) : null;
				boolean isBig = (j + 1) < count ? t1.compareTo(t2) > 0 : false;
				if (isBig) {
					T t = list.get(j + 1);
					list.set(j + 1, list.get(i));
					list.set(i, t);
				}
			}
		}
	}
	
	
	public void showHaffman() {
		LinkedList<TreeNode<T>> list = new LinkedList();
		list.offer(root);
		int i = 0;
		while(!list.isEmpty()) {
			TreeNode<T> node = list.pop();
			TreeNode<T> p = node.parent;
			int j = 0;
			while(p != null) {
				p = p.parent;
				j++;
			}
			if (i != j) {
				System.out.println();
				i = j;
			}
			System.out.print(node.t + " ");
			
			if (node.left != null) {
				list.offer(node.left);
			}
			if (node.right != null) {
				list.offer(node.right);
			}
		}
		
		
	}
	
	public String getCode(TreeNode<T> node) {
		if (node == null) {
			return null;
		}
		Stack<String> stack = new Stack();
		TreeNode<T> tNode = node;
		while(tNode != null && tNode.parent != null) {
			// left 0, right 1
			if (tNode.parent.left == tNode) {
				stack.add("0");
			} else if (tNode.parent.right == tNode) {
				stack.add("1");
			}
			tNode = tNode.parent;
		}
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		HaffmanTree<String> tree = new HaffmanTree<String>("p");
//		Integer []array = {22,35,99,1,2,6,43,8,6,26, 11, 22};
//		List<Integer> list1 = new ArrayList<Integer>(array.length);
//		Collections.addAll(list1, array);
//		tree.sort4Min(list1);
//		System.out.println(list1 + "  " + list1.size());
		
		
		List<TreeNode<String>> list = new ArrayList<TreeNode<String>>();
		TreeNode<String> node = new TreeNode("good", 54);
		list.add(node);
		list.add(new TreeNode("morning", 10));
		list.add(new TreeNode("afternoon", 20));
		list.add(new TreeNode("hello", 100));
		list.add(new TreeNode("hi", 200));
		
		tree.createHaffManTree(list);
		tree.showHaffman();
		System.out.println();
		System.out.println(tree.getCode(node));
	}
}
