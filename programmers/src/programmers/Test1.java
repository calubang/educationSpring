package programmers;

public class Test1 {
	public static void main(String[] args) {
		String answer = "";
		String s = "abcde";
		
		int length = s.length();
		answer = s.substring( (int)(length/2.0 - 0.5), length/2 + 1);
		System.out.println(answer);
		
	}
}
