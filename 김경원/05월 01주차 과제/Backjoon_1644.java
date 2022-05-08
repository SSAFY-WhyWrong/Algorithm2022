import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Backjoon_1644 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();


        boolean[] prime = new boolean[4000001];

        prime[0] = prime[1] = true;

        for(int i=2;i*i<=N;i++){
            if(!prime[i]){
                for(int j=i*i; j<=N; j+=i){
                    prime[j] = true;
                }
            }
        }

        List<Integer> list = new ArrayList<>();

        for(int i=2;i<=N;i++){
            if(!prime[i]){
                list.add(i);
            }
        }
        int i=0,j=0;
        int num = 2;
        int answer = 0;

        while(i>=j && i < list.size()){
            if(num < N){
                i++;
                if(i == list.size()){
                    break;
                }
                num += list.get(i);
            } else if (num >= N) {
                num -= list.get(j);
                j++;
            }

            if (num == N) {
                answer++;
            }

        }

        if(N == 2){
            answer = 2;
        }

        System.out.println(answer);


    }

}
