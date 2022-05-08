import java.util.LinkedList;
import java.util.Queue;

public class Programmers_경주로_건설 {
	static public class Node{
		int i,j, type, weight;
		
		Node(int i,int j,int type,int weight){
			this.i= i;
			this.j = j;
			this.type = type;
			this.weight = weight;
		}
	}
	
	//직선 100원 , 코너 500원
    static public int solution(int[][] board) {
        int answer = 0;
        
        int N = board.length;
        
        int[][][] cost = new int[4][N][N];
        
        
        for(int i=0;i<4;i++) {
        	for(int j=0;j<N;j++) {
        		for(int k=0;k<N;k++) {
        			cost[i][j][k] = Integer.MAX_VALUE;
        		}
        	}
        }
        
        Queue<Node> q = new LinkedList<>();
        
        cost[0][0][0] = 0;
        cost[1][0][0] = 0;
        cost[2][0][0] = 0;
        cost[3][0][0] = 0;
        
        q.add(new Node(0,0,1,0));
        q.add(new Node(0,0,3,0));
        
        int[] ni = {-1,1,0,0};
        int[] nj = {0,0,-1,1};
        
        while(!q.isEmpty()) {
        	
        	Node now = q.poll();
        	
        	
        	for(int d=0;d<4;d++) {
        		int x = now.j + nj[d];
        		int y = now.i + ni[d];
        		
        		if(x < 0 || y < 0 || x>= N || y>= N || board[y][x] == 1) {
        			continue;
        		}
        		
        		int num = 100;
        		if(now.type != d) {
        			num += 500;
        		}
        		
        		if(cost[now.type][now.i][now.j] + num < cost[d][y][x]) {
        			cost[d][y][x] = cost[now.type][now.i][now.j] + num;
        			q.add(new Node(y,x,d,cost[d][y][x]));
        		}
        		
        	}
        	
        	
        	
        }
        
        answer = Integer.MAX_VALUE;
        
        for(int d=0;d<4;d++) {
        	answer = Math.min(answer, cost[d][N-1][N-1]);
        }
        
        return answer;
    }

}
