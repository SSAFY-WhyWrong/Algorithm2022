import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Backjoon_9007 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		StringBuilder sb = new StringBuilder();
		
		int T;
		
		T = Integer.parseInt(br.readLine());
		
		for(int t=0;t<T;t++) {
			int k,n;
			
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			
			int[][] weight = new int[4][n];
			for(int i=0;i<4;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					weight[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int sum1[] = new int[n*n];
			int sum2[] = new int[n*n];
			int result, answer=0;
			int min = Integer.MAX_VALUE;
			int idx = 0;
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					sum1[idx] = weight[0][i] + weight[1][j];
					sum2[idx] = weight[2][i] + weight[3][j];
					idx++;
				}
			}
			
			Arrays.sort(sum1);
			Arrays.sort(sum2);
			
			for(int num : sum1) {
				result = binarySearch(sum2, k - num);
				int abs = Math.abs(result);
				
				if(min > abs) {
					answer = result;
					min = abs;
				}else if(min == abs && answer < 0) {
					answer = result;
				}
				
				
			}
			
			sb.append(k-answer).append('\n');
			
		}
		
		System.out.println(sb);	
	}
	
	
	static int binarySearch(int[] arr, int num) {
		int result = 0;
		int min = Integer.MAX_VALUE;
		int low,high;
		
		low = 0;
		high = arr.length - 1;
		
		while(low <= high) {
			int mid = (low+high)/2;
			int val = num - arr[mid];
			
			int abs = Math.abs(num - arr[mid]);
			
			if(min > abs) {
				min = abs;
				result = val;
			}else if(min == abs && result < 0) {
				result = val;
			}
			
			if(val >0) {
				low = mid +1;
			}else if(val < 0) {
				high = mid -1;
			}else {
				return 0;			
			}
			
			
			
		}
		
		return result;
	}
}
