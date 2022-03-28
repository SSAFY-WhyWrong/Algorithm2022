package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon20058_마법사상어와파이어스톰 {

	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	static int N, Q, L, size, part, tot, big;
	static int[][] ice, lose;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		size = (int) Math.pow(2, N);
		ice = new int[size][size];
		for(int i=0; i<size; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<size; j++) {
				ice[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		lose = new int[size][size]; //얼음 몇 개가 인접해 있는지 검사하기
		Search();
		//print(lose);
		
		st = new StringTokenizer(br.readLine()," ");
		for(int q=0; q<Q; q++) {
			L = Integer.parseInt(st.nextToken());
			part = (int)Math.pow(2, L);

			for(int i=0; i<size; i+=part) {
				for(int j=0; j<size; j+=part) {
					//System.out.println(i+","+j+"를 출발점으로 회전시키기");
					make(i,j);
				}
			}
			Search();
			
			//print(ice);
			//System.out.println();
			//print(lose);
			melt();
//			System.out.println("=============================");
		}

		StringBuilder sb = new StringBuilder();
		
		tot = 0;
		big = 0;
		visited = new boolean[size][size];
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(ice[i][j]>0 && !visited[i][j]) {
					big = Math.max(big, bfs(i,j));
				}
			}
		}
		sb.append(tot+"\n"+big);
		System.out.println(sb.toString());

	}
	
	
	private static int bfs(int i, int j) {
		
		Queue<Point> q = new LinkedList<>();
		visited[i][j] = true;
		q.offer(new Point(i, j));
		
		int cnt = 0;
		while(!q.isEmpty()) {
			
			Point cur = q.poll();
			
			tot += ice[cur.i][cur.j];
			cnt++;
			
			for(int d=0; d<4; d++) {
				int ni = cur.i+di[d];
				int nj = cur.j+dj[d];
				if(ni>=0 && ni<size && nj>=0 && nj<size && ice[ni][nj]>0 && !visited[ni][nj]) {
					q.offer(new Point(ni, nj));
					visited[ni][nj] = true;
				}
			}
		}
		return cnt;
		
	}
	
	
	private static void melt() {
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(lose[i][j]<3 && ice[i][j]>0) ice[i][j]--;
			}
		}
		
	}
	
	private static void make(int row, int column) {
		
		int[][] next = new int[part][part];
		
		for(int i=0; i<part; i++) {
			for(int j=0; j<part; j++) {
				next[i][j] = ice[i+row][j+column];
			}
		}
		next = rotate(next);
		
		for(int i=0; i<part; i++) {
			for(int j=0; j<part; j++) {
				ice[i+row][j+column] = next[i][j];
			}
		}
		
	}

	private static void print(int[][] cur) {
		for(int i=0; i<cur.length; i++) {
			for(int j=0; j<cur[i].length; j++) {
				System.out.print(cur[i][j]+" ");
			}
			System.out.println();
		}
	}


	private static void Search() {
		
		
		for(int i=0; i<size; i++) {
			Arrays.fill(lose[i], 0);
			for(int j=0; j<size; j++) {
				for(int d=0; d<4; d++) {
					int ni = i+di[d];
					int nj = j+dj[d];
					if(ni>=0 && ni<size && nj>=0 && nj<size && ice[ni][nj]>0) lose[i][j]++;
				}
			}
		}
	}

	
	public static int[][] rotate(int[][] cur) {
		int[][] next = new int[cur[0].length][cur.length];

		for(int i = 0; i < cur[0].length; i++) {
			for(int j = 0; j < cur.length; j++) {
				next[i][j] = cur[cur.length - 1 - j][i];
			}
		}
		return next;
	}
	
	static class Point{
		int i;
		int j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
