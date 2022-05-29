package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon15927_회문은회문아니야 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		
		int ans = 0;
		String rev = new StringBuilder(s).reverse().toString();
		if(s.equals(rev)) {
			if(s.length()==1 || s.charAt(0)==s.charAt(s.length()-2)) {
				//같은 character로 이루어져있는지 검사하기
				boolean flag = true;
				for(int i=s.length()-1; i>=0; i--) {
					if(s.charAt(0) != s.charAt(i)) {
						flag = false;
						break;
					}
				}
				if(flag) ans = -1;
				else ans = s.length()-1;
			}else ans = s.length()-1;
			
			
		}else {
			ans = s.length();
		}
		System.out.println(ans);
	}
}
