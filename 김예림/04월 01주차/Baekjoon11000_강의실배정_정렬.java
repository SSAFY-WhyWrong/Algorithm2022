package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon11000_강의실배정_정렬 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Lecture[] room = new Lecture[N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			
			int s = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			
			room[i] = new Lecture(s, t);
		}
		Arrays.sort(room);
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(); //종료시간만 저장
		pq.offer(room[0].end); //시작시간과 끝시간이 가장 빠른 강의가 마치는 시간
		
		for(int i=0; i<N; i++) {
			System.out.println(room[i].start+","+room[i].end);
		}
		
		for(int i=1; i<N; i++) {
			
			//순서대로 있는 배열의 시작시간보다 pq에 들어간 강의가 일찍 마치면 
			//같은 강의실을 사용할 수 있기 때문에 pq에서 지운다
			if(pq.peek() <= room[i].start) {
				System.out.println(pq.peek()+"이 "+i+"번째 강의의 시작시간보다 빠르다 (빨리 끝남)");
				//배정되지 않은 강의의 시작시간이 pq의 종료시간보다 빠르면 같은 강의실 사용 가능
				pq.poll();
			}
			
			System.out.println(room[i].end+"를 pq에 왜...넣어..?");
			//같은 강의실을 사용하는 경우와 아닌 경우 둘 다 강의실 배정을 위해 end값 저장
			//여기 잘 모르겠다
			pq.offer(room[i].end);
			
		}
		System.out.println(pq.size());
		
	}
	static class Lecture implements Comparable<Lecture>{
		int start;
		int end;
		public Lecture(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Lecture o) {
			if(this.start == o.start) return this.end-o.end;
			return this.start-o.start;
		}
	}
}
