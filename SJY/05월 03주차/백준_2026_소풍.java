import java.util.*;
import java.io.*;

public class 백준_2026_소풍 {
	
	static int K, N, F;
	static boolean end;
	static boolean[] visited;
	static boolean[][] friends;
	static ArrayList<Integer> list;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		K = sc.nextInt();
		N = sc.nextInt();
		F = sc.nextInt();
		
		friends = new boolean[901][901];
		
		for(int i=0;i<F;i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			friends[a][b] = true;
			friends[b][a] = true;
		}

		for(int i=1;i<N;i++) {
			end = false;
			list = new ArrayList<>();
			visited = new boolean[N+1];
			
			dfs(i);
			
			if(end) break;
		}
		
		if(!end) {
			System.out.println(-1);
		}
	}
	
	public static void dfs(int v) {
		if(end) return;
		
		visited[v] = true;
		list.add(v);
		
		if(K == list.size()) {
			for(int f : list) {
				System.out.println(f);
			}

			end = true;
			return;
		}
		
		for(int i=v+1;i<=N;i++) {
			if(!visited[i]) {
				boolean flag = true;
				
				for(int f : list) {
					if(!friends[i][f]) {
						flag = false;
						break;
					}
				}
				
				if(flag) dfs(i);
					
			}
		}
	}
}