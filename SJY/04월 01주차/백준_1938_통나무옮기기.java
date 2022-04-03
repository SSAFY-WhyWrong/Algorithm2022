import java.util.*;
import java.io.*;

public class 백준_1938_통나무옮기기 {
	
	static int N;
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	static char[][] board;
	static boolean[][][] visit;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		board = new char[N][N];
		visit = new boolean[N][N][2]; // 0 : 가로 , 1 : 세로
		int[][] loc = new int[3][2];
		
		for(int i=0, x=0;i<N;i++) {
			String tmp = bf.readLine();
			for(int j=0;j<N;j++) {
				if(tmp.charAt(j) == 'B') {
					board[i][j] = '0';
					loc[x][0] = i;
					loc[x++][1] = j;
				} else {
					board[i][j] = tmp.charAt(j);
				}
			}
		}
		
		int ndir = dir(new Location(loc[0][0], loc[0][1], loc[1][0], loc[1][1], loc[2][0], loc[2][1]));
		visit[loc[0][0]][loc[0][1]][ndir] = true;
		visit[loc[1][0]][loc[1][1]][ndir] = true;
		visit[loc[2][0]][loc[2][1]][ndir] = true;
		
		Location start = new Location(loc[0][0], loc[0][1], loc[1][0], loc[1][1], loc[2][0], loc[2][1]);
		Queue<Location> q = new LinkedList<>();
		q.add(start);
		int cnt = 0;
		boolean check = false;
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for(int s=0;s<size;s++) {
				Location now = q.poll();
//				System.out.println(now.toString());
				if(board[now.x1][now.y1] == 'E' && board[now.x2][now.y2] == 'E' && board[now.x3][now.y3] == 'E') {
					check = true;
					break;
				}
				
				Location u = up(now);
				if(u != null) {
					q.add(u);
//					System.out.println("up = " + u.toString());
				}
				
				Location d = down(now);
				if(d != null) {
//					System.out.println("down = " + d.toString());
					q.add(d);
				}
				
				Location l = left(now);
				if(l != null) {
					q.add(l);
//					System.out.println("left = " + l.toString());
				}
				
				Location r = right(now);
				if(r != null) {
//					System.out.println("right = " + r.toString());
					q.add(r);
				}
				
				Location t = turn(now);
				if(t != null) {
//					System.out.println("turn = " + t.toString());
					q.add(t);
				}
			}
//			System.out.println();
			if(check) break;
			cnt++;
		}
		
		if(!check) cnt = 0;
		System.out.println(cnt);
	}
	
	private static Location turn(Location now) {
		int d = dir(now);
		int start, end;
		
		// d == 0 -> 상하
		if(d == 0) {
			start = 0;
			end = 2;
		} else {
			start = 2;
			end = 4;
		}
		
		int nx1, nx2, nx3, ny1, ny2, ny3;
		
		for(int i=start;i<end;i++) {
			nx1 = now.x1 + dx[i]; ny1 = now.y1 + dy[i];
			nx2 = now.x2 + dx[i]; ny2 = now.y2 + dy[i];
			nx3 = now.x3 + dx[i]; ny3 = now.y3 + dy[i];
			
			if(nx1 < 0 || nx1 >= N || ny1 < 0 || ny1 >= N || nx2 < 0 || nx2 >= N
					|| ny2 < 0 || ny2 >= N || nx3 < 0 || nx3 >= N || ny3 < 0 || ny3 >= N) return null;
			
			if(board[nx1][ny1] == '1' || board[nx2][ny2] == '1' || board[nx3][ny3] == '1') return null;
		}
		
		nx1 = now.x2 + dx[start]; ny1 = now.y2 + dy[start];
		nx2 = now.x2; ny2 = now.y2;
		nx3 = now.x2 + dx[start+1]; ny3 = now.y2 + dy[start+1];
		d = (d == 0) ? 1:0;
		if(visit[nx1][ny1][d] && visit[nx2][ny2][d] && visit[nx3][ny3][d]) return null;
		
		visit[nx1][ny1][d] = true;
		visit[nx2][ny2][d] = true;
		visit[nx3][ny3][d] = true;
		
		return new Location(nx1, ny1, nx2, ny2, nx3, ny3);
	}

	private static Location right(Location now) {
		int nx1 = now.x1 + dx[3], ny1 = now.y1 + dy[3];
		int nx2 = now.x2 + dx[3], ny2 = now.y2 + dy[3];
		int nx3 = now.x3 + dx[3], ny3 = now.y3 + dy[3];
		int d = dir(now);
		
		if(nx1 < 0 || nx1 >= N || ny1 < 0 || ny1 >= N || nx2 < 0 || nx2 >= N
				|| ny2 < 0 || ny2 >= N || nx3 < 0 || nx3 >= N || ny3 < 0 || ny3 >= N) return null;
		
		if(board[nx1][ny1] == '1' || board[nx2][ny2] == '1' || board[nx3][ny3] == '1') return null;
		if(visit[nx1][ny1][d] && visit[nx2][ny2][d] && visit[nx3][ny3][d]) return null;
		
		visit[nx1][ny1][d] = true;
		visit[nx2][ny2][d] = true;
		visit[nx3][ny3][d] = true;
		
		return new Location(nx1, ny1, nx2, ny2, nx3, ny3);
	}

	private static Location left(Location now) {
		int nx1 = now.x1 + dx[2], ny1 = now.y1 + dy[2];
		int nx2 = now.x2 + dx[2], ny2 = now.y2 + dy[2];
		int nx3 = now.x3 + dx[2], ny3 = now.y3 + dy[2];
		int d = dir(now);
		
		if(nx1 < 0 || nx1 >= N || ny1 < 0 || ny1 >= N || nx2 < 0 || nx2 >= N
				|| ny2 < 0 || ny2 >= N || nx3 < 0 || nx3 >= N || ny3 < 0 || ny3 >= N) return null;
		
		if(board[nx1][ny1] == '1' || board[nx2][ny2] == '1' || board[nx3][ny3] == '1') return null;
		if(visit[nx1][ny1][d] && visit[nx2][ny2][d] && visit[nx3][ny3][d]) return null;
		
		visit[nx1][ny1][d] = true;
		visit[nx2][ny2][d] = true;
		visit[nx3][ny3][d] = true;
		
		return new Location(nx1, ny1, nx2, ny2, nx3, ny3);
	}

	private static Location down(Location now) {
		int nx1 = now.x1 + dx[1], ny1 = now.y1 + dy[1];
		int nx2 = now.x2 + dx[1], ny2 = now.y2 + dy[1];
		int nx3 = now.x3 + dx[1], ny3 = now.y3 + dy[1];
		int d = dir(now);
		
		if(nx1 < 0 || nx1 >= N || ny1 < 0 || ny1 >= N || nx2 < 0 || nx2 >= N
				|| ny2 < 0 || ny2 >= N || nx3 < 0 || nx3 >= N || ny3 < 0 || ny3 >= N) return null;
		
		if(board[nx1][ny1] == '1' || board[nx2][ny2] == '1' || board[nx3][ny3] == '1') return null;
		if(visit[nx1][ny1][d] && visit[nx2][ny2][d] && visit[nx3][ny3][d]) return null;
		
		visit[nx1][ny1][d] = true;
		visit[nx2][ny2][d] = true;
		visit[nx3][ny3][d] = true;
		
		return new Location(nx1, ny1, nx2, ny2, nx3, ny3);
	}

	private static Location up(Location now) {
		int nx1 = now.x1 + dx[0], ny1 = now.y1 + dy[0];
		int nx2 = now.x2 + dx[0], ny2 = now.y2 + dy[0];
		int nx3 = now.x3 + dx[0], ny3 = now.y3 + dy[0];
		int d = dir(now);
		
		if(nx1 < 0 || nx1 >= N || ny1 < 0 || ny1 >= N || nx2 < 0 || nx2 >= N
				|| ny2 < 0 || ny2 >= N || nx3 < 0 || nx3 >= N || ny3 < 0 || ny3 >= N) return null;
		
		if(board[nx1][ny1] == '1' || board[nx2][ny2] == '1' || board[nx3][ny3] == '1') return null;
		if(visit[nx1][ny1][d] && visit[nx2][ny2][d] && visit[nx3][ny3][d]) return null;
		
		visit[nx1][ny1][d] = true;
		visit[nx2][ny2][d] = true;
		visit[nx3][ny3][d] = true;
		
		return new Location(nx1, ny1, nx2, ny2, nx3, ny3);
	}

	public static int dir(Location location) {
		if(location.x1 + 1 == location.x2) {
			return 1;
		}
		return 0;
	}
	
	static class Location {
		int x1, y1;
		int x2, y2;
		int x3, y3;
		
		public Location(int x1, int y1, int x2, int y2, int x3, int y3) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.x3 = x3;
			this.y3 = y3;
		}

		@Override
		public String toString() {
			return "Location [x1=" + x1 + ", y1=" + y1 + ", x2=" + x2 + ", y2=" + y2 + ", x3=" + x3 + ", y3=" + y3
					+ "]";
		}
		
	}
}
