package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon1941_소문난칠공주 {
	
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	static char[][] students;
	static boolean[][] check;
	static int[] selectNum;
	static boolean[] selected;
	static int tot;
	
	public static void main(String[] args) throws IOException,NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		students = new char[5][5];
		for(int i=0; i<5; i++) {
			students[i] = br.readLine().toCharArray();
		}
		
		selected = new boolean[7];
		selectNum = new int[7];
		tot = 0;
		
		Comb(0,0);

		System.out.println(tot);
	}
	
	private static void Comb(int cnt, int start) {
		
		if(cnt==7) {
			check = new boolean[5][5];
			checkStudents();
			return;
		}
		
		for(int i=start; i<25; i++) {
			selectNum[cnt] = i;
			Comb(cnt+1, i+1);
			
		}
	}
	
	private static void checkStudents() {
		
		int i=0, j=0;
		for(int n=0; n<7; n++) {
			i = selectNum[n]/5;
			j = selectNum[n]%5;
			
			check[i][j] = true;
		}
		Bfs(i,j);
	}
	
	private static void Bfs(int i, int j) {
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(i, j));
		check[i][j] = false;
		
		int cnt = 1, sCnt = 0;
		if(students[i][j]=='S') sCnt++;
		
		while(!q.isEmpty()) {
			
			Point cur = q.poll();
			
			for(int d=0; d<4; d++) {
				int ni = cur.i + di[d];
				int nj = cur.j + dj[d];
				if(ni>=0 && ni<5 && nj>=0 && nj<5 && check[ni][nj]) {
					q.offer(new Point(ni, nj));
					check[ni][nj] = false;
					cnt++;
					
					if(students[ni][nj]=='S') sCnt++;
				}
			}
			
		}
		if(cnt==7 && sCnt>=4) tot++;
		
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
