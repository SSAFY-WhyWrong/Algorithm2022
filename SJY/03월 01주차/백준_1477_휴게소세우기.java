import java.util.*;
import java.io.*;

public class 백준_1477_휴게소세우기 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[] shop = new int[N+2];
		shop[0] = 0;
		shop[N+1] = L;
		
		st = new StringTokenizer(bf.readLine());
		for(int i=1;i<N+1;i++) {
			shop[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(shop);
		int start = 0, end = L, mid = 0, cnt = 0;
		while(start <= end) {
			mid = (start + end) / 2;
			cnt = 0;
			
			for(int i=1;i<N+2;i++) {
				if(mid == 0) {
					cnt = Integer.MAX_VALUE;
					break;
				}
				int dist = shop[i] - shop[i-1];
				cnt += (dist/mid);
				
				if(dist % mid == 0) cnt--;
			}
			
			if(cnt <= M) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		
		System.out.println(start);
		
	}

}
