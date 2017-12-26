import java.util.Stack;


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
    
    
    /**
     * 给定一个字符串，其中只包含()，[]，{},判定字符串中的括号匹配是否合法。

比如 “()”，“()[]{}”是合法的，“(}”，“([)]”是非法的。
     * 用一个Stack来存储括号， 主要考察对栈数据结构的操作。

算法的时间复杂度是O(n)，空间复杂度也是O(n)。

遍历传入的String s，如果遇到左括号就入栈；如果遇到右括号，检查栈如果为空，证明不能匹配，如果栈不空，pop出栈顶的元素，看是否与当前的右括号匹配。

如果匹配，继续向下进行新一轮的循环，如果不匹配，返回false.
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
    	char[] chars = s.toCharArray();
    	Mystack<Character> stack = new Mystack<Character>();
    	for (int i = 0; i < chars.length; i++) {
    		char t = chars[i];
    		if (t == '(' || t == '{' || t == '[') {
    			stack.push(t);
    		} else {
    			if (t == ')') {
					if (stack.isEmpty() || stack.pop() != '(') {
						return false;
					}
    			} else if (t == '}') {
					if (stack.isEmpty() || stack.pop() != '{') {
						return false;
					}
    			}else if (t == ']') {
					if (stack.isEmpty() || stack.pop() != '[') {
						return false;
					}
    			}
    		}
    	}
    	
    	return stack.isEmpty();
    }
    
    public static void main(String[] args) {
//    	Mystack<String> stack = new Mystack<String>();
//		for (int i = 0; i < 5; i++) {
//			stack.push(i + "");
//		}
//	
//		while (!stack.isEmpty()) {
//			System.out.println(stack.pop());
//		}
    	System.out.print(isValid("{([])}"));
	}
}
