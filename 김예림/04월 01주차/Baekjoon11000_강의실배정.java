package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon11000_강의실배정 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Class> pq = new PriorityQueue<>();
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			
			int s = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
					
			pq.add(new Class(s, 1));
			pq.add(new Class(t, -1));
		}
		
		int max = 0;
		int classroom = 0;
		while(!pq.isEmpty()) {
			Class cur = pq.poll();
			classroom += cur.inout;
			
			max = Math.max(max, classroom);
		}
		System.out.println(max);
		
	}
	static class Class implements Comparable<Class>{
		int time;
		int inout;
		public Class(int time, int inout) {
			this.time = time;
			this.inout = inout;
		}
		@Override
		public int compareTo(Class o) {
			if(this.time==o.time) return this.inout-o.inout;
			return this.time-o.time;
		}
	}
}
/*
5
1 7
2 3
3 4
4 8
7 10

2
*/
