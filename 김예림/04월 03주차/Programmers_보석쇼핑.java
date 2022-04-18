package AlgorithmStudy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Programmers_보석쇼핑 {
	public static void main(String[] args) {
		
		String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
		
		int[] answer = solution(gems);
		for(int i=0; i<answer.length; i++) {
			System.out.print(answer[i]+" ");
		}
		
	}
	public static int[] solution(String[] gems) {
		
        Queue<String> q = new LinkedList<>();
        HashSet<String> set = new HashSet<>(); //hashset은 중복없이 값을 저장해준다.
        HashMap<String, Integer> map = new HashMap<>();
        int start = 0;
        int end = Integer.MAX_VALUE;
        
        for(String s : gems) {
        	set.add(s);
        }

        int startPoint = 0;
        for(int i = 0; i < gems.length; i++) {
        	if(!map.containsKey(gems[i])) {
        		map.put(gems[i],1);
        	}else map.replace(gems[i], map.get(gems[i])+1);
            
            q.add(gems[i]);
            
            while(true) {
                String temp = q.peek();
                if(map.get(temp) > 1) {
                    q.poll();
                    start++;
                    map.put(temp, map.get(temp) -1);
                }else {
                    break;
                }
            }
            //보석을 모두 고르거나 뒤의 end가 최소값인 경우
            if(map.size() == set.size() && end > q.size()) {
                end = q.size();
                startPoint = start;
            }
            
            
        }
        int[] answer = new int[2];
        answer[0] = startPoint+1;
        answer[1] = startPoint+end;
        return answer;
        
    }
}
