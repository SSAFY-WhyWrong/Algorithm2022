import java.io.*;
import java.util.*;

public class 백준_17837_새로운게임2 {

	static int N, K;
	static int[][] board;
	static ArrayList<Integer>[][] pos; // 0번으로 갈수록 아래에 있고, 번호가 커질수록 위에 있음
	static Piece[] piece;
	
	static class Piece {
		int x, y;
		int dir;
		
		public Piece(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Piece [x=" + x + ", y=" + y + ", dir=" + dir + "]";
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);

		board = new int[N + 1][N + 1];
		piece = new Piece[K + 1];
		pos = new ArrayList[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			input = br.readLine().split(" ");
			for (int j = 1; j <= N; j++) {
				board[i][j] = Integer.parseInt(input[j - 1]);
				pos[i][j] = new ArrayList<>();
			}
		}

		for (int i = 1; i <= K; i++) {
			input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0]);
			int y = Integer.parseInt(input[1]);
			int d = Integer.parseInt(input[2]);
			piece[i] = new Piece(x, y, d);
			pos[x][y].add(i);
		}

		System.out.println(solve());

	}

	public static int solve() {

		int[] dx = { 0, 0, 0, -1, 1 };
		int[] dy = { 0, 1, -1, 0, 0 };

		int cnt = 0;

		while (true) {

			if (cnt > 1000)
				return -1;

			cnt++;

			for (int i = 1; i <= K; i++) {

				Piece m = piece[i];

				int nx = m.x + dx[m.dir];
				int ny = m.y + dy[m.dir];

				if ((nx < 1 || nx > N) || (ny < 1 || ny > N) || board[nx][ny] == 2) {
					int d = changeDirect(m.dir); // 방향 반대로 바꿈
					nx = m.x + dx[d]; // 방향 바뀐 후, 반대쪽으로 한칸 이동한 위치
					ny = m.y + dy[d];

					m.dir = d;
				}

				if ((1 <= nx && nx <= N) && (1 <= ny && ny <= N) && board[nx][ny] == 0) {

					List<Integer> tmp = new ArrayList<>();
					boolean flag = false;

					for (int n : pos[m.x][m.y]) { // 원래 위치에서 vector에서 저장된 아래에 말부터 꺼냄
						if (n == i)
							flag = true;

						if (flag) {
							pos[nx][ny].add(n);
							tmp.add(n);
						}
					}

					for (int n : tmp) {
						pos[piece[n].x][piece[n].y].remove((Integer) n);
						piece[n].x = nx;
						piece[n].y = ny;
					}
				} else if ((1 <= nx && nx <= N) && (1 <= ny && ny <= N) && board[nx][ny] == 1) {

					List<Integer> tmp = new ArrayList<>();

					for (int j = pos[m.x][m.y].size() - 1; j >= 0; j--) {
						int n = pos[m.x][m.y].get(j);

						pos[nx][ny].add(n);
						tmp.add(n);

						if (n == i)
							break;
					}

					for (int n : tmp) {
						pos[piece[n].x][piece[n].y].remove((Integer) n);
						piece[n].x = nx;
						piece[n].y = ny;
					}
				}
				for (int k = 1; k <= K; k++) {
					if (pos[piece[k].x][piece[k].y].size() >= 4)
						return cnt;
				}
			}
		}

	}

	public static int changeDirect(int i) {

		if (i == 1)
			return 2;
		else if (i == 2)
			return 1;
		else if (i == 3)
			return 4;
		else
			return 3;
	}

}