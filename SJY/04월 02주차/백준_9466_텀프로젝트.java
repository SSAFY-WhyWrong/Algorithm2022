import java.util.*;
import java.io.*;

public class 백준_9466_텀프로젝트 {
	
	static int cnt;
	static int[] nums;
	static boolean[] visit, group;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		
		while(T-- > 0) {
			int N = Integer.parseInt(bf.readLine());
			StringTokenizer st = new StringTokenizer(bf.readLine());
			nums = new int[N+1];
			visit = new boolean[N+1];
			group = new boolean[N+1];
			
			for(int i=1;i<N+1;i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			cnt = 0;
			for(int i=1;i<N+1;i++) {
				if(!visit[i]) {
					dfs(i);
				}
			}
			
			System.out.println(N - cnt);
		}
	}
	
	public static void dfs(int now) {
		visit[now] = true;
		
		int next = nums[now];
		if(!visit[next]) {
			dfs(next);
		} else {
			if(!group[next]) {
				cnt++;
				
				while(next != now) {
					cnt++;
					next = nums[next];
				}
			}
		}
		
		group[now] = true;
	}

}
