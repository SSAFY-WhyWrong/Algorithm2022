import java.util.*;
import java.io.*;

public class 백준_1261_알고스팟 {
	
	static class Point implements Comparable<Point> {
		int x;
		int y;
		int cnt;
		
		public Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Point o) {
			if(this.cnt == o.cnt && this.x == this.y) {
				return o.y - this.y;
			} else if(this.cnt == o.cnt) {
				return o.x - this.x;
			}
			
			return this.cnt - o.cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int N = sc.nextInt();
		int[][] board = new int[N][M];
		int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};
		
		for(int i=0;i<N;i++) {
			String tmp = sc.next();
			for(int j=0;j<M;j++) {
				board[i][j] = tmp.charAt(j) - '0';
			}
		}
		
		boolean[][] visit = new boolean[N][M];
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.add(new Point(0, 0, 0));
		visit[0][0] = true;
		
		while(!pq.isEmpty()) {
			Point now = pq.poll();

			if(now.x == N-1 && now.y == M-1) {
				System.out.println(now.cnt);
				break;
			}
			
			for(int d=0;d<4;d++) {
				int nx = now.x + dx[d];
				int ny = now.y + dy[d];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if(!visit[nx][ny] && board[nx][ny] == 1) {
					visit[nx][ny] = true;
					pq.add(new Point(nx, ny, now.cnt + 1));
				} else if(!visit[nx][ny]) {
					visit[nx][ny] = true;
					pq.add(new Point(nx, ny, now.cnt));
				}
			}
		}
		
	}

}
