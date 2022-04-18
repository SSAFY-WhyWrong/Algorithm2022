import java.util.*;
import java.io.*;

public class 백준_21774_가희와로그파일 {

	static long[] logs;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		logs = new long[N+2];
		logs[N+1] = Integer.MAX_VALUE;
		int[] lv = new int[N+1];
		int[][] dp = new int[N+1][7];
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(bf.readLine(), "#");
			logs[i] = timeToLong(st.nextToken());
			lv[i] = Integer.parseInt(st.nextToken());
			
			for(int j=lv[i];j>0;j--) {
				dp[i][j]++;
			}
		}
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<7;j++) {
				dp[i][j] += dp[i-1][j];
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<Q;i++) {
			st = new StringTokenizer(bf.readLine(), "#");
			long start = timeToLong(st.nextToken());
			long end = timeToLong(st.nextToken());
			int level = Integer.parseInt(st.nextToken());
			
			int lower = lowerBound(1, N+1, start); // 위
			int upper = upperBound(1, N+1, end); // 아래
			int cnt = dp[upper-1][level] - dp[lower-1][level];
			sb.append(cnt + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	public static long timeToLong(String time) {
		return Long.parseLong(
				time.substring(0, 4) + time.substring(5, 7) 
				+ time.substring(8, 10) + time.substring(11, 13)
				+ time.substring(14, 16) + time.substring(17, 19));
	}
	
	public static int lowerBound(int left, int right, long target) {
		while(left < right) {
			int mid = (left + right) / 2;
			
			if(target <= logs[mid]) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		
		return right;
	}
	
	public static int upperBound(int left, int right, long target) {
		while(left < right) {
			int mid = (left + right) / 2;
			
			if(target >= logs[mid]) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		
		return right;
	}

}
