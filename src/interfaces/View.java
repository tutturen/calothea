package interfaces;

import java.util.ArrayList;
import java.util.Stack;

public interface View {

	public boolean isDone();
	public void setUnDone();
	public String getTitle();
	public ArrayList<String> getContent();
	public String getQuery();
	public void giveInput(String input, Stack<View> viewStack);
}
