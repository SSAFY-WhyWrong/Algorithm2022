import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Backjoon_3078 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		st = new StringTokenizer(br.readLine());
		int N,K;
		long answer = 0;
		
		N = Integer.parseInt(st.nextToken(" "));
		K = Integer.parseInt(st.nextToken(" "));
		
		Queue<Integer>[] q = new LinkedList[21];
		for(int i=2;i<=20;i++) {
			q[i] = new LinkedList<>();
		}
		
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			int len = str.length();
			
			
			while(!q[len].isEmpty() && q[len].peek() + K < i) {
				q[len].poll();
			}
			
			answer += q[len].size();
			
			q[len].add(i);
			
		}
		
		System.out.println(answer);
		
		
		
	}
}
