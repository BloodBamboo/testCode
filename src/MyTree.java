
public class MyTree<T> {

	public MyTreeNode rootNode;
	
	
	public void pre(MyTreeNode node) {
		if (node == null) {
			return;
		}
		System.out.print(" " + node.t);
		pre(node.leftTreeNode);
		pre(node.rightTreeNode);
	}
	
	
	public void mid(MyTreeNode node) {
		if (node == null) {
			return;
		}
		mid(node.leftTreeNode);
		System.out.print(" " + node.t);
		mid(node.rightTreeNode);
	}
	
	public void last(MyTreeNode node) {
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
		tree.rootNode = new MyTreeNode<String>("a");
		tree.rootNode.leftTreeNode = new MyTreeNode<String>("b");
		tree.rootNode.rightTreeNode = new MyTreeNode<String>("c"); 
		tree.rootNode.leftTreeNode.leftTreeNode = new MyTreeNode<String>("d");
		tree.rootNode.leftTreeNode.rightTreeNode = new MyTreeNode<String>("e"); 
		tree.rootNode.rightTreeNode.leftTreeNode = new MyTreeNode<String>("f");
		tree.rootNode.rightTreeNode.rightTreeNode = new MyTreeNode<String>("g");
		tree.rootNode.leftTreeNode.leftTreeNode.leftTreeNode = new MyTreeNode<String>("h");
		tree.rootNode.leftTreeNode.leftTreeNode.rightTreeNode = new MyTreeNode<String>("i");
		
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
