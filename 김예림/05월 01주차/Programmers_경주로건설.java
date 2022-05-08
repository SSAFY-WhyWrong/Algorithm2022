package AlgorithmStudy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Programmers_경주로건설 {
	public static void main(String[] args) {
		
//		int[][] board = {{0,0,0},{0,0,0},{0,0,0}};
//		int[][] board = {{0,0,0,0,0,0,0,1},
//						{0,0,0,0,0,0,0,0},
//						{0,0,0,0,0,1,0,0},
//						{0,0,0,0,1,0,0,0},
//						{0,0,0,1,0,0,0,1},
//						{0,0,1,0,0,0,1,0},
//						{0,1,0,0,0,1,0,0},
//						{1,0,0,0,0,0,0,0}};
//		int[][] board = {{0,0,1,0},{0,0,0,0},{0,1,0,1},{1,0,0,0}};
		int[][] board = {{0,0,0,0,0,0},
						{0,1,1,1,1,0},
						{0,0,1,0,0,0},
						{1,0,0,1,0,1},
						{0,1,0,0,0,1},
						{0,0,0,0,0,0}};
		
		
		System.out.println(solution(board));
		
	}
	static int[] di = {-1,1,0,0}; //위 아래 왼 오
	static int[] dj = {0,0,-1,1};
	static int[][] map;
	static int[][][] minValue; //i,j,dir
	static boolean[][] visited;
	static int min, size;
	
	public static int solution(int[][] board) {
		
		size = board.length;
		map = new int[size][size];
		minValue = new int[size][size][4];
		visited = new boolean[size][size];
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				map[i][j] = board[i][j];
				Arrays.fill(minValue[i][j], Integer.MAX_VALUE);
			}
		}
		min = Integer.MAX_VALUE;
		
		bfs(0,0);
		
        return min;
    }
	public static void bfs(int i, int j) {
		
		Queue<Point> q = new LinkedList<>();
		
		q.offer(new Point(i, j, -1, 0));
		visited[0][0] = true;
		minValue[0][0][1] = 0;
		minValue[0][0][3] = 0;
		
		while(!q.isEmpty()) {
			
			Point cur = q.poll();
			if(cur.i == size-1 && cur.j == size-1) {
				min = Math.min(cur.cnt, min);
			}
			
			for(int d=0; d<4; d++) {
				int ni = cur.i + di[d];
				int nj = cur.j + dj[d];
				if(ni>=0 && ni<size && nj>=0 && nj<size && map[ni][nj]==0) {
					
					int money = cur.cnt;
					if(d == cur.dir || cur.dir == -1) money += 100;
					else money += 600;
					
					if(!visited[ni][nj] || minValue[ni][nj][d] > money) {
						visited[ni][nj] = true;
						minValue[ni][nj][d] = money;
						q.offer(new Point(ni, nj, d, money));
					}
					
				}			
			}
		}
	}
	static class Point{
		int i;
		int j;
		int dir;
		int cnt;
		public Point(int i, int j, int dir, int cnt) {
			super();
			this.i = i;
			this.j = j;
			this.dir = dir;
			this.cnt = cnt;
		}
	}
	
	
	public static void dfs(int i, int j, int cnt, int dir) {
		
		if(cnt>min) return;
		if(i==size-1 && j==size-1) {
			min = Math.min(cnt, min);
			return;
		}
		//방향의 인덱스가 같으면 직선도로, 아닌 경우 전부 코너
		for(int d=0; d<4; d++) {
            int curDir = (d+dir)%4;
			int ni = i + di[curDir];
			int nj = j + dj[curDir];
			if(ni>=0 && ni<size && nj>=0 && nj<size && map[ni][nj]==0 && !visited[ni][nj]) {
				visited[ni][nj] = true;
				
				int money = 0;
				if(curDir != dir) money = 600;
				else money = 100;
				
				dfs(ni,nj,cnt+money,curDir);
				
				visited[ni][nj] = false;
			}			
		}
	}
}
