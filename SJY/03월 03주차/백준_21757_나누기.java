import java.util.*;
import java.io.*;

public class 백준_21757_나누기 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int[] nums = new int[N+1];
		long[] sum = new long[N+1];
		long div = 0;
		long answer = 0;
		boolean isZero = true;
		
		for(int i=1;i<N+1;i++) {
			int num = Integer.parseInt(st.nextToken());
			
			div += num;
			nums[i] = num;
			
			if(i != 0) {
				sum[i] = sum[i-1] + num;
			} else {
				sum[i] = num;
			}
			
			if(num != 0) isZero = false;
		}
		
		if(div == 0 && isZero) {
			answer = combCalc(N);
		} else if(div % 4 == 0) {
			div /= 4;
			
			long[] dp = new long[3];
			for(int i=1;i<N;i++) {
				if(sum[i] == div) {
					dp[0] += 1;
				} else if(sum[i] == 2*div) {
					dp[1] += dp[0];
				} else if(sum[i] == 3*div) {
					dp[2] += dp[1];
				}
			}

			answer = dp[2];
			if(answer == 0) {
				answer = combCalc(dp[0]+1);
			}
			
		}
		
		System.out.println(answer);
	}
	
	public static long combCalc(long n) {
		return (n-1) * (n-2) * (n-3) / 6;
	}

}
