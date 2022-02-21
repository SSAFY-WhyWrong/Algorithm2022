package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon10836_여왕벌 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int M = Integer.parseInt(st.nextToken()); //격자칸의 가로와 세로크기
		int N = Integer.parseInt(st.nextToken()); //날짜 수
		
//		ArrayList<Integer> list = new ArrayList<>();
		int[][] hive = new int[M][M];
		int size = 2*M-1;
		int[] put = new int[size];
		Arrays.fill(put, 1);
		for(int i=0; i<N; i++) {
			//첫 날부터 순서대로 제일 왼쪽열과 제일 위쪽행의 애벌레들이 자라는 정도
			st = new StringTokenizer(br.readLine()," ");
			
			int zero = Integer.parseInt(st.nextToken());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			//zero + one + two = 2M-1
			
			int idx = 0;
			for(int j=0; j<zero; j++) {
				idx++;
			}
			for(int j=0; j<one; j++) {
				put[idx++] += 1;
			}
			for(int j=0; j<two; j++) {
				put[idx++] += 2;
			}
		}
		for(int i=0; i<M; i++) {
			hive[M-i-1][0] = put[i];
		}
		for(int i=M; i<size; i++) {
			hive[0][i-M+1] = put[i];
		}
		for(int i=1; i<M; i++) {
			for(int j=1; j<M; j++) {
				hive[i][j] = Math.max(Math.max(hive[i-1][j], hive[i][j-1]), hive[i-1][j-1]);
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			for(int j=0; j<M; j++) {
				sb.append(hive[i][j]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
