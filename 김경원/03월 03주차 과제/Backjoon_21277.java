import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon_21277 {
	static int N1, M1, N2, M2;
	static int[][] arr1;
	static int[][] arr2;
	static int[][] frame;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N1 = Integer.parseInt(st.nextToken(" "));
		M1 = Integer.parseInt(st.nextToken(" "));

		arr1 = new int[N1][M1];

		for (int i = 0; i < N1; i++) {
			String str = br.readLine();
			for (int j = 0; j < M1; j++) {
				arr1[i][j] = str.charAt(j) - '0';
			}
		}

		st = new StringTokenizer(br.readLine());

		N2 = Integer.parseInt(st.nextToken(" "));
		M2 = Integer.parseInt(st.nextToken(" "));

		arr2 = new int[N2][M2];

		for (int i = 0; i < N2; i++) {
			String str = br.readLine();
			for (int j = 0; j < M2; j++) {
				arr2[i][j] = str.charAt(j) - '0';
			}
		}

		frame = new int[150][150];

		for (int i = 0; i < N1; i++) {
			for (int j = 0; j < M1; j++) {
				frame[i + 50][j + 50] = arr1[i][j];
			}
		}

		int x1, x2, y1, y2;
		int answer = 10000;

		for (int d = 0; d < 4; d++) {
			arr2 = rotate(arr2);
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					if (isPossible(i, j)) {
						y1 = Math.min(i, 50);
						y2 = Math.max(i + N2 - 1, N1 + 49);
						x1 = Math.min(j, 50);
						x2 = Math.max(j + M2 - 1, M1 + 49);

						int area = (x2 - x1 + 1) * (y2 - y1 + 1);
						answer = Math.min(answer, area);
					}
				}
			}
		}

		System.out.println(answer);

	}

	static int[][] rotate(int[][] arr) {

		int[][] tmp = new int[50][50];

		for (int i = M2 - 1; i >= 0; i--) {
			for (int j = 0; j < N2; j++) {
				tmp[M2 - 1 - i][j] = arr[j][i];
			}
		}
		

		int t = M2;
		M2 = N2;
		N2 = t;

		return tmp;
	}

	static boolean isPossible(int y, int x) {

		for (int i = 0; i < N2; i++) {
			for (int j = 0; j < M2; j++) {
				if (frame[i + y][j + x] == 1 && arr2[i][j] == 1) {
					return false;
				}
			}
		}

		return true;
	}
}
