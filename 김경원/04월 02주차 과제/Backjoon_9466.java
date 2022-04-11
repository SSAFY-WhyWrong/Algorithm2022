import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon_9466 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T;

        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int t=0;t<T;t++){
            int N;
            int count = 0;
            N = Integer.parseInt(br.readLine());

            int[] arr = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++){
                arr[i] = Integer.parseInt(st.nextToken(" ")) - 1;
            }

            boolean[] isUsed = new boolean[N];
            boolean[] isDone = new boolean[N];

            for(int i=0;i<N;i++){
                count += DFS(i,isUsed, isDone,arr);
            }

            sb.append(N-count).append('\n');

        }
        System.out.println(sb);
    }

    static int DFS(int now, boolean[] isUsed, boolean[] isDone, int[] arr){

        if(isUsed[now]){
            return 0;
        }

        isUsed[now] = true;

        int next = arr[now];
        int count = 0;
        if(!isUsed[next]){
            count += DFS(next,isUsed, isDone,arr);
        }else if(!isDone[next]){
            count++;
            for(int i = next; i != now; i=arr[i]) {
                count++;
            }
        }

        isDone[now] = true;

        return count;

    }

}
