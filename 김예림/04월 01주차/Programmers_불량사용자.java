package AlgorithmStudy;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Programmers_불량사용자 {
	public static void main(String[] args) {
//		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
//		String[] banned_id = {"fr*d*", "abc1**"};
//		
		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id = {"*rodo", "*rodo", "******"};
//		
//		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
//		String[] banned_id = {"fr*d*", "*rodo", "******", "******"};
		
		
		System.out.println(solution(user_id, banned_id));
	}
	
	static int tot, len1, len2, bit;
	static ArrayList<Integer>[] list;
	static ArrayList<Integer> numbers;
	
	public static int solution(String[] user_id, String[] banned_id) {
		
		len1 = user_id.length;
		len2 = banned_id.length;
		list = new ArrayList[len2];
		numbers = new ArrayList<>();
		
		for(int i=0; i<len2; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<len2; i++) {
			String str = banned_id[i].replaceAll("[*]", "."); //정규표현식 문장으로 변환
			
			for(int j=0; j<len1; j++) {
				if(Pattern.matches(str, user_id[j])) { 
					list[i].add(j); //i번째의 제재아이디에 포함되는 아이디의 idx 넣기
				}
			}
		}

		for(int i=0; i<len2; i++) {
			bit = 0;
			dfs(i, 0);
		}
        return tot;
    }
	
	private static void dfs(int start, int cnt) {
		
		if(start == len2) {
			if(cnt==len2 && !numbers.contains(bit)) {
				tot++;
				numbers.add(bit);
			}
			return;
		}
		
		for(int i=0; i<list[start].size(); i++) {
			int check = list[start].get(i);
			
			if((bit&(1<<check))==0) {
				bit|=(1<<check); //check번째 비트 켜기
				dfs(start+1, cnt+1);
				bit&=~(1<<check); //check번째 비트 끄기
			}
		}
	}
}
