import java.util.Objects;


public class MyHashMap<K,T> {

	public static class MyHashMapEntry<K,T> {
		public K key;
		public T value;
		
		public MyHashMapEntry(K key, T value) {
			this.key = key;
			this.value = value;
		}
	}
	
	private float DEFAULT_EXPAND_RATE = 0.75f; 
	
	private int maxSize = 256;
	private int size = 0;
	
	private MyLinkList<MyHashMapEntry<K,T>> []array = new MyLinkList[maxSize];
	
	private int hashCode(K key) {
		return Objects.hashCode(key) & (maxSize - 1);
	} 
	
	public void put(K key, T value) {
		if (size >= maxSize) {
			return;
		}
		int index = hashCode(key);
		if (array[index] == null) {
			array[index] = new MyLinkList<MyHashMapEntry<K,T>>();
		}
		MyListNode<MyHashMapEntry<K, T>> current = array[index].firstNode;
		while(current != null) {
			if (index == hashCode(current.t.key) && (current.t.key == key || key.equals(current.t.key))) {
				current.t.value = value;
				size++;
				return;
			}
			current = current.next;
		}
		
		array[index].add(new MyHashMapEntry<K, T>(key, value));
		size++;
	}
	
	public T get(K key) {
		int index = hashCode(key);
		if (array[index] == null) {
			return null;
		}
		
		MyListNode<MyHashMapEntry<K, T>> current = array[index].firstNode;
		
		while(current != null) {
			if (index == hashCode(current.t.key) && (current.t.key == key || key.equals(current.t.key))) {
				return current.t.value;
			}
			current = current.next;
		}
		return null;
	}
	
	public void clear() {
		for(int i = 0; i < maxSize; i++) {
			if (array[i] != null) {
				array[i].clear();
			}
			array[i] = null;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyHashMap<String, String> map = new MyHashMap();
		map.put("a", "a");
		map.put("b", "b");
		map.put("c", "c");
		map.put("d", "d");
		map.put(null, "阿什顿发");
		
		System.out.println(map.get("a"));
		System.out.println(map.get("c"));
		System.out.println(map.get(null));
		
		System.out.println(1000000058 & 255);
	}

}
