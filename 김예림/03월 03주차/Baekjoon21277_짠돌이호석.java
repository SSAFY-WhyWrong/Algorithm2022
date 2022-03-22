package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon21277_짠돌이호석 {

	static int[][] puzzle, paint1, paint2, print;
	static int N1, M1, N2, M2, min, paintH, paintW;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N1 = Integer.parseInt(st.nextToken());
		M1 = Integer.parseInt(st.nextToken());
		puzzle = new int[150][150];
		paint1 = new int[N1][M1];
		for(int i=0; i<N1; i++) {
			String str = br.readLine();
			for(int j=0; j<M1; j++) {
				paint1[i][j] = str.charAt(j)-'0';
				puzzle[50+i][50+j] = str.charAt(j)-'0';
			}
		}
		paintH = 50+N1;
		paintW = 50+M1;
		
		st = new StringTokenizer(br.readLine()," ");
		N2 = Integer.parseInt(st.nextToken());
		M2 = Integer.parseInt(st.nextToken());
		paint2 = new int[N2][M2];
		for(int i=0; i<N2; i++) {
			String str = br.readLine();
			for(int j=0; j<M2; j++) {
				paint2[i][j] = str.charAt(j)-'0';
			}
		}
		
		
		
		//회전해서 4개의 방향으로 다 해보기
		//고정되는 퍼즐 -> 움직이는 퍼즐
		//고정되는 퍼즐의 시작 인덱스 : [50][50]
		//움직이는 퍼즐의 탐색 범위(출발지 중심) : [50-N2][50-M2] ~ [50+N1][50+M1]
		
		int[][] move1 = rotate(paint2);
		int[][] move2 = rotate(move1);
		int[][] move3 = rotate(move2);
		int[][] move4 = rotate(move3);
		
		min = Integer.MAX_VALUE;
		//paint2의 출발지
//		for(int i=0; i<100; i++) {
//			for(int j=0; j<100; j++) {
//				if(check(i,j,move1)==1) calculatePre(i,j,move1);
//				if(check(i,j,move2)==1) calculatePre(i,j,move2);
//				if(check(i,j,move3)==1) calculatePre(i,j,move3);
//				if(check(i,j,move4)==1) calculatePre(i,j,move4);
//			}
//		}
		System.out.println(check(51,51,move3));
		System.out.println(min);
	}
	
	
	private static void calculatePre(int i, int j, int[][] paint) {
		
		
		for(int x=0; x<paint.length; x++) {
			for(int y=0; y<paint[i].length; y++) {
				if(puzzle[x+i][y+j]==1) System.out.print(1);
				else if(paint[x][y]==1) System.out.print(2);
				else System.out.print(0);
			}
			System.out.println();
		}
		
		int height = paint.length;
		int width = paint[0].length;
		
		int h = 0;
		int w = 0;
		
		
		if(i+height <= 100) {
			//i = Math.min(i, 50);
			h = 100-i;
		}
		else {
			//if(height+i < 50+N1) h = 50+N1;
			h = (height+i)-50;
		}
		
		
		if(j+width <= 100) {
			//j = Math.min(50, j);
			w = 100-j;
		}else {
			w = (width+j)-50;
		}
		
//		if(h*w == 15) {
//			
//			for(int x=i; x<i+paint.length; x++) {
//				for(int y=j; y<j+paint[x-i].length; y++) {
//					System.out.print(puzzle[x][y]);
//					
//				}
//				System.out.println();
//			}
//			
//			for(int x=0; x<paint.length; x++) {
//				for(int y=0; y<paint[x].length; y++) {
//					if(puzzle[x+i][y+j]==1) System.out.print(1);
//					else if(paint[x][y]==1) System.out.print(2);
//					else System.out.print(0);
//				}
//				System.out.println();
//			}
//		}
		if(min > h*w) {
			min = Math.min(min, h*w);
			System.out.println("출발지 : "+i+","+j);		
		}
	}
	
	
	private static int check(int x, int y, int[][] paint) {
		
		
		int[][] print = new int[150][150];
		
		for(int i=0; i<paint.length; i++) {
			for(int j=0; j<paint[i].length; j++) {
				if(puzzle[x+i][y+j]==paint[i][j] && paint[i][j]==1) {
					//System.out.println("퍼즐의 "+(x+i)+","+(y+j)+"와 페인트의 "+i+","+j+"가 겹쳐요");
					return -1;
				}
				else if(puzzle[x+i][y+j]==1) print[x+i][y+i] = 1;
				else if(paint[i][j]==1) print[x+i][y+i] = 2;
			}
		}
		for(int i=50; i<100; i++) {
			for(int j=50; j<100; j++) {
				System.out.print(print[i][j]);
			}
			System.out.println();
		}
		
		//System.out.println("안 겹치네유~!");
		return 1;
	}

	public static int[][] rotate(int[][] cur) {
		int[][] next = new int[cur[0].length][cur.length];

		for(int i = 0; i < cur[0].length; i++) {
			for(int j = 0; j < cur.length; j++) {
				next[i][j] = cur[cur.length - 1 - j][i];
			}
		}
		return next;
	}

}
