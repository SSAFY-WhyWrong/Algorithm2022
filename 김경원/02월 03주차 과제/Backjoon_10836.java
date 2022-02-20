import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon_10836 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N,M;
        int[] num = new int[3];
        long[][] arr;
        int[] change;

        st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken(" "));
        N = Integer.parseInt(st.nextToken(" "));

        arr = new long[M][M];
        change = new int[M*2];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++){
                num[j] = Integer.parseInt(st.nextToken(" "));
            }
            change[num[0]]++;
            change[num[0] + num[1]]++;

        }
        int sum = 0;
        int idx = 0;
        for(int i=M-1;i>=0;i--){
            sum += change[idx++];
            arr[i][0] = 1 + sum;
        }

        for(int j=1;j<M;j++){
            sum += change[idx++];
            arr[0][j] = 1 + sum;
        }

        for(int i=1;i<M;i++){
            for(int j=1;j<M;j++){
                arr[i][j] = arr[0][j];
            }
        }



        StringBuilder sb = new StringBuilder();

        for(int i=0;i<M;i++){
            for(int j=0;j<M;j++){
                sb.append(arr[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }




}
