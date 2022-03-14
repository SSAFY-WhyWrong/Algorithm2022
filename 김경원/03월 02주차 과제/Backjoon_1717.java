import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon_1717 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N,M;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] parent = new int[N+1];
		for(int i=1;i<=N;i++) {
			parent[i] = i;
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int order, a, b;
			
			order = Integer.parseInt(st.nextToken(" "));
			a = Integer.parseInt(st.nextToken(" "));
			b = Integer.parseInt(st.nextToken(" "));
			
			if(order == 0) {
				union(a,b,parent);
			}
			
			if(order == 1) {
				if(isUnion(a,b,parent)) {
					sb.append("YES").append('\n');
				}else {
					sb.append("NO").append('\n');
				}
			}
			
			
		}
		
		System.out.println(sb);
		
		
		
	}
	
	
	static int getParent(int num, int[] parent) {
		
		if(num == parent[num]) {
			return num;
		}
		
		return parent[num] = getParent(parent[num],parent);
	}
	
	static boolean isUnion(int a, int b, int[] parent) {
		
		int aParent = getParent(a, parent);
		int bParent = getParent(b, parent);
		
		if(aParent!=bParent) {
			return false;
		}
		
		
		return true;
	}
	
	
	static void union(int a, int b, int[] parent) {

		int aParent = getParent(a, parent);
		int bParent = getParent(b, parent);
		
		parent[aParent] = Math.min(aParent, bParent);
		parent[bParent] = Math.min(aParent, bParent);
		
		
		
	}
}
