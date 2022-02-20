import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Backjoon_5052 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		List<String> str = new ArrayList<>();
		
		int t_case;
		int str_cnt;
		boolean flag;
		StringBuilder sb = new StringBuilder();
		String s1,s2;
		String s;
		
		
		st = new StringTokenizer(br.readLine());
		t_case = Integer.parseInt(st.nextToken(" "));
		
		for(int t=0;t<t_case;t++) {
			st = new StringTokenizer(br.readLine());
			str_cnt = Integer.parseInt(st.nextToken(" "));
			
			for(int i=0;i<str_cnt;i++) {
				st = new StringTokenizer(br.readLine());
				s = st.nextToken();
				str.add(s);
			}
			
			Collections.sort(str, (o1,o2)->o1.compareTo(o2));
			
//			for(int i=0;i<str_cnt;i++) {
//				System.out.println(str.get(i));
//			}
			
			
			flag = true;
			
			
				for(int j=1;j<str_cnt;j++) {
					s1 = str.get(j-1);
					s2 = str.get(j);
					if(s1.length() > s2.length()) continue;
					s2 = s2.substring(0, s1.length());
					if(s1.equals(s2)) {
						flag =false;
						break;
				}
			}
			
				
				
			
			
			if(flag)sb.append("YES").append("\n");
			else sb.append("NO").append("\n");
			
					
			str.clear();
		}
		System.out.println(sb);
	}
}
