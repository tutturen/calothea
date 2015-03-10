package utlils;

public class Console {
	public static final int HEIGHT = 21;
	public static final int WIDTH = 89;
	
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
	
	
}
