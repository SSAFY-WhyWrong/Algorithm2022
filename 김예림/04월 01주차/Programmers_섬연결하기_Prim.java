package AlgorithmStudy;

import java.util.*;

public class Programmers_섬연결하기_Prim {
	public static void main(String[] args) {
//		int n = 4;
//		int[][] costs = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
		
//		int n = 5;
//		int[][] costs = {{0,1,5},{1,2,3},{2,3,3},{3,1,2},{3,0,4},{2,4,6},{4,0,7}};
		
		int n = 5;
		int[][] costs = {{0,1,1},{0,2,2},{1,2,5},{1,3,3},{2,3,8},{3,4,1}};
		
		System.out.println(solution(n, costs));
	}
	
	public static int solution(int n, int[][] costs) {
        //n은 섬의 개수
        ArrayList<Island>[] list = new ArrayList[n];
        for(int i=0; i<n; i++) {
        	list[i] = new ArrayList<>();
        }
        
        for(int i=0; i<costs.length; i++) {
        	int start = costs[i][0];
        	int end = costs[i][1];
        	int cost = costs[i][2];
        	
        	list[start].add(new Island(start, end, cost));
        	list[end].add(new Island(end, start, cost));
        }
        
        PriorityQueue<Island> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n];
        
        //임의의 한 정점 선택
        for(int i=0; i<list[0].size(); i++) {
        	pq.offer(list[0].get(i));
        }
        visited[0] = true;
        
        int tot = 0;
        while(!pq.isEmpty()) {
        	
        	Island cur = pq.poll();
        	
        	if(!visited[cur.end]) {
        		tot += cur.cost;
        		visited[cur.end] = true;
        		
        		for(int i=0; i<list[cur.end].size(); i++) {
        			pq.offer(list[cur.end].get(i));
        		}
        	}
        }
        
        return tot;
    }
	static class Island implements Comparable<Island>{
		int start;
		int end;
		int cost;
		public Island(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
		@Override
		public int compareTo(Island o) {
			return this.cost-o.cost;
		}
	}
	
}
