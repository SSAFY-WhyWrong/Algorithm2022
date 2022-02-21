package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon5052_전화번호목록 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int t=0; t<T; t++) {
			int n = Integer.parseInt(br.readLine());
			
			String[] number = new String[n];
			for(int i=0; i<n; i++) {
				String str = br.readLine();
				number[i] = str;
			}
			Arrays.sort(number);
			int start = 0;
			boolean flag = false;
			for(int i=1; i<n; i++) {
				if(number[i].length() < number[i-1].length()) {
					start = i;
					continue;
				}
				for(int j=start; j<i; j++) {
					int len = number[j].length();
					
					if(number[i].substring(0, len).equals(number[j])) {
						flag = true;
						break;
					}
				}
			}
			if(flag)sb.append("NO\n");
			else sb.append("YES\n");
		}
		System.out.println(sb.toString());
	}
}
