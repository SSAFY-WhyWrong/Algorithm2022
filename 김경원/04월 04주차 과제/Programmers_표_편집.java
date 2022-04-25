import java.util.Stack;

public class Programmers_표_편집 {
	public static void main(String[] args) {
		
		int n = 8;
		int k = 2;
		String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
		
		System.out.println(solution(n, k, cmd));
		
	}
	static class Node{
		int up, down;
		boolean isActive;
		
		Node(int up, int down, boolean isActive){
			this.up = up;
			this.down = down;
			this.isActive = isActive;
		}
	}
	
    static public String solution(int n, int k, String[] cmd) {
        String answer = "";
        
        int cmdCnt = cmd.length;
        
        Node[] table = new Node[n];
        
        for(int i=0;i<n;i++) {
        	table[i] = new Node(i-1,i+1,true);
        }
        
        
        Stack<Integer> deleted = new Stack<>();
        
        
        for(int t=0;t<cmdCnt;t++) {

        	char token = cmd[t].charAt(0);
        	int num;
        	
        	switch(token) {
        	
        	
        	case 'U':
        		num = Integer.parseInt(cmd[t].substring(2));
        		
        		for(int i=0;i<num;i++) {
        			k = table[k].up;
        		}
        		
        		break;
        		
        	case 'D':
        		num = Integer.parseInt(cmd[t].substring(2));
        		for(int i=0;i<num;i++) {
        			k = table[k].down;
        		}
        		
        		break;
        		
        	case 'C':
        		if(table[k].up >= 0) {
        			table[table[k].up].down = table[k].down;
        		}
        		if(table[k].down < n) {
        			table[table[k].down].up = table[k].up;
        		}
        		deleted.add(k);
        		table[k].isActive = false;
        		if(table[k].down < n && table[table[k].down].isActive) {
        			k = table[k].down;
        		}else {
        			k = table[k].up;
        		}
        		
        		break;
        		
        		
        	case 'Z':
        		
        		num = deleted.pop();
        		
        		if(table[num].up >= 0) {        			
        			table[table[num].up].down = num;
        		}
        		if(table[num].down < n) {        			
        			table[table[num].down].up = num;
        		}
        		table[num].isActive = true;
        		
        		
        		break;
        	
        	
        	}
        	
        	
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++) {
        	if(table[i].isActive) {
        		sb.append("O");
        	}
        	else {
        		sb.append("X");
        	}
        }
        
        
        return sb.toString();
    }
}
