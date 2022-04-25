import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Backjoon_1766 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N,M;
		
		st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] list = new ArrayList[N+1];
		int[] cnt = new int[N+1];
		
		for(int i=1;i<=N;i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken(" "));
			int b = Integer.parseInt(st.nextToken(" "));
			
			list[a].add(b);
			cnt[b]++;
			
		}
		
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i=1;i<=N;i++) {
			if(cnt[i]==0) {
				pq.add(i);
			}
		}
		
		
		StringBuilder sb = new StringBuilder();
		while(!pq.isEmpty()) {
			
			int now = pq.poll();
			sb.append(now).append(" ");
			
			for(int next : list[now]) {
				cnt[next]--;
				
				if(cnt[next]==0) {
					pq.add(next);
				}
				
				
			}
			
		}
		System.out.println(sb);
		
		
	}
}
