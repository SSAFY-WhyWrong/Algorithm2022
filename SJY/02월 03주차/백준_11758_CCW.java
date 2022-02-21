import java.util.Scanner;

public class 백준_11758_CCW {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x1 = sc.nextInt();
		int y1 = sc.nextInt();
		int x2 = sc.nextInt();
		int y2 = sc.nextInt();
		int x3 = sc.nextInt();
		int y3 = sc.nextInt();
		
		System.out.println(ccw(x1, y1, x2, y2, x3, y3));

	}
	
	public static int ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
		int a = x1 * y2 + x2 * y3 + x3 * y1;
		int b = y1 * x2 + y2 * x3 + y3 * x1;
		
		if(a-b < 0) {
			return -1;
		} else if(a-b == 0) {
			return 0;
		}
		
		return 1;
	}

}
