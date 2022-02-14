import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Backjoon_2261 {
	
	
	static public class Node implements Comparable<Node> {
		int i,j;
		
		Node(int i, int j){
			this.i = i;
			this.j = j;
		}
		
		@Override
		public int compareTo(Node o) {
			if(this.i == o.i) {
				return this.j - o.j;
			}
			return this.i - o.i;
		}
		
	}
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		Node[] point = new Node[N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			point[i] = new Node(Integer.parseInt(st.nextToken(" ")), Integer.parseInt(st.nextToken(" ")));
		}
		
		Arrays.sort(point);
		
		int start = 0;
		
		TreeSet<Node> set = new TreeSet<>((Node o1, Node o2)->{
			if(o1.j == o2.j) {
				return o1.i - o2.i;
			}
			
			return o1.j - o2.j;
		}
				
				
				);
		
		set.add(point[0]);
		set.add(point[1]);
		
		int answer = getDistance(point[0], point[1]);
		
		for(int i=2; i<N; i++) {
			
			while(start < i) {
				int y = point[start].i - point[i].i;
				
				if(y * y <= answer) {
					break;
				}
				
				set.remove(point[start]);
				start++;
				
			}
			
			
			int distance = (int) Math.sqrt((double) answer) + 1;
			

			TreeSet<Node> lowerSet = (TreeSet<Node>) set.subSet(new Node(-100000, point[i].j - distance), new Node(100000, point[i].j + distance));
			
			for(Node node : lowerSet) {
				distance = getDistance(point[i],node);
				answer = Math.min(answer, distance);
			}
			
			
			set.add(point[i]);
		}
		
		
		
		System.out.println(answer);
		
	}
	
	
	static public int getDistance(Node p1, Node p2) {
		return (p1.i - p2.i)*(p1.i - p2.i) + (p1.j - p2.j)*(p1.j - p2.j);
	}
	
}
