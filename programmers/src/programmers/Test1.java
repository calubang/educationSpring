package programmers;

import java.util.ArrayList;
import java.util.List;

public class Test1 {
	public static void main(String[] args) {
		String answer = "";
		String s = "abcde";
		
		int length = s.length();
		answer = s.substring( (int)(length/2.0 - 0.5), length/2 + 1);
		System.out.println(answer);
		
		
		
		List<String> likeList = new ArrayList<String>();
		List<String> parseLikeList = new ArrayList<String>();
		
		for(String str : likeList) {
			if(str != null && str.length() <= 2 ) {
				parseLikeList.add(str);
			}else if(str != null && str.length() > 2){
				for(int i=str.length() -1 ; i > 2 ; i--) {
					parseLikeList.add(str.substring(0, i));
				}
			}else {
				
			}
		}
		String temp = "";
		StringBuffer sb = new StringBuffer();
		for(String str : parseLikeList) {
			sb.append(str+"|");
		}
		sb.substring(0, sb.length()-1);

	}
}
