import java.util.*;
import java.io.*;

public class 백준_10282_해킹 {
	
	static class Computer implements Comparable<Computer> {
		
		int idx;
		int time;
		
		public Computer(int idx, int time) {
			this.idx = idx;
			this.time = time;
		}

		@Override
		public int compareTo(Computer o) {
			return this.time - o.time;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			ArrayList<Computer>[] info = new ArrayList[N+1];
			for(int i=1;i<N+1;i++) {
				info[i] = new ArrayList<>();
			}
			
			for(int i=0;i<D;i++) {
				st = new StringTokenizer(bf.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				
				info[b].add(new Computer(a, s));
			}
			
			int[] d = new int[N+1];
			Arrays.fill(d, Integer.MAX_VALUE);
			d[C] = 0;
			PriorityQueue<Computer> pq = new PriorityQueue<>();
			pq.add(new Computer(C, 0));
			
			while(!pq.isEmpty()) {
				Computer now = pq.poll();
				
				if(d[now.idx] < now.time) continue;
				
				for(int i=0;i<info[now.idx].size();i++) {
					int nt = d[now.idx] + info[now.idx].get(i).time;
					
					if(nt < d[info[now.idx].get(i).idx]) {
						d[info[now.idx].get(i).idx] = nt;
						pq.add(new Computer(info[now.idx].get(i).idx, nt));
					}
				}
			}
			
			int cnt = 0, answer = 0;
			for(int i=1;i<N+1;i++) {
				if(d[i] != Integer.MAX_VALUE) {
					cnt++;
					answer = Math.max(answer, d[i]);
				}
			}
			
			System.out.println(cnt + " " + answer);
		}
	}

}
