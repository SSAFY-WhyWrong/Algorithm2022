import java.util.*;
import java.io.*;

public class 백준_1135_뉴스전하기 {
	
	static int N, answer;
	static ArrayList<Integer>[] boss;
	static boolean[] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		boss = new ArrayList[N];
		
		for(int i=0;i<N;i++) {
			boss[i] = new ArrayList<>();
		}
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for(int i=0;i<N;i++) {
			int num = Integer.parseInt(st.nextToken());
			
			if(num == -1) continue;
			boss[num].add(i);
		}
		
		answer = Integer.MAX_VALUE;
		visit = new boolean[N];
		visit[0] = true;
		time(0, 0, 1);
		System.out.println(answer);
	}
	
	public static void time(int node, int total, int cnt) {
		System.out.println(node + " " + total + " " + cnt);
		if(cnt == N) {
			answer = Math.min(total, answer);
			return;
		}
		
		for(int i=0;i<boss[node].size();i++) {
			int next = boss[node].get(i);
			if(visit[next]) continue;
			
			visit[next] = true;
			time(next, total+1, cnt+1);
			visit[next] = false;
		}
		
		if(boss[node].size() == 0) {
			time(0, total+1, cnt+1);
		}
	}
}
