import java.util.Stack;

class Solution {
    public String solution(int n, int k, String[] cmd) {
		Stack<Node> delete = new Stack<>();
		Node root = new Node(0);
		Node now = root;
        
		for (int i = 1; i < n; i++) {
			Node node = new Node(i);
			now.next = node;
			node.prev = now;
			now = node;
		}

		Node tail = now;
		root.prev = tail;
		tail.next = root;
		now = root;
        
		while (k-- > 0) {
			now = now.next;
		}
		
		for (int i = 0; i < cmd.length; i++) {
			String[] s = cmd[i].split(" ");
			if (s.length == 1) {
				if (s[0].equals("C")) {
					now.prev.next = now.next;
					now.next.prev = now.prev;
					delete.push(now);

					if (now == root) {
						root = now.next;
						now = root;  
					} else if (now == tail) {
						tail = now.prev;
						now = tail;
					}else {
						now = now.next;
					}
				} else {//되돌리기Z
					Node node = delete.pop();
					Node tmp = node.prev.next;
					node.prev.next = node;
					tmp.prev = node;
                    
					if(node.data < root.data)
						root = node;
					else if(node.data > tail.data)
						tail = node;
				}
			} else {
				int num = Integer.parseInt(s[1]);
				if (s[0].equals("U")) {
					while (num-- > 0) {
						now = now.prev;
					}
				} else {
					while (num-- > 0) {
						now = now.next;
					}
				}
			}
		}
		StringBuilder ans = new StringBuilder();
		
		for (int i = 0; i < n; i++) {
			if(root.data == i) {
				ans.append("O");
				root=root.next;
			}
			else {
				ans.append("X");
			}
		}
        return ans.toString();

	} 

	static class Node {
		Node prev;
		Node next;
		int data;

		public Node(int data) {
			this.data = data;
			this.prev = null;
			this.next = null;
		}
	}

}