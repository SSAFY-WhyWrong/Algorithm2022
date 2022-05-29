package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon2026_소풍 {
	
	static boolean friend[][], end;
	static int K, N, F;
	static ArrayList<Integer> list;
	static int temp[], ans[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		K = Integer.parseInt(st.nextToken()); //선택해야 할 학생 수
		N = Integer.parseInt(st.nextToken()); //총 학생 수
		F = Integer.parseInt(st.nextToken()); //친구 관계 갯수
		
		friend = new boolean[N+1][N+1];
		list = new ArrayList<>();
		temp = new int[K];
		ans = new int[K];
		end=false;
		
		for(int i=0; i<F; i++) {
			st = new StringTokenizer(br.readLine()," ");
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			friend[from][to] = true;
			friend[to][from] = true;
		}
		
		for(int i=1;i<=N;i++) {
			int cnt = 0;
			for(int j=1;j<=N;j++) 
				if(friend[i][j]) cnt++;
			if(cnt>=K-1)
				list.add(i);
		}
		
		dfs(0,0);
		
		if(!end) System.out.println(-1);
		else {
			for(int a: ans)
				System.out.println(a);
		}
	}
	
	static void dfs(int idx, int cnt) {
		
		if(end) return;
		if(cnt==K) {
			boolean pos = true;
			for(int i=0;i<K;i++) {
				for(int j=0;j<K;j++) {
					if(i==j) continue;
					if(!friend[temp[i]][temp[j]]) {
						pos=false;
						break;
					}
				}
				if(!pos) break;
			}
			if(pos) {
				end=true;
				ans = temp.clone();
			}
			return;
		}
		for(int i=idx;i<list.size();i++) {
			temp[cnt]=list.get(i);
			dfs(idx+1,cnt+1);
			temp[cnt]=0;
		}
	}
	
}
