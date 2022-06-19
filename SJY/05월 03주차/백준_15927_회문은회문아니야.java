import java.util.*;
import java.io.*;

public class 백준_15927_회문은회문아니야 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		
		boolean isPalin = false;
		boolean answer = false;
		
		for(int i=0;i<str.length() / 2;i++) {
			if(str.charAt(i) != str.charAt(str.length() - 1 - i)) {
				answer = true;
				break;
			} else if(str.charAt(i) != str.charAt(i + 1)) {
				isPalin = true;
			}
		}
		
		if(answer) {
			System.out.println(str.length());
		} else {
			if(isPalin) {
				System.out.println(str.length() - 1);
			} else {
				System.out.println(-1);
			}
		}
	}

}
