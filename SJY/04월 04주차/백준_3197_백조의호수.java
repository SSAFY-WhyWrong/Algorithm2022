import java.util.*;
import java.io.*;

public class 백준_3197_백조의호수 {
	
	static int R, C;
	static Point[] swan;
	static char[][] board;
	static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};
	static Queue<Point> ice;
	static Queue<Point> delete;
	
	static class Point {
		int x, y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		ice = new LinkedList<>();
		delete = new LinkedList<>();
		swan = new Point[2];
		
		for(int i=0, s=0;i<R;i++) {
			String tmp = bf.readLine();
			for(int j=0;j<C;j++) {
				board[i][j] = tmp.charAt(j);
				
				if(board[i][j] == 'L') {
					swan[s++] = new Point(i, j);
				} else if(board[i][j] == 'X') {
					ice.add(new Point(i, j));
				}
			}
		}
		
		int day = 0;
		while(true) {
			if(isMeet()) break;
			
			select();
			melting();
			day++;
		}
		
		System.out.println(day);
	}

	private static void melting() {
		while(!delete.isEmpty()) {
			Point now = delete.poll();
			
			board[now.x][now.y] = '.';
		}
	}

	private static void select() {
		int size = ice.size();
		while(size-- > 0) {
			Point now = ice.poll();
			
			boolean check = false;
			for(int d=0;d<4;d++) {
				int nx = now.x + dx[d];
				int ny = now.y + dy[d];
				
				if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
				if(board[nx][ny] == '.') {
					delete.add(now);
					check = true;
					break;
				}
			}
			
			if(!check) ice.add(now);
		}
		
	}

	private static boolean isMeet() {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visit = new boolean[R][C];
		q.add(swan[0]);
		visit[swan[0].x][swan[0].y] = true;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			for(int d=0;d<4;d++) {
				int nx = now.x + dx[d];
				int ny = now.y + dy[d];
				
				if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
				if(nx == swan[1].x && ny == swan[1].y) return true;
				if(visit[nx][ny] || board[nx][ny] == 'X') continue;
				
				visit[nx][ny] = true;
				q.add(new Point(nx, ny));
			}
		}

		return false;
	}

}
