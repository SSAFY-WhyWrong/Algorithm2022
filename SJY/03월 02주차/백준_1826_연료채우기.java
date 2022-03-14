import java.util.*;
import java.io.*;

public class 백준_1826_연료채우기 {
	
	static class Station implements Comparable<Station> {
		int distance;
		int gas;
		
		public Station(int distance, int gas) {
			this.distance = distance;
			this.gas = gas;
		}
		
		@Override
		public int compareTo(Station o) {
			return this.distance - o.distance;
		}

		@Override
		public String toString() {
			return "Station [distance=" + distance + ", gas=" + gas + "]";
		}
		
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		Station[] stations = new Station[N];
		StringTokenizer st;
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(bf.readLine());
			stations[i] = new Station(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		st = new StringTokenizer(bf.readLine());
		int L = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		Arrays.sort(stations);

		PriorityQueue<Station> pq = new PriorityQueue<>(new Comparator<Station>() {

			@Override
			public int compare(Station o1, Station o2) {
				return o2.gas - o1.gas;
			}
		});
		int idx = 0, cnt = 0;
		while(P < L) {
			while(idx < N && P >= stations[idx].distance) {
				pq.add(stations[idx]);
				idx++;
			}
			
			if(pq.isEmpty()) {
				cnt = -1;
				break;
			}
			
			cnt++;
			P += pq.poll().gas;
		}
		
		System.out.println(cnt);
	}

}
