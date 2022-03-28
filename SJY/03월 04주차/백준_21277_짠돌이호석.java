import java.util.*;
import java.io.*;

public class 백준_21277_짠돌이호석 {

	static int N1, M1, N2, M2;
	static int[][] standard;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N1 = Integer.parseInt(st.nextToken());
		M1 = Integer.parseInt(st.nextToken());
		int[][] hPuzzle = new int[N1][M1];
		
		for(int i=0;i<N1;i++) {
			String tmp = bf.readLine();
			for(int j=0;j<M1;j++) {
				hPuzzle[i][j] = tmp.charAt(j) - '0';
			}
		}
		
		st = new StringTokenizer(bf.readLine());
		N2 = Integer.parseInt(st.nextToken());
		M2 = Integer.parseInt(st.nextToken());
		int[][] sPuzzle = new int[N2][M2];
		
		for(int i=0;i<N2;i++) {
			String tmp = bf.readLine();
			for(int j=0;j<M2;j++) {
				sPuzzle[i][j] = tmp.charAt(j) - '0';
			}
		}
		
		standard = new int[150][150];
		for(int i=0;i<N1;i++) {
			for(int j=0;j<M1;j++) {
				standard[i+50][j+50] = hPuzzle[i][j];
			}
		}
		
		int answer = Integer.MAX_VALUE;	
		for(int i=0;i<4;i++) {
			int[][] rsPuzzle = rotate(sPuzzle, i); // 변하는 퍼즐, hPuzzle이 고정
			int size = makePuzzle(rsPuzzle);
			answer = Math.min(size, answer);
		}
		
		System.out.println(answer);
	}

	private static int makePuzzle(int[][] compare) {
		int res = Integer.MAX_VALUE;
		
		for(int i=0;i<150-N2;i++) {
			for(int j=0;j<150-M2;j++) {
				if(isOver(i, j, compare)) continue;
				
				int maxX = Math.max(i+N2, 50+N1);
				int minX = Math.min(i, 50);
				int maxY = Math.max(50+M1, j+M2);
				int minY = Math.min(j, 50);
				int area = (maxY-minY) * (maxX-minX);
				res = Math.min(res, area);
			}
		}
		
		return res;
	}

	private static boolean isOver(int i, int j, int[][] compare) {
		for(int x=0;x<N2;x++) {
			for(int y=0;y<M2;y++) {
				if(standard[i+x][j+y] == 1 && compare[x][y] == 1) return true;
			}
		}
		return false;
	}

	private static void print(int[][] puzzle) {
		for(int i=0;i<puzzle.length;i++) {
			for(int j=0;j<puzzle[0].length;j++) {
				System.out.print(puzzle[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static int[][] rotate(int[][] puzzle, int cnt) {
		if(cnt == 0) return puzzle;
		
		int N = puzzle.length;
		int M = puzzle[0].length;
		int[][] res = new int[M][N];
		N2 = M; M2 = N;
		
		for(int i=0, a=0;i<M;i++, a++) {
			for(int j=N-1, b=0;j>=0;j--, b++) {
				res[a][b] = puzzle[j][i];
			}
		}

		
		return rotate(res, cnt-1);
	}

}

// 하나를 고정하고 한개를 전체 탐색 => 좌표값으로 넓이 계산하기
