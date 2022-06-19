import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        int minFriends = 0, maxFriends = 200000000; // 친구수
        
        while(minFriends <= maxFriends) {
            int mid = (minFriends + maxFriends) / 2;
            
            int notCross = 0; // 건널 수 없는 돌
            for(int stone : stones) {
                if(stone < mid) {
                    notCross++;
                } else {
                    notCross = 0;
                }
                
                if(notCross >= k) {
                    notCross = -1;
                    break;
                }
            }
            
            if(notCross == -1) {
                maxFriends = mid - 1;
            } else {
                minFriends = mid + 1;
                answer = Math.max(answer, mid);
            }
        }
        
        return answer;
    }
}