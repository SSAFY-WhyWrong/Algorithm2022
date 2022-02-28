package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon20666_인물이와정수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken()); //총 N마리의 몬스터
		int M = Integer.parseInt(st.nextToken()); //최소 잡아야하는 몬스터 수

		st = new StringTokenizer(br.readLine()," ");

		ArrayList<Rule>[] list = new ArrayList[N+1];
		for(int i=0; i<N+1; i++) {
			list[i] = new ArrayList<>();
		}

		long[] game = new long[N+1]; //난이도 숫자의 범위가 크기 때문에 long으로 변형
		for(int i=1; i<=N; i++) {
			game[i] = Long.parseLong(st.nextToken());
		}
		int p = Integer.parseInt(br.readLine());
		for(int i=0; i<p; i++) {
			st = new StringTokenizer(br.readLine()," ");

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			
			list[a].add(new Rule(a, b, t));
			game[b] += t;
		}
		
		PriorityQueue<Game> pq = new PriorityQueue<>();
		for(int i=1; i<=N; i++) {
			pq.offer(new Game(i, game[i]));
		}
		
		boolean[] complete = new boolean[N+1]; //몬스터 이미 잡은애인지 아닌지 체크하기
		int cnt = 0;
		long ans = 0;
		while(!pq.isEmpty()) {
			Game cur = pq.poll();
			
			int idx = cur.idx;
			long level = cur.level;
			
			if(!complete[idx]) {
				if(!list[idx].isEmpty() || list[idx] != null) {
					int size = list[idx].size();
					for(int s=0; s<size; s++) {
						int b = list[idx].get(s).b;
						int t = list[idx].get(s).t;
						if(!complete[b]) {
							game[b] -= t;
							pq.offer(new Game(b, game[b]));
						}
					}
				}
				complete[idx] = true;
				cnt++;
			}
			ans = Math.max(ans, level);
			
			if(cnt == M) break;
		}
		System.out.println(ans);
	}
	static class Game implements Comparable<Game>{
		int idx;
		long level;
		public Game(int idx, long level) {
			this.idx = idx;
			this.level = level;
		}
		@Override
		public int compareTo(Game o) {
			if(this.level > o.level) return 1;
			else return -1;
		}
	}
	static class Rule{
		int a; //필요한 아이템
		int b; //난이도 조정되는 아이템
		int t; //올라가는 난이도 숫자
		public Rule(int a, int b, int t) {
			this.a = a;
			this.b = b;
			this.t = t;
		}
	}
}
