package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon1958_LCS3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String one = br.readLine();
		String two = br.readLine();
		String three = br.readLine();
		
		int n1 = one.length();
		int n2 = two.length();
		int n3 = three.length();
		
		int[][][] comp = new int[n1+1][n2+1][n3+1];
		
		for(int i=1; i<=n1; i++) {
			for(int j=1; j<=n2; j++) {
				for(int k=1; k<=n3; k++) {
					char c1 = one.charAt(i-1);
					char c2 = two.charAt(j-1);
					char c3 = three.charAt(k-1);
					
					if(c1==c2 && c2==c3) comp[i][j][k] += comp[i-1][j-1][k-1]+1;
					//같지 않을 경우 : 같은 왼쪽과 위쪽의 값 중 더 큰 값이 들어간다.
					else comp[i][j][k] = Math.max(comp[i-1][j][k], Math.max(comp[i][j-1][k], comp[i][j][k-1]));
				}
			}
		}
		System.out.println(comp[n1][n2][n3]);
	}
}
