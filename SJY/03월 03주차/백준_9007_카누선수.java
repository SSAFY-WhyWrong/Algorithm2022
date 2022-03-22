import java.util.*;
import java.io.*;

public class 백준_9007_카누선수 {
	
	static int K, N;
	static int[][] weights;
	static int[] sum12, sum34;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bf.readLine());
		
		while(t-- > 0) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			K = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			weights = new int[4][N];
			sum12 = new int[N*N];
			sum34 = new int[N*N];
			
			for(int i=0;i<4;i++) {
				st = new StringTokenizer(bf.readLine());
				for(int j=0;j<N;j++) {
					weights[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					sum12[(N*i)+j] = weights[0][i] + weights[1][j];
					sum34[(N*i)+j] = weights[2][i] + weights[3][j];
				}
			}

			Arrays.sort(sum34);

			System.out.println(totalWeights());
		}
	}

	private static int totalWeights() {
		int res = Integer.MAX_VALUE;
		int maxGap = Integer.MAX_VALUE;

		for(int i=0;i<N*N;i++) {
			int start = 0;
			int end = N*N;
			
			while(start <= end && start + end < 2*N*N) {
				int mid = (start + end) / 2;
				int sum = sum12[i] + sum34[mid];
				int gap = Math.abs(sum - K);

				if(sum == K) return K;
				if(sum < K) {
					start = mid + 1;
				} else {
					end = mid - 1;
				}
				
				if(gap < maxGap) {
					res = sum;
					maxGap = gap;
				} else if(gap == maxGap) {
					res = Math.min(sum, res);
				}
			}
		}

		return res;
	}

}