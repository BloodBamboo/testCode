
public class Mystack<T> {

	private MyLinkList<T> data = new MyLinkList<T>();
	private int max = 15;
	private int top = 0;
	
	
	//栈大小
    public int length(){
        return top;
    }
    
    //是否为空栈
    public boolean isEmpty(){
        return top==0;
    }
	
    //查看栈顶值
    public T peek() {
    	if (isEmpty()) {
    		return null;
    	} else {
			return data.lastNode.t;
		}
    }
    
    //往栈顶添加值
    public boolean push(T t) {
    	try {
    		data.add(t);
    		top++;
        	return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return false;
    }
    
    
    //移除栈顶值并返回
    public T pop() {
    	if (isEmpty()) {
    		return null;
    	} else {
    		top--;
			return data.remove();
		}
    }
    
    public static void main(String[] args) {
    	Mystack<String> stack = new Mystack<String>();
		for (int i = 0; i < 5; i++) {
			stack.push(i + "");
		}
	
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}
}
