
public class MyTree<T> {

	public TreeNode rootNode;
	
	public static class TreeNode<T> {
		public T t;
		public TreeNode leftTreeNode;
		public TreeNode rightTreeNode;
		
		public TreeNode(T t) {
			this.t = t;
		}
	}
	
	
	
	public void pre(TreeNode node) {
		if (node == null) {
			return;
		}
		System.out.print(" " + node.t);
		pre(node.leftTreeNode);
		pre(node.rightTreeNode);
	}
	
	
	public void mid(TreeNode node) {
		if (node == null) {
			return;
		}
		mid(node.leftTreeNode);
		System.out.print(" " + node.t);
		mid(node.rightTreeNode);
	}
	
	public void last(TreeNode node) {
		if (node == null) {
			return;
		}
		last(node.leftTreeNode);
		last(node.rightTreeNode);
		System.out.print(" " + node.t);
	}
	
	
	/**
	 *             a
	 *       b           c
	 *   d      e    f       g
	 * h    i
	 * @param args
	 */
	public static void main(String[] args) {
		MyTree<String> tree = new MyTree<String>();
		tree.rootNode = new TreeNode<String>("a");
		tree.rootNode.leftTreeNode = new TreeNode<String>("b");
		tree.rootNode.rightTreeNode = new TreeNode<String>("c"); 
		tree.rootNode.leftTreeNode.leftTreeNode = new TreeNode<String>("d");
		tree.rootNode.leftTreeNode.rightTreeNode = new TreeNode<String>("e"); 
		tree.rootNode.rightTreeNode.leftTreeNode = new TreeNode<String>("f");
		tree.rootNode.rightTreeNode.rightTreeNode = new TreeNode<String>("g");
		tree.rootNode.leftTreeNode.leftTreeNode.leftTreeNode = new TreeNode<String>("h");
		tree.rootNode.leftTreeNode.leftTreeNode.rightTreeNode = new TreeNode<String>("i");
		
		System.out.println("pre---------------");
		tree.pre(tree.rootNode);
		System.out.println("\n---------------");
		System.out.println("mid---------------");
		tree.mid(tree.rootNode);
		System.out.println("\n---------------");
		System.out.println("last---------------");
		tree.last(tree.rootNode);
		System.out.println("\n---------------");
	}

}
