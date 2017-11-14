import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

public class MyLinkList<T> {

	MyListNode<T> firstNode;
	MyListNode<T> lastNode;

	public void add(T t) {
		MyListNode<T> newNode = new MyListNode<T>(t, null);
		if (firstNode == null) {
			firstNode = newNode;
			lastNode = newNode;
		} else {
			lastNode.next = newNode;
			lastNode = newNode;
		}
	}

	public T remove(int index) {
		if (isEmpty() || index >= length()) {
			return null;
		}
		
		if (index == 0) {
			MyListNode<T> temp = firstNode;
			firstNode = firstNode.next;
			return temp.t;
		}
		MyListNode<T> parentNode = getNode(index - 1);
		MyListNode<T> currentNode = getNode(index);
		if (currentNode == lastNode) {
			lastNode = parentNode;
			parentNode.next = null;
			return currentNode.t;
		}
		parentNode.next = currentNode.next;
		return currentNode.t;
	}

	public T remove() {
		if (isEmpty()) {
			return null;
		}
		int length = length();
		MyListNode<T> temp = getNode(length - 1);
		if (length < 2) {
			firstNode = null;
			lastNode = null;
			return temp.t;
		}
		lastNode = getNode(length - 2);
		lastNode.next = null;
		return temp.t;
	}
	
	public T getValue(int index) {
		MyListNode<T> temp = getNode(index);
		if (temp != null) {
			return temp.t;
		} else {
			return null;
		}
	}
	
	private MyListNode<T> getNode(int index) {
		if (index >= length()) {
			return null;
		}
		
		if (index == 0) {
			return firstNode;
		}

		if (index == (length() - 1)) {
			return lastNode;
		}

		MyListNode<T> tempNode = firstNode.next;
		int i = 1;
		while (tempNode.next != null) {
			if (i == index) {
				return tempNode;
			}
			i++;
			tempNode = tempNode.next;
		}

		return null;
	}

	public int length() {
		if (firstNode == null) {
			return 0;
		}
		int i = 1;
		MyListNode<T> tempNode = firstNode;
		while (tempNode.next != null) {
			i++;
			tempNode = tempNode.next;
		}
		return i;
	}
	
	public boolean isEmpty() {
		if (lastNode == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public void clear() {
		MyListNode<T> current  = firstNode;
		while (current != null) {
			MyListNode<T> next = current.next;
			current.next = null;
			current.t = null;
			current = next;
		}
	}

	public void reverse2() {
		lastNode = firstNode;
		MyListNode<T> currentNode = firstNode;
		MyListNode<T> prev = null;
		while (currentNode != null) {
			MyListNode<T> temp = currentNode;
			currentNode = currentNode.next;
			temp.next = prev;
			prev = temp;
		}

		firstNode = prev;
	}

	public void reverse() {
		reverseNode(firstNode);
		lastNode.next = null;
	}

	private void reverseNode(MyListNode<T> node) {
		if (node.next != null && node.next.next != null) {
			reverseNode(node.next);
		}

		if (node.next.next == null) {
			firstNode = node.next;
			lastNode = node;
			firstNode.next = lastNode;
		} else {
			lastNode.next = node;
			lastNode = node;
		}
	}

	public void print() {
		MyListNode<T> current  = firstNode;
		while (current != null) {
			System.out.println(current.t + "");
			current = current.next;
		}
	}

	public static void main(String[] args) {
		MyLinkList<Integer> linkList = new MyLinkList<Integer>();
		for (int i = 0; i < 5; i++) {
			linkList.add(i);
		}
		int length = linkList.length();
		System.out.println(length);
		System.out.println("------------------------");
		linkList.print();
		System.out.println("------------------------");
		linkList.reverse();
		linkList.print();
		System.out.println("------------------------");
		linkList.reverse2();
		linkList.print();
		System.out.println("------------------------");
		linkList.remove(3);

//		for (int i = 0; i < linkList.length(); i++) {
//			System.out.println(linkList.getNode(i).t + "");
//		}
		
		MyListNode<Integer> current  = linkList.firstNode;
		while (current != null) {
			System.out.println(current.t + "");
			current = current.next;
		}
	}
}
