package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon9663_N_Queen {
	
	static int N, tot;
	static int[] select;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		select = new int[N];
		
		tot = 0;
		DFS(0);
		System.out.println(tot);
	}
	
	private static void DFS(int cnt) {
		
		if(cnt == N) {
			tot++;
			return;
		}
		
		for(int i=0; i<N; i++) {
			select[cnt] = i;
			
			if(Check(cnt)) {
				DFS(cnt+1);
			}
		}
	}
	
	private static boolean Check(int cur) {
		
		for(int i=0; i<cur; i++) {
			if(select[i] == select[cur]) return false;
			else if(Math.abs(cur-i) == Math.abs(select[cur]-select[i])) 
				return false;
		}
		return true;
	}
}
