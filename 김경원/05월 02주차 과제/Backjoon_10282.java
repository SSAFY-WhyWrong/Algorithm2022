import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Backjoon_10282{
	
	static class Node implements Comparable<Node>{
		int num, weight;
		
		Node(int num, int weight){
			this.num = num;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		int T;
		
		T = Integer.parseInt(br.readLine());
		
		for(int t=0;t<T;t++) {
			
			int n,d,c;
			
			st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken()) - 1;
			
			List<Node>[] edge = new ArrayList[n];
			
			for(int j=0;j<n;j++) {
				edge[j] = new ArrayList<>();
			}
			
			for(int j=0;j<d;j++) {
				int from, to, w;
				st = new StringTokenizer(br.readLine());
				to = Integer.parseInt(st.nextToken()) - 1;
				from = Integer.parseInt(st.nextToken()) - 1;
				w = Integer.parseInt(st.nextToken());
				
				edge[from].add(new Node(to,w));
			}
			
			dijkstra(edge,n, c);
			
		}
		
		
		
	}
	
	
	static void dijkstra(List<Node>[] edge, int n, int c) {
		
		int[] dist = new int[n];
		for(int i=0;i<n;i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		pq.add(new Node(c,0));
		
		while(!pq.isEmpty()) {
			
			Node now = pq.poll();
			if(dist[now.num] < now.weight) {
				continue;
			}
			
			dist[now.num] = now.weight; 
			
			for(Node next : edge[now.num]) {
				if(dist[next.num] - dist[now.num] > next.weight ) {
					dist[next.num] = dist[now.num]+ next.weight;
					pq.add(new Node(next.num, dist[next.num]));
				}
				
				
			}
			
			
			
		}
		
		int count = 0;
		int answer = 0;
		for(int i=0;i<n;i++) {
			if(dist[i] != Integer.MAX_VALUE) {
				count ++;
				answer = Math.max(answer, dist[i]);
			}
		}
		
		System.out.println(count +" "+answer);
		
	}
}
