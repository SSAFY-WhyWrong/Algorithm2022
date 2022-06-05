import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class Backjoon_7490 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T;

        T = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        Vector<Character> v = new Vector<>();

        for(int t=0;t<T;t++) {
            int N;

            N = Integer.parseInt(br.readLine());
            DFS(N,1,v, answer);
            answer.append('\n');

        }

        System.out.println(answer);


    }

    static void DFS(int N, int now, Vector<Character> v, StringBuilder sb){

        if(now == N){

            int num = 1;
            int sum = 0;
            int temp = 1;
            StringBuilder exp = new StringBuilder();
            exp.append(1);

            for(Character c : v){
                num++;
                exp.append(c);
                exp.append(num);
                if(c == ' '){
                    temp = temp *10;
                    if(temp < 0){
                        temp -= num;
                    }else{
                        temp += num;
                    }
                }else if(c == '-'){
                    sum += temp;
                    temp = -num;
                }else if(c == '+'){
                    sum += temp;
                    temp = num;
                }
            }

            sum += temp;
            if(sum == 0){
                sb.append(exp).append('\n');
            }



            return;
        }

        v.add(' ');
        DFS(N, now+1, v, sb);
        v.remove(now-1);

        v.add('+');
        DFS(N, now+1, v, sb);
        v.remove(now-1);

        v.add('-');
        DFS(N, now+1, v, sb);
        v.remove(now-1);




    }


}
