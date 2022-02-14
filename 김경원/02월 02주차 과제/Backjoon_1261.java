import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Backjoon_1261 {
	
	static public class Node implements Comparable<Node>{
		int i,j, w;
		Node(int i, int j, int w){
			this.i = i;
			this.j = j;
			this.w = w;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int N,M;
		M = Integer.parseInt(st.nextToken(" "));
		N = Integer.parseInt(st.nextToken(" "));
		
		int[][] arr = new int[N][M];
		
		List<Node>[][] edge = new ArrayList[N][M];
		
		
		int[] ni = {-1,1,0,0};
		int[] nj = {0,0,-1,1};
		
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++) {
				
				arr[i][j] = str.charAt(j) -'0';
				
				for(int d=0;d<4;d++) {
					int y = i+ni[d];
					int x = j+nj[d];
					if( y <0 || x <0 || y >=N || x >= M) {
						continue;
					}
					
					
					
					
				}
			}
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				edge[i][j] = new ArrayList<>();
				for(int d=0;d<4;d++) {
					int y = i+ni[d];
					int x = j+nj[d];
					if( y <0 || x <0 || y >=N || x >= M) {
						continue;
					}
					edge[i][j].add(new Node(y,x,arr[y][x]));
				}
			}
		}
		
		int answer = dijkstra(N,M,edge);
		System.out.println(answer);
		
		
		
	}
	
	
	
	static public int dijkstra(int N, int M, List<Node>[][] edge) {
		
		
		
		int[][] dist = new int[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				dist[i][j] = 100000000;
			}
		}
		
		Queue<Node> q = new PriorityQueue<>();
		
		q.add(new Node(0,0,0));
		
		while(!q.isEmpty()) {
			
			Node now = q.poll();
			
			if(dist[now.i][now.j] < now.w) {
				continue;
			}
			
			dist[now.i][now.j] = now.w;
			
			for(Node next : edge[now.i][now.j]) {
				
				if(dist[next.i][next.j] > next.w + now.w) {
					dist[next.i][next.j] = next.w + now.w;
					q.add(new Node(next.i, next.j, dist[next.i][next.j]));
				}
				
				
			}
			
			
		}
		
		
		return dist[N-1][M-1];
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
