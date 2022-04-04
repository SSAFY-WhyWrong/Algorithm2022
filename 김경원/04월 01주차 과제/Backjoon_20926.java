import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Backjoon_20926 {
	
	static class Node implements Comparable<Node>{
		int i,j,w;
		
		Node(int i,int j, int w){
			this.i = i;
			this.j = j;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.w - o.w;
		}
		
		
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int W,H;
		
		st = new StringTokenizer(br.readLine());
		
		W  = Integer.parseInt(st.nextToken());
		H  = Integer.parseInt(st.nextToken());
		
		int[][] arr  = new int[H][W];
		Node start = null, end = null;
		
		for(int i=0;i<H;i++) {
			String str = br.readLine();
			for(int j=0;j<W;j++) {
				char token = str.charAt(j);
				
				if(token == 'R') {
					arr[i][j] = -1;
				}else if(token == 'E') {
					end = new Node(i,j,0);
					arr[i][j] = -2;
				}else if(token == 'T') {
					start = new Node(i,j,0);
				}else if(token == 'H'){
					arr[i][j] = -3;
					
				}else {
					arr[i][j] = token -'0';
				}
				
				
			}
		}
		
		int[] ni = {-1,1,0,0};
		int[] nj = {0,0,-1,1};
		
		List<Node>[][] edge = new ArrayList[H][W];
		
		
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				edge[i][j] = new ArrayList<>();
				for(int d=0;d<4;d++) {
					int y = i + ni[d];
					int x = j + nj[d];
					int w = 0;
					while(y >= 0 && x>= 0 && y < H && x < W && arr[y][x] >= 0) {
						w += arr[y][x];
						y += ni[d];
						x += nj[d];
					}
					
					if(y < 0 || x < 0 || y>=H || x>=W || arr[y][x] == -3) {
						continue;
					}
					
					if(arr[y][x] == -2) {
						edge[i][j].add(new Node(y,x,w));
					}else if(arr[y][x] == -1 && (y-ni[d] != i || x - nj[d] != j)) {
						edge[i][j].add(new Node(y-ni[d],x-nj[d],w));
					}
					
				}
			}
		}
		
		System.out.println(dijkstra(edge,start,end,H,W));
		
		
	}
	static final int NO_WAY = 2000000000;
	static int dijkstra(List<Node>[][] edge, Node start, Node end, int H, int W) {
		
		int answer = -1;
		int[][] dist  = new int[H][W];
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				dist[i][j] = NO_WAY;
			}
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		pq.add(start);
		
		boolean isVisited[][] = new boolean[H][W];
		
		while(!pq.isEmpty()) {
			
			Node now = pq.poll();
			
			if(isVisited[now.i][now.j]) {
				continue;
			}
			isVisited[now.i][now.j] = true;
			
			for(Node node : edge[now.i][now.j]) {
				if(now.w + node.w < dist[node.i][node.j]) {
					dist[node.i][node.j] = now.w + node.w; 
					pq.add(new Node(node.i, node.j, dist[node.i][node.j]));
				}
			}
		}
		
		answer = dist[end.i][end.j];
		if(answer == NO_WAY) {
			answer = -1;
		}
		
		
		return answer;
	}
	
}
