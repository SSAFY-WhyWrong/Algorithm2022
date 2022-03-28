import java.util.*;
import java.io.*;

public class 백준_20440_니가싫어 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		Time[] times = new Time[N];
		
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			times[i] = new Time(s, e);
		}
		
		Arrays.sort(times, new Comparator<Time>() {

			@Override
			public int compare(Time o1, Time o2) {
				return o1.start - o2.start;
			}
		});
		
		PriorityQueue<Time> pq = new PriorityQueue<>();
		int size = 0, Tem = 0, Txm = 0;
		
		for(int i=0;i<N;i++) {
			while(!pq.isEmpty() && pq.peek().end <= times[i].start) pq.poll();
			pq.add(times[i]);
			
			if(pq.size() == size && Txm == times[i].start) {
				Txm = pq.peek().end;
			} else if(pq.size() > size) {
				size = pq.size();
				Tem = times[i].start;
				Txm = pq.peek().end;
			}
		}
		
		System.out.println(size);
		System.out.println(Tem + " " + Txm);
		
	}
	
	static class Time implements Comparable<Time> {
		int start;
		int end;
		
		public Time(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Time o) {
			return this.end - o.end;
		}
	}

}
