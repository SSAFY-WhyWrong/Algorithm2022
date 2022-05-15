import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Backjoon_1339 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int N;
		N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[27];
		

		
		for(int i=0;i<N;i++) {
			String str= br.readLine();
			int num = 1;
			for(int j=str.length()-1;j>=0;j--) {
				arr[str.charAt(j)-'A'] += num;
				num*=10;
			}
		}
		
		int answer = 0;
		Arrays.sort(arr);
		
		for(int i=0;i<10;i++) {
			answer += arr[26-i]*(9-i);
		}
		
		System.out.println(answer);
		

	}
	

	
}
