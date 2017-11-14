
public class MyListNode<T>  {
	public T t;
	public MyListNode<T> next;

	public MyListNode(T t, MyListNode node) {
		this.t = t;
		this.next = node;
	}
}
