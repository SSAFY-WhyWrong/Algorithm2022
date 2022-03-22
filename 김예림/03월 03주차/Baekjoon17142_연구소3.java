package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon17142_연구소3 {
	
	static int N, M, max, min, size, tot;
	static int[][] lab;
	static int[] selected;
	static Virus[] viruses;
	static boolean[][] visited;
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		lab = new int[N][N];
		tot = 0;
		Queue<Virus> q = new LinkedList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
				
				if(lab[i][j] == 2) {
					q.offer(new Virus(i, j, 0));
					lab[i][j] = -2;
				}
				else if(lab[i][j] == 1) lab[i][j] = -1;
				else tot++;
			}
		}
		
		size = q.size();
		viruses = new Virus[size];
		int idx = 0;
		while(!q.isEmpty()) {
			Virus cur = q.poll();
			viruses[idx++] = new Virus(cur.i, cur.j,0);
		}
		min = Integer.MAX_VALUE;
		selected = new int[M];
		
		if(tot == 0) min = 0;
		else Comb(0,0);
		
		if(min == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
		
	}
	
	private static void Comb(int cnt, int start) {
		
		if(cnt == M) {
//			System.out.println(Arrays.toString(selected));
			bfs();
			return;
		}
		
		for(int i=start; i<size; i++) {
			selected[cnt] = i;
			Comb(cnt+1, i+1);
		}
	}
	
	private static void bfs() {
		
		visited = new boolean[N][N];
		
		Queue<Virus> bfsQ = new LinkedList<>();
		for(int i=0; i<M; i++) {
			bfsQ.offer(new Virus(viruses[selected[i]].i, viruses[selected[i]].j,0));
			visited[viruses[selected[i]].i][viruses[selected[i]].j] = true;
			
//			System.out.println(viruses[selected[i]].i+","+viruses[selected[i]].j+"에 있는 애 선택");
		}
		
		max = 0;
		int cntVirus = 0;
		while(!bfsQ.isEmpty()) {
			
			Virus cur = bfsQ.poll();
			if(lab[cur.i][cur.j]==0) {
				cntVirus++;
				max = Math.max(max, cur.cnt);
			}
			//cntVirus++;
			
			for(int d=0; d<4; d++) {
				int ni = cur.i + di[d];
				int nj = cur.j + dj[d];
				if(ni>=0 && ni<N && nj>=0 && nj<N && lab[ni][nj]!=-1 && !visited[ni][nj]) {
					bfsQ.offer(new Virus(ni, nj, cur.cnt+1));
					visited[ni][nj] = true;
					
				}
			}
		}
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				if(lab[i][j]==-1) System.out.print("- ");
//				else if(lab[i][j] == -2) System.out.print("* ");
//				else System.out.print(lab[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		if(cntVirus == tot)	min = Math.min(min, max);
	}
	
	
	static class Virus{
		int i;
		int j;
		int cnt;
		public Virus(int i, int j, int cnt) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
	}
}
/*
4 4
1 1 1 1
1 2 2 1
1 2 2 1
1 1 1 1
0

5 1
0 2 2 2 2
0 1 2 2 2
0 1 2 2 2
0 1 2 2 2
0 1 2 2 1
5

9 1
0 2 2 2 2 2 2 2 0
1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1
4

*/
