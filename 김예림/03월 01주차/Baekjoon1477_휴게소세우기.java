package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon1477_휴게소세우기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int N = Integer.parseInt(st.nextToken()); //현재 휴게소의 수
		int M = Integer.parseInt(st.nextToken()); //더 지으려고 하는 휴게소의 개수
		int L = Integer.parseInt(st.nextToken()); //고속도로의 길이
		
		int[] rest = new int[N+2];
		st = new StringTokenizer(br.readLine()," ");
		for(int i=1; i<=N; i++) {
			rest[i] = Integer.parseInt(st.nextToken());
		}
		rest[0] = 0;
		rest[N+1] = L;
		Arrays.sort(rest);
		
		int start = 1; //0으로 하면 divide by zero 런타임 에러 발생
		int end = L;
		while(true) {
			if(start>end) break;
			
			int mid = (start+end)/2;
			
			int cnt = 0;
			for(int i=1; i<=N+1; i++) {
				cnt += (rest[i]-rest[i-1]-1)/mid;
			}
			if(cnt > M) start = mid+1;
			else end = mid-1;
		}
		System.out.println(start);
	}
}
