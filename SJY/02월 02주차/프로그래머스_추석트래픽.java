import java.util.*;

class Solution {
    public int solution(String[] lines) {
        int answer = 0;
        int[] start = new int[lines.length];
        int[] end = new int[lines.length];
        
        for(int i=0;i<lines.length;i++) {
            String[] splitTime = lines[i].split(" ");

            end[i] = makeTime(splitTime[1]);
            start[i] = startTime(end[i], splitTime[2]);
        }
        
        for(int i=0;i<lines.length;i++) {
            int standard = end[i];
            int cnt = 1;
            for(int j=i+1;j<lines.length;j++) { 
                // 오름차순 정렬되어 있기 때문에 end[j]와 standard를 비교할 필요가 없음
                
                if(start[j] >= standard && start[j] < standard + 1000) {
                    cnt++;
                } else if(start[j] <= standard && end[j] > standard + 1000) {
                    cnt++;
                } else if(end[j] < standard + 1000) {
                    cnt++;
                }
            }
            
            answer = Math.max(answer, cnt);
        }
        
        return answer;
    }
    
    public int makeTime(String time) {
        int res = 0;
        
        int hour = Integer.parseInt(time.substring(0, 2)) * 3600;
        int min = Integer.parseInt(time.substring(3, 5)) * 60;
        int sec = Integer.parseInt(time.substring(6, 8));
        
        res += (hour + min + sec) * 1000 + Integer.parseInt(time.substring(9));
        
        return res;
    }
    
    public int startTime(int end, String during) {
        int res = end;
        double duringTime = Double.parseDouble(during.substring(0, during.length()-1)) * 1000;
        
        res -= (int)duringTime;
        res += 1;
        
        if(res < 0) {
            res = 0;
        }
        return res;

    }
}