package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon1717_집합의표현 {
	
	static int[] route;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		route = new int[n+1];
		for(int i=0; i<=n; i++) {
			route[i] = i;
		}
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int check = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(check == 0) {
				Union(a,b);
			}else {
				if(Find(a)==Find(b)) sb.append("YES\n");
				else sb.append("NO\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static int Find(int a) {
		if(route[a]==a) return a;
		
		return route[a] = Find(route[a]);
	}

	private static void Union(int a, int b) {
		int aRoot = Find(a);
		int bRoot = Find(b);
		
		if(aRoot != bRoot) route[aRoot] = bRoot;
		
		return;
	}
}
