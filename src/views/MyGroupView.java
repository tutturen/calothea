package views;

import java.util.ArrayList;
import java.util.Stack;

public class MyGroupView implements View{
	
	private boolean done;

	public MyGroupView(){
		this.done = false; 
	}
	
	@Override
	public boolean isDone() {
		return done;
	}

	@Override
	public void setUnDone() {
		this.done = false;
		
	}

	@Override
	public String getTitle() {
		return "Dine grupper";
	}

	@Override
	public ArrayList<String> getContent() {
		ArrayList<String> content = new ArrayList<String>();
		content.add("");
		content.add("1.\t Beste gruppa");
		content.add("2.\t Nest beste gruppa");
		content.add("3.\t Tred beste gruppa");
		content.add("");
		return content;
		
		
	}

	@Override
	public String getQuery() {
		return "Press 0 for å gå tilbake, eller velg gruppe";
	}

	@Override
	public void giveInput(String input, Stack<View> viewStack) {
		if(input.equals("0")){
			this.done = true;
		}
		else{
			
			viewStack.push(new GroupView(getContent().get(Integer.parseInt(input))));
		}
		
		
	}

}
