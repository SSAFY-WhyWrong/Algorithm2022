package AlgorithmStudy;

import java.util.Stack;
import java.util.StringTokenizer;

public class Programmers_표편집 {
	public static void main(String[] args) {
		
//		int n = 8;
//		int k = 2;
//		String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};
		//OOOOXOOO
		
		int n = 8;
		int k = 2; 
		String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
		//OOXOXOOO
		
		System.out.println(solution(n, k, cmd));
		
	}
	
	public static String solution(int n, int k, String[] cmd) {

        int size = n;
		Stack<Integer> stack = new Stack<>();		
		int idx = k;
		for(int i=0; i<cmd.length; i++) {
			StringTokenizer st = new StringTokenizer(cmd[i]," ");
			String act = st.nextToken();
			
			int cnt = 0;
			switch (act) {
			case "U":
				cnt = Integer.parseInt(st.nextToken());
				idx -= cnt;
				break;
			case "D":
				cnt = Integer.parseInt(st.nextToken());
				idx += cnt;
				break;
			case "C":
				stack.add(idx);
                size--;
                    
				if(size==idx) idx--;
				break;
			case "Z":
				int num = stack.pop();
                if(num<=idx) idx++;    
                size++;
				break;
			}
		}
		StringBuilder sb = new StringBuilder();
        for(int i=0; i<size; i++){
            sb.append("O");
        }
		while(!stack.isEmpty()) {         
            sb.insert(stack.pop(),"X");
		}
        return sb.toString();
    }
}
