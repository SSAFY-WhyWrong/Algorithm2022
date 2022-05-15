package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Baekjoon1339_단어수학 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		String[] word = new String[N];
		HashMap<Character, Integer> map = new HashMap<>();
		for(int i=0; i<N; i++) {
			String tmp = br.readLine();
			word[i] = tmp;
			
			int len = tmp.length();
			
			for(int j=0; j<len; j++) {
				char c = tmp.charAt(len-1-j);
				
				if(map.containsKey(c)) map.replace(c, map.get(c)+(int)Math.pow(10, j));
				else map.put(c, (int)Math.pow(10, j));
			}
		}
		int[] num = new int[map.size()];
		int idx = 0;
		for(Map.Entry<Character, Integer> m : map.entrySet()) {
			num[idx++] = m.getValue();
		}
		Arrays.sort(num);
		int n = 9;
		int tot = 0;
		for(int i=num.length-1; i>=0; i--) {
			tot += num[i]*n--;
		}
		System.out.println(tot);
	}
}
