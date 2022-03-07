import java.util.*;
import java.io.*;

public class 백준_2550_전구 {
	
	static class DP implements Comparable<DP>{
		int cnt;
		String list;
		
		public DP(int cnt, String list) {
			this.cnt = cnt;
			this.list = list;
		}

		@Override
		public String toString() {
			return "DP [cnt=" + cnt + ", list=" + list + "]";
		}

		@Override
		public int compareTo(DP o) {
			return o.cnt - this.cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		int[] click = new int[N+1];
		int[] blub = new int[N+1];
		int[] revClick = new int[N+1];
		int[] revBlub = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for(int i=1;i<N+1;i++) {
			int s = Integer.parseInt(st.nextToken());
			
			click[i] = s;
			revClick[s] = i;
		}
		
		st = new StringTokenizer(bf.readLine());
		for(int i=1;i<N+1;i++) {
			int s = Integer.parseInt(st.nextToken());
			
			blub[i] = s;
			revBlub[s] = i;
		}
		
		DP[] dp = new DP[N+1];
		for(int i=0;i<N+1;i++) {
			dp[i] = new DP(0, "");
		}
		
		for(int i=1;i<N+1;i++) {
			dp[click[i]] = new DP(1, Integer.toString(click[i]) + " ");
			
			for(int j=1;j<revBlub[click[i]];j++) {
				if(revClick[blub[j]] >= i) continue;
				
				if(dp[click[i]].cnt < dp[blub[j]].cnt + 1) {
					dp[click[i]].cnt = dp[blub[j]].cnt + 1;
					dp[click[i]].list = dp[blub[j]].list + Integer.toString(click[i]) + " ";
				}
			}
		}
		
		Arrays.sort(dp);
		System.out.println(dp[0].cnt);
		String[] listArr = dp[0].list.split(" ");
		Arrays.sort(listArr);
		for(int i=0;i<dp[0].cnt;i++) {
			System.out.print(listArr[i] + " ");
		}
	}

}
