package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon10282_해킹 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int n = Integer.parseInt(st.nextToken()); //컴퓨터 개수
			int d = Integer.parseInt(st.nextToken()); //의존성 개수
			int c = Integer.parseInt(st.nextToken()); //해킹당한 컴퓨터의 개수
			
			ArrayList<Computer>[] parents = new ArrayList[n+1];
			for(int i=0; i<n+1; i++) {
				parents[i] = new ArrayList<Computer>();
			}
			for(int i=0; i<d; i++) {
				st = new StringTokenizer(br.readLine()," ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				
				parents[b].add(new Computer(a,s));
			}
			
			int[] arrive = new int[n+1]; //도착한 시간 갱신용
			Arrays.fill(arrive, Integer.MAX_VALUE);
			arrive[c] = 0;
			
			Queue<Integer> q = new LinkedList<>();
			q.offer(c);
			
			while(!q.isEmpty()) {
				//하나의 컴퓨터에 의존성 여러 개가 있을 수 있다.
				//s초가 지나면서 의존성이 여러 개일 경우 더 빨리 감염되는 시간을 선택한다.
				int start = q.poll();
				
				if(parents[start].size()>0) {
					for(int i=0; i<parents[start].size(); i++) {
						int next = parents[start].get(i).next;
						int time = parents[start].get(i).time;
						if(time+arrive[start]<arrive[next]) {
							arrive[next] = time+arrive[start];
							q.offer(next);
						}
					}
				}
			}
			int cnt = 0;
			int max = 0;
			for(int i=0; i<n+1; i++) {
				if(arrive[i] != Integer.MAX_VALUE) {
					max = Math.max(max, arrive[i]);
					cnt++;
				}
			}
			sb.append(cnt+" "+max+"\n");
		}
		System.out.println(sb.toString());
	}
	static class Computer{
		int next;
		int time;
		public Computer(int next, int time) {
			this.next = next;
			this.time = time;
		}
	}
}
