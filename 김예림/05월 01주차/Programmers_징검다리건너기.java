package AlgorithmStudy;

public class Programmers_징검다리건너기 {
	public static void main(String[] args) {
		
		int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
		int k = 3;
		
		System.out.println(solution(stones, k));
		
	}
	static int[] rocks;
	
    public static int solution(int[] stones, int k) {
    	
    	rocks = new int[stones.length];
    	for(int i=0; i<stones.length; i++) {
    		rocks[i] = stones[i];
    	}
    	
    	int min = 1;
    	int max = Integer.MIN_VALUE;
    	int mid = 0;
    	boolean flag = true;
    	
    	
    	for(int i=0; i<rocks.length; i++) {
    		min = Math.min(rocks[i], min);
    		max = Math.max(rocks[i], max);
    	}
    	
    	while(flag ||  min <= max) {
    		
    		mid = (min+max)/2;
    		if(Check(mid,k)) { //다리를 건널 수 있는지 확인하기
    			min = mid+1;
    			flag = false;
    		}
    		else {
    			max = mid-1;
    			flag = true;
    		}
    	}
    	return mid; 
    }
    
    public static boolean Check(int mid, int k) {
    	
    	int cnt = k;
    	boolean pos = true;
    	
    	for(int i=0; i<rocks.length; i++) {
    		if(rocks[i]-mid < 0) {
    			cnt--;
    			if(cnt == 0) {
    				pos = false;
    				break;
    			}
    		}
    		else cnt = k;
    	}
    	return pos;
    }
}
