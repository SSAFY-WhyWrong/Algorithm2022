import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Programmers_브라이언의_고민 {
	public static void main(String[] args) {
		String sentence = "eBfBe";
		String answer = solution(sentence);
		System.out.println(answer);
	}
	
	static final String INVALID = "invalid";
	static public String solution(String sentence) {
	        String answer = "";
	        
	        Map<Character, List<Integer>> symbolPos = new LinkedHashMap<>();
	        StringBuilder sb = new StringBuilder();
	        
	        
	        //특수기호가 들어간 위치 확인
	        for(int i=0;i<sentence.length();i++) {
	        	char token = sentence.charAt(i);
	        	if(isSmall(token)) {
	        		
	        		if(!symbolPos.containsKey(token)) {
	        			symbolPos.put(token, new ArrayList<>());
	        		}
	        		
	        		symbolPos.get(token).add(i);
	        		
	        	}
	        }
	        
	        
	        int idx = 0;
	        int preWordStart = -1, preWordEnd = -1;
	        int preStart = -1, preEnd = -1;
	        
	        
	        for(List<Integer>pos : symbolPos.values()) {
	        	System.out.println(sb.toString());
	        	int rule = -1;
	        	int count = pos.size();
	        	
	        	int start = pos.get(0);
	        	int end = pos.get(count -1);
	        	
	        	if(count == 1 || count >=3) {
	        		for(int i=1;i<count;i++) {
	        			//규칙1인데 간격 2가 아닌 경우
	        			if(pos.get(i) - pos.get(i-1) != 2) {
	        				return INVALID;
	        			} 			
	        		}
	        		rule = 1;	        			
	        	}
	        	
	        	if(count == 2) {
	        		if(end - start == 2 && (start > preStart && end < preEnd)) {
	        			rule = 1;
	        		}else if(end - start >=2) {
	        			rule = 2;
	        		}else {
	        			return INVALID;
	        		}
	        	}
	        	
	        	int wordStart = preWordEnd;
	        	int wordEnd = preWordEnd;
	        	
	        	System.out.println(rule);
	        	
	        	if(rule ==1) {
	        		wordStart = start - 1;
	        		wordEnd = end + 1;
	        		
	        		//조건1 확인하는데 문장 범위 벗어나는 경우
	        		if(wordStart < 0 || wordEnd >= sentence.length()) {
	        			return INVALID;
	        		}
	        		
	        		//전에 확인한 단어랑 겹치는 경우
	        		if(wordStart > preWordStart && wordEnd < preWordEnd) {
	        			if(start - preStart == 2 && preEnd - end == 2) {
	        				continue;
	        			}
	        			
	        			return INVALID;
	        		}
	        		
	        	}else if(rule ==2) {
	        		wordStart = start;
	        		wordEnd = end;
	        		
	        		if(wordStart > preWordStart && wordEnd < preWordEnd) {
	        			return INVALID;
	        		}
	        	}
	        	
	        	if(preWordEnd >= wordStart) return INVALID;
	        	
	        	//기호를 가지지 않는 순수 단어들 추가
	        	if(idx < wordStart) {
	        		sb.append(sentence.substring(idx, wordStart)).append(" ");
	        	}
	        	
	        	sb.append(sentence.substring(wordStart,wordEnd+1).replaceAll("[a-z]", "")).append(" ");
	        	
	        	preWordStart = wordStart;
	        	preWordEnd = wordEnd;
	        	preStart = start;
	        	preEnd = end;
	        	idx = wordEnd+1;
	        	
	        	
	        }
	        
	        if(idx < sentence.length()) {
	        	sb.append(sentence.substring(idx,sentence.length())).append(" ");
	        }
	        
	        
	        return answer = sb.toString().trim();
	    }
	
		static boolean isSmall(char c) {
			return c >= 'a' && c <='z' ? true : false;
		}
		

		
		
		
	
}
