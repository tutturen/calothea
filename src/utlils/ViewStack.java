package utlils;

import interfaces.View;

import java.util.Stack;

public class ViewStack {

	private Stack<View> stack;

	public ViewStack() {
		stack = new Stack<View>();
	}
	
	public View pop() {
		return stack.pop();
	}
	
	public View peek() {
		return stack.peek();
	}
	
	public void push(View view) {
		stack.push(view);
	}
	
	public boolean isEmpty() {
		return stack.size() == 0;
	}
	
	@Override
	public String toString() {
		String res = "";
		if (stack.size() < 3) {
			for (int i = 1; i < stack.size(); i++) {
				res += " > " + stack.get(i).getTitle();
			}
			return res;
		} else {
			if (stack.size() != 3) {
				res += " > ...";
			}
			for (int i = stack.size() - 2; i < stack.size(); i++) {
				res += " > " + stack.get(i).getTitle();
			}
			return res;
		}
	}
}
