package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon1261_알고스팟 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean[][] visited = new boolean[M][N];
		int[][] map = new int[M][N];
		int[][] dis = new int[M][N];
		for(int i=0; i<M; i++) {
			String s = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = s.charAt(j)-'0';
				dis[i][j] = Integer.MAX_VALUE;
			}
		}
		
		int[] di = {-1,1,0,0};
		int[] dj = {0,0,-1,1};
		
		dis[0][0] = 0;
		
		PriorityQueue<Algo> pq = new PriorityQueue<>();
		pq.offer(new Algo(0, 0, 0));
		
		while(!pq.isEmpty()) { //문제점 1 : for(int i=0; i<10000; i++)로 돌렸을 때 바로 틀렸음
			//정점 방문을 한 번만 하는 것이 아니기 때문에 10000번을 넘어서 그런건가?
			
			Algo cur = pq.poll();
			
			visited[cur.i][cur.j] = true;
			
			if(cur.i == M-1 && cur.j == N-1) break;
			
			for(int d=0; d<4; d++) {
				int ni = cur.i + di[d];
				int nj = cur.j + dj[d];
				if(ni>=0 && ni<M && nj>=0 && nj<N && !visited[ni][nj]) {
					if(cur.cnt + map[ni][nj] < dis[ni][nj]) { 
						//문제점 2 : pq.offer을 할 경우 조건 없이 방문정점에서 4곳을 다 넣으면 메모리 초과
						//if문을 통해서 조건을 건 뒤 pq에 들어가는 정점을 줄였다.
						dis[ni][nj] = cur.cnt + map[ni][nj];
						pq.offer(new Algo(ni, nj, cur.cnt+map[ni][nj]));
					}		
				}
			}
		}
		System.out.println(dis[M-1][N-1]);
	}
	
	static class Algo implements Comparable<Algo>{
		int i;
		int j;
		int cnt;
		public Algo(int i, int j, int cnt) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Algo o) {
			return this.cnt-o.cnt;
		}
	}
}
/*
5 5
01000
01010
00010
11110
00000

5 4
01000
01010
00010
11110

3 1
010

3 5
001
101
001
011
000

2 2
01
10

1

23 3
00101110111000000110100
01001111101010010001100
11000001010110010110000

5
*/
