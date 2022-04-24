import java.util.*;
import java.io.*;

public class 백준_1941_소문난칠공주 {
	
	static char[][] classRoom;
	static int[] select;
	static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};
	static boolean[] visit;
	static int answer;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		classRoom = new char[5][5];
		
		for(int i=0;i<5;i++) {
			String tmp = sc.next();
			for(int j=0;j<5;j++) {
				classRoom[i][j] = tmp.charAt(j);
			}
		}
		
		answer = 0;
		select = new int[7];
		comb(0, 0);
		
		System.out.println(answer);
	}
	
	public static void comb(int cnt, int start) {
		if(cnt == 7) {
			if(isFriends() && isNearBy()) {
				answer++;
			}
			
			return;
		}
		
		for(int i=start;i<25;i++) {
			 select[cnt] = i;
			 comb(cnt+1, i+1);
		}
	}

	private static boolean isNearBy() {
		visit = new boolean[7];
		visit[0] = true;
		
		dfs(select[0] / 5, select[0] % 5);
		
		for(int i=0;i<7;i++) {
			if(!visit[i]) return false;
		}
		
		return true;
	}

	private static void dfs(int x, int y) {
		for(int d=0;d<4;d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
			int num = nx * 5 + ny;
			
			for(int i=0;i<7;i++) {
				if(select[i] == num && !visit[i]) {
					visit[i] = true;
					dfs(nx, ny);
				}
			}
		}
	}
	
	private static boolean isFriends() {
		int cnt = 0;
		
		for(int i=0;i<7;i++) {
			int row = select[i] / 5;
			int col = select[i] % 5;
			
			if(classRoom[row][col] == 'S') cnt++;
		}
		
		if(cnt >= 4) return true;
		
		return false;
	}
}
