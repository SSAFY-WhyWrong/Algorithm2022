import java.util.*;
import java.io.*;

public class 백준_9657_돌게임3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		if(N % 7 == 0 || N % 7 == 2) {
			System.out.println("CY");
		} else {
			System.out.println("SK");
		}
	}

}
