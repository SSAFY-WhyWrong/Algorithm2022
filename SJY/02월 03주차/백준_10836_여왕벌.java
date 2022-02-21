import java.util.*;
import java.io.*;

public class 백준_10836_여왕벌 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] bee = new int[2*M-1];
		Arrays.fill(bee, 1);
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(bf.readLine());
			int zero = Integer.parseInt(st.nextToken());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			
			for(int j=zero;j<zero+one;j++) {
				bee[j]++;
			}
			
			for(int j=zero+one;j<2*M-1;j++) {
				bee[j] += 2;
			}
		}
		
		for(int i=0;i<M;i++) {
			for(int j=0;j<M;j++) {
				if(j != 0) {
					System.out.print(bee[j+M-1] + " ");
				} else {
					System.out.print(bee[M-1-i] + " ");
				}
			}
			
			System.out.println();
		}

	}

}
