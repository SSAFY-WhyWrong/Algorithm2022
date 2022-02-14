import java.util.*;

public class 백준_1958_LCS3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String a = sc.nextLine();
		String b = sc.nextLine();
		String c = sc.nextLine();
		
		int[][][] LCS = new int[a.length()+1][b.length()+1][c.length()+1];
		for(int i=1;i<a.length()+1;i++) {
			for(int j=1;j<b.length()+1;j++) {
				for(int k=1;k<c.length()+1;k++) {
					if(a.charAt(i-1) == b.charAt(j-1) && b.charAt(j-1) == c.charAt(k-1)) {
						LCS[i][j][k] = LCS[i-1][j-1][k-1] + 1;
					} else {
						LCS[i][j][k] = Math.max(LCS[i-1][j][k], Math.max(LCS[i][j-1][k], LCS[i][j][k-1]));
					}
				}
			}
		}
		System.out.println(LCS[a.length()][b.length()][c.length()]);
	}

}
