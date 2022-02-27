import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon_8982 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] discharge = new int[50000];

		int[] height = new int[50000];

		int lastX = 0, lastY = 0;

		int endX = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x, y;
			x = Integer.parseInt(st.nextToken(" "));
			y = Integer.parseInt(st.nextToken(" "));

			for (int j = lastX + 1; j <= x; j++) {
				height[j] = lastY;
			}

			lastX = x;
			lastY = y;

		}
		
		
		
		
		int K = Integer.parseInt(br.readLine());

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			int x1, y1, x2, y2;

			x1 = Integer.parseInt(st.nextToken(" "));
			y1 = Integer.parseInt(st.nextToken(" "));
			x2 = Integer.parseInt(st.nextToken(" "));
			y2 = Integer.parseInt(st.nextToken(" "));

			dischargeWater(x1, x2, lastX, y1, discharge, height);

		}

		int answer = 0;

		for (int i = 1; i <= lastX; i++) {
			answer += (height[i] - discharge[i]);
//			System.out.println(water[i]);
		}

		System.out.println(answer);

	}

	static void dischargeWater(int x1, int x2, int lastX, int y, int[] discharge, int[] height) {
		
		int h = y;
		
		for (int i = x1; i > 0; i--) {
			h = Math.min(height[i], h);
			discharge[i] = Math.max(h,discharge[i]);
		}

		h = y;
		
		for (int i = x1 + 1; i <= lastX; i++) {
			h = Math.min(height[i], h);
			discharge[i] = Math.max(h,discharge[i]);
		}

	}

}
