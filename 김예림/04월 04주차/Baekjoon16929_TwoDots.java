package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon16929_TwoDots {

	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	static int[][] visited;
	static char[][] map;
	static int N, M, starti, startj;
	static boolean answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visited = new int[N][M];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		answer = false;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visited[i][j]==0) {
					dfs(i,j,1);
				}
				if(answer) break;
			}
		}
		if(answer) System.out.println("Yes");
		else System.out.println("No");

	}

	private static void dfs(int i, int j, int size) {

		visited[i][j] = size;
		
		for(int d=0; d<4; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if(ni>=0 && ni<N && nj>=0 && nj<M && map[i][j]==map[ni][nj]) {
				if(visited[ni][nj]==0) 
					dfs(ni,nj,size+1);
				else if(visited[ni][nj]<=visited[i][j]-2 && size>=4) {
					answer = true;
					return;
				}
			}
		}
	}

}
