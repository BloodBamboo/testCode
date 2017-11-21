

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
	
	
	public TwoTuple<MyTreeNode<T>, MyTreeNode<T>> searchNode4While(T t) {
		if (rootNode == null) {
			return null;
		}
		MyTreeNode<T> node = rootNode;
		MyTreeNode<T> pNode = null;
		while (node != null) {
			pNode = node;
			if (node.t.compareTo(t) == 0) {
				return new TwoTuple<MyTreeNode<T>, MyTreeNode<T>>(node, pNode);
			} else if (node.t.compareTo(t) > 0) {
				node = node.leftTreeNode;
			} else {
				node = node.rightTreeNode;
			}
		}
		
		return new TwoTuple<MyTreeNode<T>, MyTreeNode<T>>(null, pNode);
	}
	
	public void add4While(T t) {
		TwoTuple<MyTreeNode<T>, MyTreeNode<T>> temp = searchNode4While(t);
		if (temp == null) {
			rootNode = new MyTreeNode<T>(t);
		} else if (temp.second.t.compareTo(t) > 0){
			temp.second.leftTreeNode = new MyTreeNode<T>(t);
		} else if (temp.second.t.compareTo(t) < 0) {
			temp.second.rightTreeNode = new MyTreeNode<T>(t);
		}
	}
	
	
	public void add(T t) {
		MyTreeNode<T> temp = searchNode(rootNode, t, null);
		if (temp == null) {
			rootNode = new MyTreeNode<T>(t);
		} else if (temp.t.compareTo(t) > 0){
			temp.leftTreeNode = new MyTreeNode<T>(t);
		} else if (temp.t.compareTo(t) < 0) {
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
		
		SortTree<Integer> sortTree1 = new SortTree<Integer>();
		for (int i = 0; i < numbers.length; i++) {
			sortTree1.add4While(numbers[i]);
		}
		
		System.out.println("pre---------------");
		sortTree.pre(sortTree.rootNode);
		System.out.println("\n4whilepre---------------");
		sortTree1.pre(sortTree1.rootNode);
		System.out.println("\nmid---------------");
		sortTree.mid(sortTree.rootNode);
		System.out.println("\n4whilmid---------------");
		sortTree1.mid(sortTree1.rootNode);
		System.out.println("\nlast---------------");
		sortTree.last(sortTree.rootNode);
		System.out.println("\n4whillast---------------");
		sortTree1.last(sortTree1.rootNode);
	}
	
	
	

}
