import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Backjoon_3221 {


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int L, T;

		st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		int N = Integer.parseInt(br.readLine());


		int[] answer = new int[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int pos = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);

			
			if(dir == 'L') {
				pos -= T;
			}else {
				pos += T;
			}
			
			pos %= 2*L;
			
			if(pos < 0) {
				pos = 2*L + pos;
			}
			if(pos > L) {
				pos = 2*L - pos;
			}
			
			answer[i] = pos;
		}
		Arrays.sort(answer);
		
		for(int num : answer) {
			System.out.print(num+" ");
		}
	}
}
