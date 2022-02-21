import java.util.*;
import java.io.*;

public class 백준_5052_전화번호목록 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bf.readLine());
		
		while(t-->0) {
			int N = Integer.parseInt(bf.readLine());
			String[] phone = new String[N];
			
			for(int i=0;i<N;i++) {
				phone[i] = bf.readLine();
			}

			Arrays.sort(phone);
			System.out.println(find(phone));
		}
	}
	
	public static String find(String[] phone) {
		for(int i=1;i<phone.length;i++) {
			String comp;
			
			if(phone[i].length() < phone[i-1].length()) {
				comp = phone[i];
			} else {
				comp = phone[i].substring(0, phone[i-1].length());
			}
			
			if(phone[i-1].equals(comp)) return "NO";
		}
		
		return "YES";
	}

}
