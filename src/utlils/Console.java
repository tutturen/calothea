package utlils;

import java.util.ArrayList;

public class Console {
	public static final int HEIGHT = 24;
	public static final int WIDTH = 80;
	
	public static String charLine(char c, int nr) {
		String str = "";
		for (int i = 0; i < nr; i++) {
			str += c;
		}
		return str;
	}
	
	public static String emptySpace(int fill, int max) {
		return charLine(' ', max - fill);
	}
	
	public final static void clearConsole() {
		try {
			final String ANSI_CLS = "\u001b[2J";
			final String ANSI_HOME = "\u001b[H";
			System.out.print(ANSI_CLS + ANSI_HOME);
			System.out.flush();
		} catch (final Exception e) {
			// Handle any exceptions.
		}
	}
	
	public static void print(String str) {
		System.out.print(str);
	}

	public static void println(String str) {
		System.out.println(str);
	}
	
	public static String matchLength(String content, int len) {
		String res = "";
		for (int i = 0; i < len; i++) {
			if (i >= content.length()) {
				res += " ";
			} else {
				res += content.charAt(i) + "";
			}
		}
		return res;
	}
	
	public static String tableHead(String name, int width) {
		String extra = "";
		if (name.length() % 2 != 0) {
			extra = "-";
		}
		int charLen = (width - name.length()) / 2;
		String line = charLine('-', charLen - 2);
		return "+" + line + " " + name.toUpperCase() + " " + line + extra + "+";
	}
	
	public static String tableRow(String content, int width) {
		String c = matchLength(content, width - 4);
		return "| " + c + " |"; 
	}
	
	public static String tableRow(int width) {
		String c = charLine('-', width - 2);
		return "+" + c + "+"; 
	}
	
	public static ArrayList<String> fitInBox(String msg, int maxWidth, int minimumLines) {
		ArrayList<String> lines = new ArrayList<String>();
		String[] words = msg.split(" ");
		String line = " ";
		for (int i = 0; i < words.length; i++) {
			int totalWidth = line.length() + words[i].length() + 2;
			if (totalWidth < maxWidth) {
				line += words[i] + " ";
			} else {
				lines.add(line);
				line = " ";
				i--;
			}
		}
		lines.add(line);
		while (lines.size() < minimumLines) {
			lines.add("");
		}
		return lines;
		
	}
}
