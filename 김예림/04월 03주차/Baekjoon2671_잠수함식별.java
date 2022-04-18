package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Baekjoon2671_잠수함식별 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String sound = br.readLine();
		
		String str = "^(100+1+|01){1,}$";
		
		if(Pattern.matches(str, sound)) System.out.println("SUBMARINE");
		else System.out.println("NOISE");
	}

}
