import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Backjoon_1135 {
	static class Node implements Comparable<Node>{
		int idx, time;
		
		Node(int idx, int time){
			this.idx = idx;
			this.time = time;
		}

		@Override
		public int compareTo(Node o) {
			return o.time - this.time;
		}
		
		
		
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N;
		
		N = Integer.parseInt(br.readLine());
		
		List<Integer>[] edge = new ArrayList[50];
		
		st = new StringTokenizer(br.readLine());
		st.nextToken(" ");
		
		for(int i=0;i<50;i++) {
			edge[i] = new ArrayList<>();
		}
		
		for(int i=1;i<N;i++) {
			int num = Integer.parseInt(st.nextToken(" "));
			edge[num].add(i);						
		}
		
		
		int answer = call(0,edge);
		
		System.out.println(answer);
		

	
	}
	
	static int call(int now, List<Integer>[] edge) {
		
		
		int result = 0;
		
		List<Node> leaf = new ArrayList<>();
		
		for(int num : edge[now]) {
			int next = call(num,edge);
			leaf.add(new Node(num,next));
			
		}
		
		Collections.sort(leaf);
		
		for(int i=0;i<leaf.size();i++) {
			result = Math.max(result, leaf.get(i).time + i+1);
		}
		
		return result;
	}
	
	
}
