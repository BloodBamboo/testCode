

public class MyTree<T> {
	private int i = 0;
	private T[] array;
	public MyTreeNode<T> rootNode;
	
	/**
	 * 通过null虚节点构建树结构,c++可以通过地址进行相应的引用指向.java由于传递的是null地址,所以不能达到地址引用的效果.所以该方法在java中无效
	 * @param node
	 * @param t
	 */
//	public void add(MyTreeNode<String> node) {
//		Object object = o[i];
//		i++;
//		if (object == null) {
//			node = null;
//		} else {
//			if (node == null) {
//				node = new MyTreeNode<String>(object.toString());
//			}
//			add(node.leftTreeNode);
//			add(node.rightTreeNode);
//		}
//	}
	
	public MyTree() {
	}
	
	public MyTree(T[] array) {
		this.array = array;
		rootNode = new MyTreeNode(array[i]);
		i++;
		if (i < array.length) {
			add(rootNode.leftTreeNode, array[i], rootNode, true);
		}
		if (i < array.length) {
			add(rootNode.rightTreeNode, array[i], rootNode, false);
		}
		i = 0;
	}
	
	/**
	 * 通过null虚节点构建树结构,java版,改进c++指针方式
	 * @param node
	 * @param t
	 * @param pNode
	 * @param isLeft
	 */
	public void add(MyTreeNode<T> node, T t, MyTreeNode<T> pNode, boolean isLeft) {
		i++;
		if (t == null) {
			node = null;
		} else {
			if (node == null) {
				node = new MyTreeNode<T>(t);
				if (isLeft) {
					pNode.leftTreeNode = node;
				} else {
					pNode.rightTreeNode = node;
				}
			}
			
			if (i < array.length) {
				add(node.leftTreeNode, array[i], node, true);
			}
			if (i < array.length) {
				add(node.rightTreeNode, array[i], node, false);
			}
		}
	}
	
	/**
	 * 通过null虚节点构建树结构,java版,节点带父节点指针
	 * @param node
	 */
//	public TwoTree(T[] array) {
//		this.array = array;
//		root = new TreeNode(array[i], null);
//		i++;
//		if (i < array.length) {
//			add(root.left, array[i], root, true);
//		}
//		if (i < array.length) {
//			add(root.right, array[i], root, false);
//		}
//		i = 0;
//	}
//	
//	public void add(TreeNode<T> node, T t, TreeNode<T> pNode, boolean isLeft) {
//		i++;
//		if (t == null) {
//			node = null;
//		} else {
//			if (node == null) {
//				node = new TreeNode<T>(t, pNode);
//				if (isLeft) {
//					pNode.left = node;
//				} else {
//					pNode.right = node;
//				}
//			}
//			
//			if (i < array.length) {
//				add(node.left, array[i], node, true);
//			}
//			if (i < array.length) {
//				add(node.right, array[i], node, false);
//			}
//		}
//	}
	
	public void pre(MyTreeNode<T> node) {
		if (node == null) {
			return;
		}
		System.out.print(" " + node.t);
		pre(node.leftTreeNode);
		pre(node.rightTreeNode);
	}
	
	
	public void mid(MyTreeNode<T> node) {
		if (node == null) {
			return;
		}
		mid(node.leftTreeNode);
		System.out.print(" " + node.t);
		mid(node.rightTreeNode);
	}
	
	public void last(MyTreeNode<T> node) {
		if (node == null) {
			return;
		}
		last(node.leftTreeNode);
		last(node.rightTreeNode);
		System.out.print(" " + node.t);
	}
	
	public MyTreeNode<T> searchNode(MyTreeNode<T> node, T t) {
		if (node == null || t.equals(node.t)) {
			return node;
		}
		
		MyTreeNode<T> lefTreeNode  = searchNode(node.leftTreeNode, t);
		if (lefTreeNode != null) {
			return lefTreeNode;
		} else {
			return searchNode(node.rightTreeNode, t);
		}
	}
	
	
	/**
	 *             a
	 *       b           c
	 *   d      e    f       g
	 * h    i
	 * @param args
	 */
	public static void main(String[] args) {
//		MyTree<String> tree = new MyTree<String>();
//		tree.rootNode = new MyTreeNode<String>("a");
//		tree.rootNode.leftTreeNode = new MyTreeNode<String>("b");
//		tree.rootNode.rightTreeNode = new MyTreeNode<String>("c"); 
//		tree.rootNode.leftTreeNode.leftTreeNode = new MyTreeNode<String>("d");
//		tree.rootNode.leftTreeNode.rightTreeNode = new MyTreeNode<String>("e"); 
//		tree.rootNode.rightTreeNode.leftTreeNode = new MyTreeNode<String>("f");
//		tree.rootNode.rightTreeNode.rightTreeNode = new MyTreeNode<String>("g");
//		tree.rootNode.leftTreeNode.leftTreeNode.leftTreeNode = new MyTreeNode<String>("h");
//		tree.rootNode.leftTreeNode.leftTreeNode.rightTreeNode = new MyTreeNode<String>("i");
//		
//		System.out.println("pre---------------");
//		tree.pre(tree.rootNode);
//		System.out.println("\n---------------");
//		System.out.println("mid---------------");
//		tree.mid(tree.rootNode);
//		System.out.println("\n---------------");
//		System.out.println("last---------------");
//		tree.last(tree.rootNode);
//		System.out.println("\n---------------");
//		
//		
//		System.out.println(tree.searchNode(tree.rootNode, "b").t);
//		System.out.println(tree.searchNode(tree.rootNode, "f").t);
//		System.out.println(tree.searchNode(tree.rootNode, "h").t);
//		System.out.println(tree.searchNode(tree.rootNode, "a").t);
		
		
		String []array = {"a","b",null,"d", null, null, "c", null,null};
		MyTree<String> tree1 = new MyTree<String>(array);
		tree1.pre(tree1.rootNode);
	}

}
