package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon2650_교차점개수 {

	static int cross, max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		ArrayList<Line> list = new ArrayList<>();
		int[] maxValue = new int[N/2]; //몇 개의 선분이 교차했는지 알려주는것
		cross = 0;
		max = 0;
		for(int i=0; i<N/2; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");

			int side1 = Integer.parseInt(st.nextToken());
			int point1 = Integer.parseInt(st.nextToken());
			int side2 = Integer.parseInt(st.nextToken());
			int point2 = Integer.parseInt(st.nextToken());

			Line cur;

			if(side1 < side2) cur = new Line(side1, point1, side2, point2);
			else if(side1 > side2) cur = new Line(side2, point2, side1, point1);
			else {
				//side1 == side2
				if(point1 < point2) cur = new Line(side1, point1, side2, point2);
				else cur = new Line(side1, point2, side2, point1);
			}
			list.add(cur);
			//System.out.println(cur.side1+","+cur.point1+"/"+cur.side2+","+cur.point2);

			int num = cur.side1*10 + cur.side2;
			//System.out.println(num);
			for(int j=0; j<i; j++) {
				//앞에 저장된 연결선들과 비교해서 교차점이 생기는지 확인하기
				int tmp = cross;
				check(num, cur, list.get(j));
				if(tmp != cross) {
					maxValue[i]++;
					maxValue[j]++;
				}
			}
		}
//		for(int i=0; i<maxValue.length; i++) {
//			System.out.print(maxValue[i]+" ");
//		}
//		System.out.println();
		Arrays.sort(maxValue);
		System.out.println(cross);
		System.out.println(maxValue[N/2-1]);
	}

	private static void check(int num, Line cur, Line comp) {

		switch (num) {
		case 12:
			case12(cur, comp);
			break;
		case 13:
			case13(cur, comp);
			break;
		case 14:
			case14(cur, comp);
			break;
		case 23:
			case23(cur, comp);
			break;
		case 24:
			case24(cur, comp);
			break;
		case 34:
			case34(cur, comp);
			break;
		default:
			same(cur, comp);
			break;
		}

	}

	private static void case12(Line cur, Line comp) { //기준 선, 비교하는 선(이미 그어져 있는 선)

		int n = cur.point1;
		int m = cur.point2;

		int side1 = comp.side1;
		int point1 = comp.point1;
		int side2 = comp.side2;
		int point2 = comp.point2;

		if(side1==1 && side2==2) {
			if(point1<n && point2>m) cross++;
			else if(point1>n && point2<m) cross++;
		}
		else if(side1==1 && side2==3 && point1>n) cross++;
		else if(side1==1 && side2==4 && point1<n) cross++;
		else if(side1==2 && side2==3 && point2>m) cross++;
		else if(side1==2 && side2==4 && point2<m) cross++;
		else if(side1==3 && side2==4) cross++;
		else if(side1==side2) {
			if(side1==1 && point1<n && point2>n) cross++;
			else if(side1==2 && point1<m && point2>m) cross++;
		}
	}

	private static void case13(Line cur, Line comp) {
		int n = cur.point1;
		int m = cur.point2;

		int side1 = comp.side1;
		int point1 = comp.point1;
		int side2 = comp.side2;
		int point2 = comp.point2;

		if(side1==1 && side2==2 && point1<n) cross++;
		else if(side1==1 && side2==3) {
			if(point1>n && point2<m) cross++;
			else if(point1<n && point2>m) cross++;
		}else if(side1==1 && side2==4 && point1<n) cross++;
		else if(side1==2 && side2==3 && point2<m) cross++;
		else if(side1==2 && side2==4) return;
		else if(side1==3 && side2==4 && point1<m) cross++;
		else if(side1==side2) {
			if(side1==1 && point1<n && point2>n) cross++;
			else if(side1==3 && point1<m && point2>m) cross++;
		}
	}

	private static void case14(Line cur, Line comp) {
		int n = cur.point1;
		int m = cur.point2;

		int side1 = comp.side1;
		int point1 = comp.point1;
		int side2 = comp.side2;
		int point2 = comp.point2;

		if(side1==1 && side2==2 && point1<n) cross++;
		else if(side1==1 && side2==3 && point1<n) cross++;
		else if(side1==1 && side2==4) {
			if(point1<n && point2<m) cross++;
			else if(point1>n && point2>m) cross++;
		}else if(side1==2 && side2==3) return;
		else if(side1==2 && side2==4 && point2<m) cross++;
		else if(side1==3 && side2==4 && point2<m) cross++;
		else if(side1==side2) {
			if(side1==1 && point1<n && point2>n) cross++;
			else if(side1==4 && point1<m && point2>m) cross++;
		}
	}

	private static void case23(Line cur, Line comp) {
		int n = cur.point1;
		int m = cur.point2;

		int side1 = comp.side1;
		int point1 = comp.point1;
		int side2 = comp.side2;
		int point2 = comp.point2;

		if(side1==1 && side2==2 && point2<n) cross++;
		else if(side1==1 && side2==3 && point2>m) cross++;
		else if(side1==1 && side2==4) return;
		else if(side1==2 && side2==3) {
			if(point1<n && point2<m) cross++;
			else if(point1>n && point2>m) cross++;
		}else if(side1==2 && side2==4 && point1<n) cross++;
		else if(side1==3 && side2==4 && point1>m) cross++;
		else if(side1==side2) {
			if(side1==2 && point1<n && point2>n) cross++;
			else if(side1==3 && point1<m && point2>m) cross++;
		}
	}

	private static void case24(Line cur, Line comp) {
		int n = cur.point1;
		int m = cur.point2;

		int side1 = comp.side1;
		int point1 = comp.point1;
		int side2 = comp.side2;
		int point2 = comp.point2;

		if(side1==1 && side2==2 && point2>n) cross++;
		else if(side1==1 && side2==3) return;
		else if(side1==1 && side2==4 && point2>m) cross++;
		else if(side1==2 && side2==3 && point1>n) cross++;
		else if(side1==2 && side2==4) {
			if(point1<n && point2>m) cross++;
			else if(point1>n && point2<m) cross++;
		}else if(side1==3 && side2==4 && point2>m) cross++;
		else if(side1==side2) {
			if(side1==2 && point1<n && point2>n) cross++;
			else if(side1==4 && point1<m && point2>m) cross++;
		}
	}

	private static void case34(Line cur, Line comp) {
		int n = cur.point1;
		int m = cur.point2;

		int side1 = comp.side1;
		int point1 = comp.point1;
		int side2 = comp.side2;
		int point2 = comp.point2;
		
		if(side1==1 && side2==2) cross++;
		else if(side1==1 && side2==3 && point2>n) cross++;
		else if(side1==1 && side2==4 && point2>m) cross++;
		else if(side1==2 && side2==3 && point2<n) cross++;
		else if(side1==2 && side2==4 && point2<m) cross++;
		else if(side1==3 && side2==4) {
			if(point1<n && point2>m) cross++;
			else if(point1>n && point1<m) cross++;
		}else if(side1==side2) {
			if(side1==3 && point1<n && point2>n) cross++;
			else if(side1==4 && point1<m && point2>m) cross++;
		}
	}

	private static void same(Line cur, Line comp) {

		int s = cur.side1;
		int n = cur.point1;
		int m = cur.point2;

		int side1 = comp.side1;
		int point1 = comp.point1;
		int side2 = comp.side2;
		int point2 = comp.point2;

		if(s==side1 && side1==side2) {
			if(point1<n && point2>n && point2<m) cross++;
			else if(point1>n && point1<m && point2>m) cross++;
		}else if(s==side1) {
			if(point1>n && point1<m) cross++;
		}else if(s==side2) {
			if(point2>n && point2<m) cross++;
		}
	}

	static class Line {
		int side1; //1 윗변, 2 밑변, 3 왼쪽 변, 4 오른쪽 변
		int point1; //출발점에서부터의 거리
		int side2; //side1 <= side2
		int point2;
		public Line(int side1, int point1, int side2, int point2) {
			this.side1 = side1;
			this.point1 = point1;
			this.side2 = side2;
			this.point2 = point2;
		}
	}
}

/*
20
1 1 2 10
1 2 2 9
1 3 2 8
1 4 2 7
1 5 2 6
3 1 4 10
3 2 4 9
3 3 4 8
3 4 4 7
3 5 4 6

45
9


10
3 5 4 97
1 7 2 8
2 4 2 7
2 6 4 4
1 5 3 3

4
3


10
3 5 4 7
1 7 2 8
2 4 2 7
2 6 4 4
1 5 3 3

4
3

20
1 1 2 10
1 2 3 9
1 3 4 8
2 4 3 7
2 5 4 6
3 1 4 10
1 4 1 9
2 3 2 8
3 4 3 8
4 5 4 7

11
5
*/
