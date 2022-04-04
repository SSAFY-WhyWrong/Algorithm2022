package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon20926_얼음미로 {
	
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	static int[][] maze;
	static boolean[][] visited;
	static int W, H, min;
	static Point user, exit;
	static PriorityQueue<Point> pq;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		//T:캐릭터, R:바위, H:구멍, E:출구
		//-1은 구멍, 10은 돌, 100은 출구
		maze = new int[H][W];
		visited = new boolean[H][W];
		for(int h=0; h<H; h++) {
			String str = br.readLine();
			for(int w=0; w<W; w++) {
				char c = str.charAt(w);
				
				int num = 0;
				switch (c) {
				case 'R':
					num = 10;
					break;
				case 'E':
					num = 100;
					//exit = new Point(h, w, 0);
					break;
				case 'T':
					num = 0; //캐릭터
					user = new Point(h, w, 0);
					break;
				default:
					num = c-'0';
					break;
				}
				maze[h][w] = num;
			}
		}
		
//		for(int h=0; h<H; h++) {
//			for(int w=0; w<W; w++) {
//				System.out.print(maze[h][w]+" ");
//			}
//			System.out.println();
//		}
		
		min = Integer.MAX_VALUE;
		
		bfs();
		
		if(min == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
	}
	
	private static void bfs() {
		
		pq = new PriorityQueue<>();
		pq.offer(user);
		
		visited[user.i][user.j] = true;
		
		while(!pq.isEmpty()) {
			
			Point cur = pq.poll();
			
			if(visited[cur.i][cur.j]) continue;
			visited[cur.i][cur.j] = true;
			
			for(int d=0; d<4; d++) {
				Check(d, cur);
			}
		}
	}
	
	
	private static void Check(int idx, Point point) { //i,j를 시작으로 di,dj의 idx방향으로 이동
		//쭉 가면 절벽인지 아닌지 체크하는 메소드
		int i = point.i;
		int j = point.j;
		int cnt = 0;
		
		while(true) {
			//-1은 구멍, 10은 돌, 100은 출구
			i+=di[idx];
			j+=dj[idx];
			
			if(i<0 || j<0 || i>=H || j>=W) return;
			if(maze[i][j] == -1) return;
			if(maze[i][j]==10) {
				if(i-di[idx]!=point.i && j-dj[idx]!=point.j) return;
				pq.offer(new Point(i-di[idx], j-dj[idx], point.cnt+cnt));
				return;
			}
			if(maze[i][j] == 100) {
				min = Math.min(min, point.cnt+cnt);
				return;
			}
			cnt+=maze[i][j];
		}
	}
	
	static class Point implements Comparable<Point>{
		int i;
		int j;
		int cnt;
		public Point(int i, int j, int cnt) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Point o) {
			return this.cnt - o.cnt;
		}
	}
}
