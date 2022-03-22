package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Baekjoon9007_카누선수 {

	static int k, n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());

			int[][] student = new int[4][n];
			for (int i = 0; i < 4 ; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					student[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int[][] canoe = new int[2][n*n];
			int idx = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					canoe[0][idx] = student[0][i] + student[1][j];
					canoe[1][idx++] = student[2][i] + student[3][j];
				}
			}

			Arrays.sort(canoe[0]);
			Arrays.sort(canoe[1]);

			sb.append(k - find(canoe[0], canoe[1])+"\n");
		}

		System.out.println(sb);
	}

	public static int find(int[] first, int[] second) {

		int ans = 0;
		int min = Integer.MAX_VALUE;
		for (int i : first) {
			int result = Search(second, k-i);
			int abs = Math.abs(result);

			if (min > abs) {
				ans = result;
				min = abs;
			} else if (min == abs && ans < 0) {
				ans = result;
			}
		}

		return ans;
	}
	public static int Search(int[] arr, int num) {

		int result = 0;
		int min = Integer.MAX_VALUE;

		int left = 0;
		int right = arr.length - 1;

		while (left <= right) {
			int mid = (left + right)/2;

			int cur = num - arr[mid];
			int abs = Math.abs(cur);

			if (min > abs) {
				min = abs;
				result = cur;
			} else if (min == abs && result < 0) result = cur;

			if (cur > 0) left = mid + 1;
			else if (cur < 0) right = mid - 1;
			else return 0;

		}

		return result;
	}
}