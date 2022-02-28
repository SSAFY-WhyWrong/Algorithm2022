package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon8980_택배 {
	
	static int N, C, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken()); //마을 수 2<=N<=2000
		C = Integer.parseInt(st.nextToken()); //트럭의 용량 1<=C<=10000
		
		M = Integer.parseInt(br.readLine()); //보내는 박스 정보의 개수 1<=M<=10000
		PriorityQueue<Box> pq = new PriorityQueue<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			
			int send = Integer.parseInt(st.nextToken()); //보내는 마을번호
			int get = Integer.parseInt(st.nextToken()); //받는 마을번호
			int box = Integer.parseInt(st.nextToken()); //보내는 박수 개수
			
			pq.offer(new Box(send, get, box));
		}
		
		int[] deliver = new int[N+1];
		Arrays.fill(deliver, C);
		
		int tot = 0;
		while(!pq.isEmpty()) {
			
			Box cur = pq.poll();
			int start = cur.from;
			int end = cur.to;
			int need = cur.cnt;
			
			for(int j=start; j<end; j++) {
				
				//트럭에 자리가 없는 상태
				if(deliver[j]==0) {
					need = -1;
					break;
				}
				//트럭에 자리는 있지만 한정된 숫자의 자리만 존재
				else if(need > deliver[j]) need = deliver[j];
			}
			//자리가 없어서 택배를 싣지 못한 경우가 아니라면(=택배를 실을 수 있다면)
			if(need != -1) {
				//트럭에 택배가 실리기 때문에 자리를 선점하도록 -로 자리빼기
				System.out.println(start+"부터 "+end+"까지 "+need+"를 배달할 수 있다.");
				for(int j=start; j<end; j++) {
					deliver[j] -= need;
				}
				tot += need;
			}
		}
		System.out.println(tot);
	}
	static class Box implements Comparable<Box>{
		int from;
		int to;
		int cnt;
		public Box(int from, int to, int cnt) {
			this.from = from;
			this.to = to;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Box o) {
			if(this.to == o.to) return o.from - this.from;
			else return this.to - o.to;
			
//			return this.to - o.to;
			
//			if(this.to == o.to) return this.from - o.from;
//			else return this.to - o.to;
		}
	}
}
/*
10 3000
5
8 10 2188
2 10 2840
1 10 2427
7 9 2843
9 10 2154
5154

5 9
5
4 5 2
2 3 5
1 5 9
3 5 7
1 4 1
15


50 100
68
1 50 85
2 49 16
3 48 45
4 47 67
5 46 92
6 45 69
7 44 47
8 43 48
9 42 69
10 41 82
11 12 81
12 13 85
13 14 77
14 15 88
15 16 68
16 17 65
17 18 58
18 19 54
19 20 86
20 21 87
21 22 73
22 23 92
23 24 96
24 25 75
25 26 53
26 27 72
27 28 67
28 29 65
29 30 97
30 31 61
31 32 100
32 33 91
33 34 90
34 35 81
35 36 94
36 37 86
37 38 61
38 39 90
39 40 75
14 34 98
15 36 21
11 20 59
23 29 51
22 24 41
24 36 88
28 40 45
15 22 33
13 40 1
23 32 23
20 31 57
14 29 67
15 17 48
25 32 5
18 30 7
13 15 44
18 23 57
13 16 54
18 33 69
17 32 86
12 15 74
26 33 8
11 29 29
11 34 33
27 31 9
20 25 24
34 39 8
16 39 66
14 27 4
2333

7 6
4
1 2 4
2 4 8
2 3 4
3 4 5

*/
