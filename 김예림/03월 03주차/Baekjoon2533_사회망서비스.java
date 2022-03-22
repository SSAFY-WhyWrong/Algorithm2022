package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon2533_사회망서비스 {
	
	static int N;
	static boolean[] accept;
	static int[][] dp;
	static ArrayList<Integer>[] list; //자식들 list로 넣기
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); //친구관계트리의 정점 개수
		
		list = new ArrayList[N+1];
		for(int i=0; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		
		dp = new int[N+1][2];
		accept = new boolean[N+1]; //해당 정점의 사람이 아이디어를 받아들였는지 여부 체크
		for(int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			list[u].add(v);
			list[v].add(u);
		}
		
		dfs(1,-1);
		//얼리어답터가 됐을 때와, 아닐 떄 중에 최소값 출력
		int ans = Math.min(dp[1][0], dp[1][1]);
		System.out.println(ans);
		
	}
	
	private static void dfs(int cur, int parent) {
		
		dp[cur][0] = 0;
		dp[cur][1] = 1;
		for(int i=0; i<list[cur].size(); i++) {
			if(list[cur].get(i) != parent) { //자식이 있으면 타고 다시 들어가기
				dfs(list[cur].get(i), cur);
				dp[cur][0] += dp[list[cur].get(i)][1];
				dp[cur][1] += Math.min(dp[list[cur].get(i)][0], dp[list[cur].get(i)][1]);
			}
		}
		
		
	}
}
