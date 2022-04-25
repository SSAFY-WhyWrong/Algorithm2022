import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Backjoon_3197 {
	static class Node{
		int i,j, type;
		Node(int i,int j, int type){
			this.i = i;
			this.j = j;
			this.type = type;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int R,C;
		
		st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[R][C];
		
		Node start = null;
		Node end = null;
		Queue<Node> next = new LinkedList<>();
		
		for(int i=0;i<R;i++) {
			String str = br.readLine();
			for(int j=0;j<C;j++) {
				if(str.charAt(j) == 'X') {
					arr[i][j] = Integer.MAX_VALUE;
				}
				else if(str.charAt(j) =='.') {
					arr[i][j] = 0;
					next.add(new Node(i,j,2));
				} else {
					arr[i][j] = 0;
					if(start == null) {
						start = new Node(i,j,0);
					}else {
						end = new Node(i,j,1);
					}
				}
			}
		}
		
		System.out.println(simulate(start,end,arr,R,C,next));
		
		
		
	}
	
	static int simulate(Node start, Node end, int[][] arr, int R, int C, Queue<Node> next) {
		
		int answer = 0;
		
		
		Queue<Node> q = null;
		
		int[] ni = {-1,1,0,0};
		int[] nj = {0,0,-1,1};
		next.add(start);
		next.add(end);
		
		boolean[][][] visited = new boolean[3][R][C];
		visited[0][start.i][start.j] = true;
		visited[1][end.i][end.j] = true;
		while(!next.isEmpty()) {
			q = next;
			next = new LinkedList<>();
			while(!q.isEmpty()) {
				Node now = q.poll();
				if(visited[0][now.i][now.j] && visited[1][now.i][now.j] ) {
					return answer;
				}
				
				for(int d=0;d<4;d++) {
					int y = now.i + ni[d];
					int x = now.j + nj[d];
					
					if(y < 0|| x <0 || y>= R || x >= C || visited[now.type][y][x]) {
						continue;
					}
					
					if(now.type == 2 ) {
						if(arr[y][x] == Integer.MAX_VALUE) {
							arr[y][x] = answer+1;
							next.add(new Node(y,x,now.type));							
						}
						continue;
					}
					
					
					visited[now.type][y][x] = true;
					
					if(arr[y][x] > answer) {
						arr[y][x] = answer+1;
						next.add(new Node(y,x,now.type));
					}else {
						q.add(new Node(y,x,now.type));
					}
					
				}
			}
			answer++;
		}
		
		return answer;
	}
}
