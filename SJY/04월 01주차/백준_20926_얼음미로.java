import java.util.*;
import java.io.*;

public class 백준_20926_얼음미로 {
	
	static class Point implements Comparable<Point> {
		int x, y;
		int time;
		
		public Point(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
		
		@Override
		public int compareTo(Point o) {
			return this.time - o.time;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", time=" + time + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int endX = 0, endY = 0;
		int[][] board = new int[H][W];
		boolean[][] visit = new boolean[H][W];
		PriorityQueue<Point> pq = new PriorityQueue<>();
		
		for(int i=0;i<H;i++) {
			String tmp = bf.readLine();
			for(int j=0;j<W;j++) {
				char c = tmp.charAt(j);
				
				if(c == 'E') {
					endX = i; endY = j;
					board[i][j] = 0;
				} else if(c == 'T') {
					board[i][j] = 0;
					pq.add(new Point(i, j, 0));
				} else if(c == 'R') {
					board[i][j] = -1;
				} else if(c == 'H') {
					board[i][j] = -2;
				} else {
					board[i][j] = c - '0';
				}
			}
		}

		int answer = Integer.MAX_VALUE;
		int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};
		while(!pq.isEmpty()) {
			Point now = pq.poll();
			
			if(visit[now.x][now.y]) continue;
			visit[now.x][now.y] = true;
			
			for(int d=0;d<4;d++) {
				int nx = now.x;
				int ny = now.y;
				int time = now.time;

				while(true) {
					nx += dx[d];
					ny += dy[d];
					
					if(nx < 0 || nx >= H || ny < 0 || ny >= W || board[nx][ny] == -2) break;
					if(board[nx][ny] == -1) {
						pq.add(new Point(nx - dx[d], ny - dy[d], time));
							
						break;
					} else if(nx == endX && ny == endY) {
						answer = Math.min(answer, time);
						break;
					}
					
					time += board[nx][ny];
				
				}
			}
		}
		
		if(answer == Integer.MAX_VALUE) answer = -1;
		System.out.println(answer);
	}

}
