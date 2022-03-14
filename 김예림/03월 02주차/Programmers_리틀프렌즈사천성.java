package AlgorithmStudy;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Programmers_리틀프렌즈사천성 {
	public static void main(String[] args) {
//		int m = 3;
//		int n = 3; 
//		String[] board = {"DBA", "C*A", "CDB"};
//		int m = 2;
//		int n = 4; 
//		String[] board = {"NRYN", "ARYA"};
		int m = 4;
		int n = 4; 
		String[] board = {".ZI.", "M.**", "MZU.", ".IU."};
//		int m = 2;
//		int n = 2; 
//		String[] board = {"AB", "BA"};
//		
		
		System.out.println(Solution(m,n,board));
	}
	static char[][] game;
	static int[][] alphabetCheck;
	static PriorityQueue<Tile> pq;
	static PriorityQueue<Tile> never;
	static int N,M;
	static int[] di = {0,0,-1,1};
	static int[] dj = {-1,1,0,0};
	static StringBuilder sb;
	
	private static String Solution(int m, int n, String[] board) {
		
		N = n;
		M = m;
		
		sb = new StringBuilder();
		game = new char[m][n];
		pq = new PriorityQueue<>();
		never = new PriorityQueue<>();
		alphabetCheck = new int[91][3]; //65~90까지
		for(int i=0; i<m; i++) {
			String str = board[i];
			for(int j=0; j<n; j++) {
				char c = str.charAt(j);
				game[i][j] = c;
				
				int tmp = (int)c;
				if(alphabetCheck[tmp][0]==0) {
					alphabetCheck[tmp][0] = 1;
					alphabetCheck[tmp][1] = i;
					alphabetCheck[tmp][2] = j;
				}
			}
		}
		for(int i=65; i<91; i++) {
			if(alphabetCheck[i][0]==1) {
				int x = alphabetCheck[i][1];
				int y = alphabetCheck[i][2];
				
				if(!bfs(x, y)) pq.add(new Tile(x, y, game[x][y]));
				else {
					if(!pq.isEmpty()) check();
				}
			}
		}
		if(!pq.isEmpty()) sb.append("IMPOSSIBLE");
		
		return sb.toString();
	}
	private static void check(){
		
		while(!pq.isEmpty()) {
			
			Tile cur = pq.poll();
			
			if(bfs(cur.i,cur.j)) {
				while(!never.isEmpty()) {
					Tile next = never.poll();
					pq.offer(next);
				}
				check();
			}
			else never.offer(new Tile(cur.i, cur.j, cur.val));
		}
		while(!never.isEmpty()) {
			Tile next = never.poll();
			pq.offer(next);
		}
		
	}
	
	private static boolean bfs(int i, int j) {
	
		Queue<Tile> q = new LinkedList<>();
		q.offer(new Tile(i, j, game[i][j]));
		
		boolean[][] visited = new boolean[M][N];
		
		int cnt = 0;
		while(!q.isEmpty()) {
			
			Tile cur = q.poll();
			visited[cur.i][cur.j] = true;
			
			if(cnt>0 && cur.val == game[i][j]) {
				sb.append(game[i][j]);
				game[i][j] = '.';
				game[cur.i][cur.j] = '.';
				return true;
			}
			
			for(int d=0; d<4; d++) {
				int ni = cur.i + di[d];
				int nj = cur.j + dj[d];
				if(ni>=0 && ni<M && nj>=0 && nj<N) {
					if((game[ni][nj]=='.' || game[ni][nj]==game[i][j]) && !visited[ni][nj]) {
						q.offer(new Tile(ni, nj, game[ni][nj]));
					}
				}
			}
			cnt++;
		}
		return false;
	}
	
	private static void printGameBoard() {
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(game[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	
	static class Tile implements Comparable<Tile>{
		int i;
		int j;
		char val;
		public Tile(int i, int j, char val) {
			this.i = i;
			this.j = j;
			this.val = val;
		}
		@Override
		public int compareTo(Tile o) {
			return this.val - o.val;
		}
	}
}
