import java.util.*;
import java.io.*;

public class 백준_1766_문제집 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer>[] order = new ArrayList[N+1];
		int[] indegree = new int[N+1];
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i=0;i<N+1;i++) {
			order[i] = new ArrayList<>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(bf.readLine());
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			order[A].add(B);
			indegree[B]++;
		}
		
		for(int i=1;i<N+1;i++) {
			if(indegree[i] == 0) {
				pq.add(i);
			} else {
				Collections.sort(order[i]);
			}
		}
		
		while(!pq.isEmpty()) {
			int now = pq.poll();
			sb.append(now + " ");
			
			for(int i=0;i<order[now].size();i++) {
				int next = order[now].get(i);
				indegree[next]--;
				
				if(indegree[next] == 0) pq.add(next);
			}
		}

		System.out.println(sb.toString().trim());
		
	}

}
