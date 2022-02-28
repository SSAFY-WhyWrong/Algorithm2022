import java.util.*;
import java.io.*;

public class 백준_8980_택배 {

	static class BoxInfo implements Comparable<BoxInfo> {
		int sender;
		int getter;
		int cnt;
		
		public BoxInfo(int sender, int getter, int cnt) {
			this.sender = sender;
			this.getter = getter;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(BoxInfo o) {
			if(this.sender == o.sender && this.getter == o.getter) {
				return this.cnt - o.cnt;
			} else if(this.getter == o.getter) {
				return this.sender - o.sender;
			}
			
			return this.getter - o.getter;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int limit = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(bf.readLine());
		BoxInfo[] box = new BoxInfo[M];
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(bf.readLine());
			box[i] = new BoxInfo(Integer.parseInt(st.nextToken()), 
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(box);
		
		int maxCnt = 0;
		int[] capacity = new int[N+1];
		for(int i=1;i<N;i++) {
			capacity[i] = limit;
		}
		
		for(int i=0;i<M;i++) {
			BoxInfo nowBox = box[i];
			int nowCnt = Integer.MAX_VALUE;
			
			for(int j=nowBox.sender;j<nowBox.getter;j++) {
				nowCnt = Math.min(nowCnt, capacity[j]);
			}
			
			if(nowCnt >= nowBox.cnt) {
				for(int j=nowBox.sender;j<nowBox.getter;j++) {
					capacity[j] -= nowBox.cnt;
				}
				maxCnt += nowBox.cnt;
			} else {
				for(int j=nowBox.sender;j<nowBox.getter;j++) {
					capacity[j] -= nowCnt;
				}
				maxCnt += nowCnt;
			}
		}
		
		System.out.println(maxCnt);
	}

}
