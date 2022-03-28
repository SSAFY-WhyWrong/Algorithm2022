package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon20440_니가싫어싫어 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); //모기 마릿수
		
		PriorityQueue<Mosq> pq = new PriorityQueue<>();
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int enter = Integer.parseInt(st.nextToken());
			int leave = Integer.parseInt(st.nextToken());
			
			pq.add(new Mosq(enter, 1));
			pq.add(new Mosq(leave, -1));
		}
		
		PriorityQueue<Range> rangePQ = new PriorityQueue<>();
		int max = 0, start = 0, end = 0, now = 0, pre = -1;
		boolean flag = false;
		while(!pq.isEmpty()) {
			
			Mosq mos = pq.poll();
			
			if(now==max && mos.cnt==-1) {
				end = mos.time;
				rangePQ.add(new Range(start, end, max));
			}
			 
			now += mos.cnt;
			
			if(now>=max && mos.cnt==1) {
				max = now;
				start = mos.time;
			}
		}
		StringBuilder sb = new StringBuilder();
		
		start=0; end=0;
		while(!rangePQ.isEmpty()) {
			
			Range cur = rangePQ.poll();
			
			if(max == cur.cnt) { 
				if(start>0 && end==cur.start) end = cur.end; //이전 끝점과 출발점이 같으면 범위 늘려주기
				else if(start==0 && end==0) {
					start = cur.start;
					end = cur.end;
				}
			}
			
		}
		
		sb.append(max+"\n"+start+" "+end);
		System.out.println(sb.toString());
	}
	
	static class Mosq implements Comparable<Mosq>{
		int time;
		int cnt;
		public Mosq(int time, int cnt) {
			this.time = time;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Mosq o) {
			if(this.time==o.time) return this.cnt-o.cnt;
			return this.time - o.time;
		}
	}
	
	static class Range implements Comparable<Range>{
		int start;
		int end;
		int cnt;
		public Range(int start, int end, int cnt) {
			this.start = start;
			this.end = end;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Range o) {
			if(this.cnt == o.cnt) {
				return this.start-o.start;
			}
			return o.cnt-this.cnt;
		}
	}
}
/*
5
2 16
2 4
3 4
8 10
9 10

3
3 4


4
2 16
2 4
4 6
8 10

2
2 6


2
2000000000 2100000000

*/