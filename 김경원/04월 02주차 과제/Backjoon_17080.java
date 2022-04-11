import java.util.Scanner;

public class Backjoon_17080 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N;

        N = sc.nextInt();

        if(N % 4 == 3){
            System.out.println(2);
        }else{
            System.out.println(1);
        }



    }
}
