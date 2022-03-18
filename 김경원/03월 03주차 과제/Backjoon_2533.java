import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Backjoon_2533 {
    static boolean[] visited;
    static List<Integer>[] edge;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N;

        N = Integer.parseInt(br.readLine());


        dp = new int[N+1][2];
        edge = new ArrayList[N+1];
        visited = new boolean[N+1];
        for(int i=1;i<=N;i++){
            edge[i] = new ArrayList<>();
        }


        for(int i=0;i<N-1;i++){
            int from, to;
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            edge[from].add(to);
            edge[to].add(from);

        }

        DFS(1);
        System.out.println(Math.min(dp[1][0],dp[1][1]));



    }

    static void DFS(int idx){
        visited[idx] = true;
        dp[idx][0] = 1;

        for(int next : edge[idx]){
            if(visited[next]) continue;
            DFS(next);
            dp[idx][1] += dp[next][0];
            dp[idx][0] += Math.min(dp[next][1],dp[next][0]);
        }

    }


}
