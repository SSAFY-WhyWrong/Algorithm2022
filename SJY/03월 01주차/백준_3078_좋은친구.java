import java.util.*;
import java.io.*;

public class 백준_3078_좋은친구 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Integer>[] q = new LinkedList[21];
		for(int i=0;i<21;i++) {
			q[i] = new LinkedList<>();
		}
		
		long cnt = 0;
		for(int i=1;i<N+1;i++) {
			String friend = bf.readLine();
			int len = friend.length();
			
			if(q[len].isEmpty()) {
				q[len].add(i);
				continue;
			}
			
			while(!q[len].isEmpty() && i - q[len].peek() > K) {
				q[len].poll();
			}
			
			cnt += q[len].size();
			q[len].add(i);
		}
		
		System.out.println(cnt);
	}

}
