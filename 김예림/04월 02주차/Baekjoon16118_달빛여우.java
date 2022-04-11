package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon16118_달빛여우 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N+1][N+1];
		int[] foxDis = new int[N+1];
		boolean[] visited = new boolean[N+1];
		int[][] wolfDis = new int[2][N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());
			
			map[from][to] = dis*2;
			map[to][from] = dis*2;
		}
		
		Arrays.fill(foxDis, Integer.MAX_VALUE);
		
		//여우 먼저
		foxDis[1] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1, 1, foxDis[1]));
		
		while(!pq.isEmpty()) {
			
			Node cur = pq.poll();
			
			if(visited[cur.path]) continue;
			
			visited[cur.path] = true;
			
			for(int i=0; i<=N; i++) {
				if(!visited[i] && map[cur.path][i] != 0 
						&& foxDis[i]>cur.tot+map[cur.path][i]) {
					foxDis[i] = cur.tot + map[cur.path][i];
					pq.offer(new Node(cur.order, i, foxDis[i]));
				}
			}
		}
		//이제 늑대 차례
		pq.clear();
		Arrays.fill(wolfDis[0], Integer.MAX_VALUE);
		Arrays.fill(wolfDis[1], Integer.MAX_VALUE);
		wolfDis[0][1] = 0;
		pq.offer(new Node(0, 1, wolfDis[0][1]));
		
		
		while(!pq.isEmpty()) {
			
			Node cur = pq.poll();
			
			//현재 지점까지 이동하는데 걸린 최소시간보다 현재 해당 지접에 오기까지 걸린 최소시간이 더 크다면 continue
			if(wolfDis[cur.order][cur.path]<cur.tot) continue;
			
			//wolfDis[0][i] -> 현재 지점까지 빠르게 달려온 경우
			//wolfDis[1][i] -> 현재 지점까지 느리게 걸어온 경우
			//if(cur.order==1) nextorder = 0;
			
			for(int i=1; i<=N; i++) {
				if(map[cur.path][i]==0) continue;
				
				int nextorder = 1 - cur.order;
				int dis = wolfDis[cur.order][cur.path];
				
				if(cur.order==0) dis += map[cur.path][i]/2;
				else dis += map[cur.path][i]*2;
				
				if(wolfDis[nextorder][i] > dis) {
					wolfDis[nextorder][i] = dis;
					pq.offer(new Node(nextorder, i, dis));
				}
				
			}
		}
		int ans = 0;
		for(int i=1; i<=N; i++) {
			if(foxDis[i]<Math.min(wolfDis[0][i], wolfDis[1][i])) ans++;
		}
		System.out.println(ans);
		
		
	}
	static class Node implements Comparable<Node>{
		int order; //늑대가 이걸 몇 번째로 지나가는지-> %2==0이면 거리*2만큼 걸리고, !==0이면 거리/2
		int path;
		int tot;
		public Node(int order, int path, int tot) {
			this.order = order;
			this.path = path;
			this.tot = tot;
		}
		@Override
		public int compareTo(Node o) {
			if(this.tot<o.tot) return -1;
			return 1;
		}
	}
}
/*
4 4 
1 2 1 
2 3 10 
2 4 1 
4 1 1
0
*/