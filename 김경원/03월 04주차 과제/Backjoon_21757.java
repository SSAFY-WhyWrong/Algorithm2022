import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon_21757 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N;
        N = Integer.parseInt(br.readLine());

        long answer = 0;

        long[] sum = new long[N+1];
        long[][] dp = new long[5][N+1];
        st = new StringTokenizer(br.readLine());

        for(int i=1;i<=N;i++){
            sum[i] = Integer.parseInt(st.nextToken());
            sum[i] += sum[i-1];
        }

        if(sum[N] % 4 == 0) {

            dp[0][0] = 1;
            for (int i = 1; i <= 4; i++) {
                for (int j = 1; j <= N; j++) {
                    dp[i - 1][j] += dp[i - 1][j - 1];
                }

                for (int j = 1; j <= N; j++) {
                    if (sum[j] != sum[N] / 4 * i) continue;
                    dp[i][j] = dp[i - 1][j - 1];
                }

            }

            answer = dp[4][N];
        }
        System.out.println(answer);
    }
}
