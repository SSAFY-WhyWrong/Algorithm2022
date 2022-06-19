package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baekjoon7490_0만들기 {
	
	static int N;
	static ArrayList<String> list;
	static StringBuilder sb;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int i=0; i<T; i++) {
			N = Integer.parseInt(br.readLine());
			list = new ArrayList<>();
			sb = new StringBuilder();
			
			dfs(1,1,0,1,"1");
			System.out.println(sb.toString());
		}
		
	}
	private static void dfs(int idx, int num, int sum, int val, String str) {
		
		if(idx == N) {
			sum += (num*val);
			if(sum == 0) sb.append(str+"\n");
			return;
		}
		
		dfs(idx+1, num*10+(idx+1), sum, val, str+" "+Integer.toString(idx+1));
		dfs(idx+1, idx+1, sum+(num*val), 1, str+"+"+Integer.toString(idx+1));
		dfs(idx+1, idx+1, sum+(num*val), -1, str+"-"+Integer.toString(idx+1));
	}
}
