import java.util.*;
import java.io.*;

public class 백준_20666_인물이와정수 {

	static class Info implements Comparable<Info> {
		int idx;
		long level;
		
		public Info(int idx, long level) {
			this.idx = idx;
			this.level = level;
		}

		@Override
		public int compareTo(Info o) {
			if(this.level == o.level) {
				return this.idx - o.idx;
			} else if(this.level < o.level) {
				return -1;
			}
			
			return 1;
		}

		@Override
		public String toString() {
			return "Info [idx=" + idx + ", level=" + level + "]";
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long[] levels = new long[N+1];
		
		st = new StringTokenizer(bf.readLine());
		for(int i=1;i<N+1;i++) {
			levels[i] = Integer.parseInt(st.nextToken());
		}
		
		int p = Integer.parseInt(bf.readLine());
		ArrayList<Info>[] items = new ArrayList[N+1];
		for(int i=0;i<N+1;i++) {
			items[i] = new ArrayList<>();
		}
		
		for(int i=0;i<p;i++) {
			st = new StringTokenizer(bf.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			
			levels[e] += l;
			items[s].add(new Info(e, l));
		}
		
		boolean[] visit = new boolean[N+1];
		PriorityQueue<Info> pq = new PriorityQueue<>();
		for(int i=1;i<N+1;i++) {
//			System.out.println("i = " + i + " level = " + levels[i]);
			pq.add(new Info(i, levels[i]));
		}
		
		int cnt = 0;
		long ans = 0;
		while(cnt < M && !pq.isEmpty()) {
			Info now = pq.poll();
//			System.out.println(now.toString());
			if(visit[now.idx]) continue;
			
			visit[now.idx] = true;
			for(Info change : items[now.idx]) {
				if(visit[change.idx]) continue;
				levels[change.idx] -= change.level;
				pq.add(new Info(change.idx, levels[change.idx]));
			}
			
			ans = Math.max(ans, now.level);
			cnt++;
		}
		
		System.out.println(ans);
	}

}
