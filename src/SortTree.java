

public class SortTree<T extends Comparable> {

	public MyTreeNode<T> rootNode;
	
	public MyTreeNode<T> searchNode(MyTreeNode<T> child, T t, MyTreeNode<T> p) {
		if (child == null) {
			return p;
		} else if (t.compareTo(child.t) == 0) {
			return p;
		} else if (t.compareTo(child.t) < 0) {
			return searchNode(child.leftTreeNode, t, child);
		} else {
			return searchNode(child.rightTreeNode, t, child);
		}
	}
	
	
	
	public void add(T t) {
		if (rootNode == null) {
			rootNode = new MyTreeNode<T>(t);
		} else if (rootNode.t.compareTo(t) > 0){
			MyTreeNode<T> temp = searchNode(rootNode.leftTreeNode, t, rootNode);
			temp.leftTreeNode = new MyTreeNode<T>(t);;
		} else if (rootNode.t.compareTo(t) < 0) {
			MyTreeNode<T> temp = searchNode(rootNode.rightTreeNode, t, rootNode);
			temp.rightTreeNode = new MyTreeNode<T>(t);
		}
	}
	
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
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numbers[] = {12, 5, 6, 22, 7, 9, 11, 18, 68, 54};
		SortTree<Integer> sortTree = new SortTree<Integer>();
		for (int i = 0; i < numbers.length; i++) {
			sortTree.add(numbers[i]);
		}
		
		sortTree.mid(sortTree.rootNode);
	}

}
