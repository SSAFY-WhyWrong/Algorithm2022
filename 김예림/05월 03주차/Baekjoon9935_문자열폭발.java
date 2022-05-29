package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Baekjoon9935_문자열폭발 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String word = br.readLine();
		
		Stack<Character> stack = new Stack<>();
		int idx = word.length()-1;
		for(int i=0; i<s.length(); i++) {
			stack.add(s.charAt(i));
			
			//stack의 끝에서 word의 길이만큼 직접 검사해보고
			if(stack.peek()==word.charAt(idx) && stack.size()-idx>0) {
				boolean flag = true;
				for(int j=0; j<=idx; j++) {
					if(word.charAt(j) != stack.get(stack.size()-idx+j-1)) {
						flag = false;
						break;
					}
				}
				//stack을 검사했을 때 word랑 일치하는 문자열이 있으면 문자열의 길이만큼 stack에서 pop해준다.
				if(flag) {
					while(idx>=0) {
						stack.pop();
						idx--;
					}
					idx = word.length()-1;
				}
			}
			
		}
		StringBuilder sb = new StringBuilder();
		if(stack.isEmpty() || stack.size() == 0) System.out.println("FRULA");
		else {
			while(!stack.isEmpty()) {
				sb.append(stack.pop());
			}
			System.out.println(sb.reverse().toString());
		}
	}
}

