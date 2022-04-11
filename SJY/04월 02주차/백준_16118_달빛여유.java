import java.util.*;
import java.io.*;

public class 백준_16118_달빛여유 {

	static class Speed implements Comparable<Speed> {
		int node;
		double v;
		int wolfControl; // 0: 두배 빠르게, 1: 느리게
		
		public Speed(int node, double v, int wolfControl) {
			this.node = node;
			this.v = v;
			this.wolfControl = wolfControl;
		}

		@Override
		public int compareTo(Speed o) {
			if(this.v > o.v) {
				return 1;
			} else if(this.v == o.v) {
				return this.node - o.node;
			}
			
			return -1;
//			return this.v - o.v;
		}
		
		
	}
	
	static class Road {
		int tree, dir;
		
		public Road(int tree, int dir) {
			this.tree = tree;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<Road>[] roads = new ArrayList[N+1];
		
		for(int i=0;i<N+1;i++) {
			roads[i] = new ArrayList<>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(bf.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			roads[s].add(new Road(e, c));
			roads[e].add(new Road(s, c));
		}
		
		PriorityQueue<Speed> pq = new PriorityQueue<>();
		int[] fox = new int[N+1];
		double[][] wolf = new double[N+1][2];
		
		for(int i=0;i<N+1;i++) {
			fox[i] = Integer.MAX_VALUE;
			wolf[i][0] = Integer.MAX_VALUE;
			wolf[i][1] = Integer.MAX_VALUE;
		}
		
		fox[1] = 0;
		wolf[1][0] = 0.0;
//		wolf[1][1] = 0;
		pq.add(new Speed(1, 0, 0));
		
		// 여우
		while(!pq.isEmpty()) {
			Speed now = pq.poll();
			
			if(fox[now.node] < now.v) continue;
			
			for(int i=0;i<roads[now.node].size();i++) {
				Road next = roads[now.node].get(i);
				
				if(fox[next.tree] > now.v + (next.dir)) {
					fox[next.tree] = (int) (now.v + (next.dir));
					pq.add(new Speed(next.tree, fox[next.tree], 0));
				}
			}
		}
		
		// 늑대
		pq = new PriorityQueue<>();
		pq.add(new Speed(1, 0.0, 0));
		
		while(!pq.isEmpty()) {
			Speed now = pq.poll();
			
			if(wolf[now.node][now.wolfControl] < now.v) continue;
			
			for(int i=0;i<roads[now.node].size();i++) {
				Road next = roads[now.node].get(i);
				
				double nextV = (now.wolfControl == 0 ? next.dir / 2.0: next.dir * 2.0);
				int d = (now.wolfControl == 0 ? 1 : 0);
				
				if(wolf[next.tree][d] > now.v + nextV) {
					wolf[next.tree][d] = now.v + nextV;

					pq.add(new Speed(next.tree, wolf[next.tree][d], d));
				}
			}
		}
		
		int answer = 0;
		
		for(int i=1;i<N+1;i++) {
			if(fox[i] < Math.min(wolf[i][0], wolf[i][1])) answer++;
//			System.out.println("i = " + i + " fox = " + fox[i] + " wolf[0] = " + wolf[i][0] + " wolf[1] = " + wolf[i][1]);
		}
		
		System.out.println(answer);
	}

}
