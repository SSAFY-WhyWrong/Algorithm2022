import java.util.*;
import java.io.*;

public class 백준_17080_결함게임 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		if(N % 4 == 3) {
			System.out.println(2);
		} else {
			System.out.println(1);
		}

	}

}
