package interfaces;

import java.util.ArrayList;
import utlils.ViewStack;

public interface View {

	public boolean isDone();
	public void setUnDone();
	public String getTitle();
	public ArrayList<String> getContent();
	public String getQuery();
	public void giveInput(String input, ViewStack viewStack);
}
