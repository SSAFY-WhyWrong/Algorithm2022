import java.util.*;
import java.io.*;

public class 백준_2533_사회망서비스 {

	static ArrayList<Integer>[] friends;
	static boolean[] visit;
	static int[][] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		friends = new ArrayList[N+1];
		
		for(int i=0;i<N+1;i++) {
			friends[i] = new ArrayList<>();
		}
		
		for(int i=0;i<N-1;i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			friends[u].add(v);
			friends[v].add(u);
		}
		
		visit = new boolean[N+1];
		dp = new int[N+1][2]; // 0 -> 얼리어답터인 경우, 1 -> 아닌 경우
		dfs(1);
		
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}

	private static void dfs(int node) {
		visit[node] = true;
		dp[node][0] = 1;
		dp[node][1] = 0;
		
		for(int i=0;i<friends[node].size();i++) {
			int next = friends[node].get(i);
			
			if(visit[next]) continue;
			dfs(next);
			dp[node][0] += Math.min(dp[next][0], dp[next][1]);
			dp[node][1] += dp[next][0];
		}
	}

}
