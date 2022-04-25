package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon14267_회사문화1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] employee = new int[n+1];
		st = new StringTokenizer(br.readLine()," ");
		for(int i=1; i<=n; i++) {
			employee[i] = Integer.parseInt(st.nextToken());
		}
		int[] phrase = new int[n+1];
		for(int j=0; j<m; j++) {
			st = new StringTokenizer(br.readLine()," ");
			
			int i = Integer.parseInt(st.nextToken()); //칭찬을 받은 직원 번호
			int w = Integer.parseInt(st.nextToken()); //칭찬의 수치
			
			phrase[i] += w;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(phrase[1]+" ");
		for(int i=2; i<=n; i++) {
			phrase[i] += phrase[employee[i]];
			
			sb.append(phrase[i]+" ");
		}
		System.out.println(sb.toString());
	}
}
