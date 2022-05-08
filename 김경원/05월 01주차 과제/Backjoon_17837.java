import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Backjoon_17837 {
	static class Node{
		int i,j,dir;
		Node(){}
		Node(int i,int j,int dir){
			this.i = i;			
			this.j = j;
			this.dir = dir;
		}
	}
	
	static int N, K;
	static int arr[][];
	
	static int ni[] = {0,0,-1,1};
	static int nj[] = {1,-1,0,0};
	static Queue<Integer> chess[][];
	static List<Node> pawn;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken(" "));
		K = Integer.parseInt(st.nextToken(" "));
		
		arr = new int[N][N];
		chess = new LinkedList[N][N];
		
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken(" "));
				// 0 흰색, 1 빨간색, 2 파란색
				chess[i][j] = new LinkedList();
				
			}
		}
		int x,y,dir;
		pawn = new LinkedList();
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken(" "))-1;
			y = Integer.parseInt(st.nextToken(" "))-1;
			dir = Integer.parseInt(st.nextToken(" "))-1;
			chess[x][y].add(i);
			pawn.add(new Node(x,y,dir));
		}

		
		simulate();
		
		
	}
	
	static void simulate() {
		int t=1;
		Node node;
		int x,y;
		boolean isMove;
		boolean isFinish = false;
		Queue<Integer> temp = new LinkedList();
		
		
		
		
		while(!isFinish && t<=1000) {
			
			for(int p_num = 0; p_num<K; p_num++) {
				node = pawn.get(p_num);
				
				while(!chess[node.i][node.j].isEmpty()) {
					int temp_num = chess[node.i][node.j].poll();
					if(temp_num == p_num) break;
					else temp.add(temp_num);						
				}
				//칸에서 위치 찾기
			
				x = node.i + ni[node.dir];
				y = node.j + nj[node.dir];
				isMove = true;
				if(x<0|| y<0 || x>=N || y>=N || arr[x][y]==2) {
					
					x = node.i - ni[node.dir];
					y = node.j - nj[node.dir];
					
					
					if(node.dir%2==1) {
						node.dir-=1;
					}else {
						node.dir+=1;
					}
					
					if(x<0|| y<0 || x>=N || y>=N || arr[x][y]==2) {
						x = node.i;
						y = node.j;

						isMove = false;
					}				
				}
				
				
				if(isMove) { //움직일 때
					int temp_num;
					int q_size;
					switch(arr[x][y]) {
					
					case 0:
						chess[x][y].add(p_num);
						
						
						
						while(!chess[node.i][node.j].isEmpty()) {
							temp_num = chess[node.i][node.j].poll();
							chess[x][y].add(temp_num);
						}
						
						while(!temp.isEmpty()) {
							chess[node.i][node.j].add(temp.poll());
						}		
						q_size = chess[x][y].size();
						for(int q=0;q<q_size;q++) {
							temp_num = chess[x][y].poll();
							pawn.get(temp_num).i = x;
							pawn.get(temp_num).j = y;
							chess[x][y].add(temp_num);
						}
						
						
						if(chess[x][y].size()>=4)
							isFinish = true;

						break;
						
					case 1:
						Stack<Integer> reverse = new Stack();
						reverse.add(p_num);
						
						while(!chess[node.i][node.j].isEmpty()) {
							reverse.add(chess[node.i][node.j].poll());
						}
						
						while(!temp.isEmpty()) {
							chess[node.i][node.j].add(temp.poll());
						}
						
						while(!reverse.isEmpty()) {
							temp_num = reverse.pop();
							chess[x][y].add(temp_num);
							pawn.get(temp_num).i = x;
							pawn.get(temp_num).j = y;
						}
						if(chess[x][y].size()>=4)
							isFinish = true;
						
											
						
						break;
					default:
						break;
						
					}					
				} 
				else { //안 움직일 때
					temp.add(p_num);
					while(!chess[node.i][node.j].isEmpty()) {
						temp.add(chess[node.i][node.j].poll());
					}	
					
					while(!temp.isEmpty())
						chess[node.i][node.j].add(temp.poll());
					
				}
				
				
				if(isFinish) break;
				
//				for(int i=0;i<N;i++) {
//					for(int j=0;j<N;j++) {
//						System.out.print(chess[i][j].size()+" ");
//					}
//					System.out.println();
//				}
//				System.out.println("------"+p_num+"----------");
				
			}
			
			if(isFinish) break;
			t++;
			
		}
		

		if(t > 1000) System.out.println(-1);
		else System.out.println(t);
		
	}
	
}
