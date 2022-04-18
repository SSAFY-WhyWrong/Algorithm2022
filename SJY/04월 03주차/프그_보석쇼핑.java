import java.util.*;

public class 프그_보석쇼핑 {

	public static void main(String[] args) {
		Solution s = new Solution();
		String[] gems = new String[] {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
		int[] ans = s.solution(gems);
		System.out.println(Arrays.toString(ans));
	}
	
	static class Solution {
	    public int[] solution(String[] gems) {
	        int[] answer = new int[2];
	        int start = 0, end = 0;
	        int length = gems.length + 1;
	        HashMap<String, Integer> buy = new HashMap<>();
	        
	        for(int i=0, j=0;i<gems.length;i++) {
	            if(!buy.containsKey(gems[i])) {
	                buy.put(gems[i], j);
	                j++;
	            }
	        }
	        
	        int target = buy.size();
	        buy = new HashMap<>();

	        while(end < gems.length) {
	            if(!buy.containsKey(gems[end])) {
	                buy.put(gems[end], 1);
	            } else {
	                int cnt = buy.get(gems[end]);
	                buy.put(gems[end], cnt+1);
	            }
	            end++;
	            
	            if(buy.size() == target) {
	                while(start < end) {
	                    if(buy.get(gems[start]) > 1) {
	                        int tmp = buy.get(gems[start]);
	                        buy.put(gems[start], tmp-1);
	                        start++;
	                    } else if(length > end - start) {
	                        length = end - start;
	                        answer[0] = start + 1;
	                        answer[1] = end;
	                        break;
	                    } else {
	                        break;
	                    }
	                }
	            }
	        }
	        
	        return answer;
	    }
	}
}
