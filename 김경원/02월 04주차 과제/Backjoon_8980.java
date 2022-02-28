import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Backjoon_8980 {
	
	static class Node implements Comparable<Node>{
		int from, to , w;
		
		public Node(int from, int to, int w){
			this.from = from;
			this.to = to;
			this.w = w;
		}
		
		@Override
		public int compareTo(Node o) {
			
			if(this.to == o.to) {
				return this.from - o.from;
			}
			
			return this.to - o.to;
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N,C,M;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken(" "));
		C = Integer.parseInt(st.nextToken(" "));
		
		M = Integer.parseInt(br.readLine());
		
		List<Node> list = new ArrayList<>();
		
		
		int[] truck = new int[2001];
		
		for(int i=0;i<M;i++) {
			int from, to, w;
			
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken(" "));
			to = Integer.parseInt(st.nextToken(" "));
			w = Integer.parseInt(st.nextToken(" "));
			
			list.add(new Node(from,to,w));
			
			
		}
		
		Collections.sort(list);
		
		int result = 0;
		
		for(int i=0;i<M;i++) {
			int cnt = 0;
			
			Node now = list.get(i);
			
			for(int j=now.from; j < now.to; j++) {
				cnt = Math.max(cnt, truck[j]);
			}
			
			int usedSpace = Math.min(now.w, C - cnt);
			result += usedSpace;
			
			for(int j=now.from; j<now.to;j++) {
				truck[j] += usedSpace;
			}
			
			
			
		}
		

		
		System.out.println(result);
		
		
	}
	
	
}
