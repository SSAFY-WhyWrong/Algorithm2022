import java.util.*;
import java.io.*;

public class 백준_8982_수족관1 {
	
	static class Point {
		int start;
		int depth;
		
		public Point(int start, int depth) {
			this.start = start;
			this.depth = depth;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(bf.readLine());
		int[] water = new int[40001];
		int[] origin = new int[40001];
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for(int i=0;i<N/2-1;i++) {
			st = new StringTokenizer(bf.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(bf.readLine());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			for(int j=x1;j<=x2;j++) {
				origin[j] = y1;
			}
		}
		st = new StringTokenizer(bf.readLine());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(bf.readLine());
		Point[] hole = new Point[K];
		
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(bf.readLine());
			hole[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		for(Point p : hole) {
			int nowDepth = p.depth;
			int nowIdx = p.start;
			
			for(int i=nowIdx;i>=0;i--) {
				nowDepth = Math.min(nowDepth, origin[i]);
				water[i] = Math.max(water[i], nowDepth);
			}
			
			nowDepth = p.depth;
			nowIdx = p.start;
			
			for(int i=nowIdx;i<M;i++) {
				nowDepth = Math.min(nowDepth, origin[i]);
				water[i] = Math.max(water[i], nowDepth);
			}
		}
		
		int ans = 0;
		for(int i=0;i<M;i++) {
			ans += origin[i] - water[i];
		}
		
		System.out.println(ans);
	}

}
