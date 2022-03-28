package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon21757_나누기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int[] numbers = new int[N+1]; //뽑은 애들 저장하는 배열
		long tot = 0;
		for(int i=1; i<=N; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			tot += num;
			numbers[i] = num;
		}
		
		int[] sum = new int[N+1];
		for(int i=1; i<=N; i++) {
			sum[i] = sum[i-1] + numbers[i];
		}
		
		long ans = 0;
		int[][] dp = new int[N+1][N+1];
		//그룹 하나의 합이 전체tot/4여야 함
		if(tot%4==0) {
			long part = tot/4;
			
			dp[0][0] = 1;
			for(int i=1; i<=N; i++) {
				dp[i][0] = 1;
				for(int j=1; j<=3; j++) { //4등분 해야하기 때문에
					dp[i][j] = dp[i-1][j];
					if(part*j == sum[i]) { //누적합 배열이 tot/4인 part의 배수일 때 자르기
						dp[i][j] += dp[i-1][j-1];
					}
				}
			}
			ans = dp[N-1][3];
		}
		System.out.println(ans);
	}
}
