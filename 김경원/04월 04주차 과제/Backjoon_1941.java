import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Backjoon_1941{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        char[][] arr = new char[5][5];

        for(int i=0;i<5;i++){
            String str = br.readLine();
            for(int j=0;j<5;j++){
                arr[i][j] = str.charAt(j);
            }
        }

        int[] choice = new int[7];

        int result = DFS(0,0,arr,choice);

        System.out.println(result);


    }

    static int DFS(int idx, int cnt, char[][] arr, int[] choice){

        int sum = 0;

        if(idx >=25) {
            if (cnt == 7) {
                int sCnt = 0;

                for (int i = 0; i < 7; i++) {

                    int y = choice[i] / 5;
                    int x = choice[i] % 5;

                    if (arr[y][x] == 'S') {
                        sCnt++;
                    }
                }

                if (sCnt < 4) {
                    return sum;
                }

                if(isPossible(choice)){
    
                    return 1;
                }

            }
            return 0;
        }


            if(cnt < 7){
                choice[cnt] = idx;
                sum += DFS(idx+1, cnt+1, arr, choice);
                choice[cnt] = -1;
            }
            sum += DFS(idx+1, cnt,arr, choice);

            return sum;

    }

    static boolean isPossible(int[] choice){

        int[][] visited = new int[5][5];
        int cnt = 1;
        for(int i=0;i<7;i++){
        	int y = choice[i]/5;
        	int x = choice[i]%5;
            visited[y][x] = 1;
        }

        visited[choice[0]/5][choice[0]%5] = 2;

        Queue<Integer> q = new LinkedList<>();

        q.add(choice[0]);


        int[] ni = {-1,1,0,0};
        int[] nj = {0,0,-1,1};

        while(!q.isEmpty()){
            int now = q.poll();

            for(int d=0;d<4;d++){
                int y = now/5 + ni[d];
                int x = now % 5 + nj[d];


                if(x < 0 || y < 0 || x >= 5 || y>=5 || visited[y][x]!=1){
                    continue;
                }

                visited[y][x]++;
                q.add(y*5 + x);
                cnt++;


            }
        }

        if(cnt == 7) {
            return true;
        }

        return false;

    }


}
