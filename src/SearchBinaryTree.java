public class SearchBinaryTree<T extends Comparable> {
	
	public Node<T> root; 
	
	public class Node<T> {
		public Node<T> parent;
		public Node<T> left;
		public Node<T> right;
		public T t;
		
		public Node(T t) {
			this.t = t;
		}
		
		public Node(T t, Node parent) {
			this.t = t;
			this.parent = parent;
		}
	}

	public Node<T> searchNode(T t){
		if (root == null) {
			return root;
		}
		Node<T> node = root; 
		while(node != null) {
			if (node.t.compareTo(t) == 0) {
				return node;
			} else if (node.t.compareTo(t) < 0) {
				node = node.right;
			} else {
				node = node.left;
			}
		}
		
		return null;
	}
	
	
	public void add(T t) {
		if (root == null) {
			root = new Node(t);
			return;
		}
		Node<T> node = root;
		Node<T> parent = null;
		while(node != null) {
			parent = node;
			if (node.t.compareTo(t) == 0) {
				node.t = t;
				return;
			} else if (node.t.compareTo(t) < 0) {
				node = node.right;
			} else {
				node = node.left;
			}
		}
		
		if (parent.t.compareTo(t) > 0) {
			parent.left = new Node(t, parent);
		} else {
			parent.right = new Node(t, parent);
		}
	}
	
	public void remove(Node<T> node) {
		if (node == null) {
			return;
		}
		Node<T> parent = node.parent;
		
		//1.没有孩子
		if (node.left == null && node.right == null) {
			if (parent == null) {
				root = null;
			}
			if (parent.left == node) {
				parent.left = null;
			} else {
				parent.right = null;
			}
			node.parent = null;
		} else if (node.left != null && node.right == null) {//2.只有左孩子
			if (parent == null) {
				root = node.left;
			} else if (parent.left == node) {
				parent.left = node.left;
			} else {
				parent.right = node.left;
			}
			node.left.parent = parent;
			node.left = null;
			node.parent = null;
		} else if (node.left == null && node.right != null) {//3.只有右孩子
			if (parent == null) {
				root = node.right;
			} else if (parent.left == node) {
				parent.left = node.right;
			} else {
				parent.right = node.left;
			}
			node.right.parent = parent;
			node.right = null;
			node.parent = null;
		} else {//4.左右孩子都有
			//1：删除节点的右子树的左子树是否为空，如果为空，则把要删除节点的左子树设为删除点的右子树的左子树
			if (node.right.left == null) {
				node.right.left = node.left;
				node.left.parent = node.right;
				if (parent == null) {
					root = node.right;
				} else if (parent.left == node) {
					parent.left = node.right;
				} else {
					parent.right = node.left;
				}
				node.right.parent = parent;
				node.left = null;
				node.right = null;
				node.parent = null;
			} else {
				// 最左子树
				Node<T> leftNode = getMinLeftTreeNode(node.right);
				//1
				leftNode.left = node.left;
				node.left.parent = leftNode;
				//2
				Node<T> lp = leftNode.parent;
				lp.left = leftNode.right;
				if (leftNode.right != null) {
					leftNode.right.parent = lp;
				}
				//3
				leftNode.right = node.right;
				node.right.parent = leftNode;
				//4
				if (parent == null) {
					root = leftNode;
				} else if (parent.left == node) {
					parent.left = leftNode;
				} else {
					parent.right = leftNode;
				}
				
				leftNode.parent = parent;
				
				node.left = null;
				node.right = null;
				node.parent = null;
			}
		}
	}
	
	// 最左子树
	public Node<T> getMinLeftTreeNode(Node<T> node) {
		Node<T> pNode = null;
		while(node != null) {
			pNode = node;
			node = node.left;
		}
		return pNode;
	}
	
	public void mid(Node<T> node) {
		if (node == null) {
			return;
		}
		
		mid(node.left);
		System.out.print(node.t + " ");
		mid(node.right);
	}
	
	public void pre(Node<T> node) {
		if (node == null) {
			return;
		}
		System.out.print(node.t + " ");
		pre(node.left);
		pre(node.right);
	}
	
	
	public static void main(String[] args) {
		int[] arrays = {12, 3 ,23, 5 , 4, 8, 1, 11, 19};
		
		SearchBinaryTree tree = new SearchBinaryTree();
		
		for(int i = 0; i < arrays.length; i++) {
			tree.add(arrays[i]);
		}
		
		tree.mid(tree.root);
		System.out.println("-------------------------");
		tree.remove(tree.searchNode(3));
		tree.pre(tree.root);
		System.out.println("-------------------------");
	}
}
