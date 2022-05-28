import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Backjoon_20947 {

    static class Node implements Comparable<Node>{
        int i,j;
        char token;

        Node(int i,int j, char token){
            this.i = i;
            this.j = j;
            this.token = token;
        }

        @Override
        public int compareTo(Node o) {
            if(o.token == 'X')
                return 1;
            else
                return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] map = new String[N];

        for(int i=0;i<N;i++){
            map[i] = br.readLine();
        }
        PriorityQueue<Node> q = new PriorityQueue<>();


        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++) {
                char token = map[i].charAt(j);

                if(token != '.'){
                    q.add(new Node(i,j,token));
                }
            }
        }

        int[] ni = {-1,1,0,0};
        int[] nj = {0,0,-1,1};

        char[][] result = new char[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                result[i][j] = '.';
            }
        }

        while(!q.isEmpty()){
            Node now = q.poll();
            result[now.i][now.j] = now.token;
            char token = 'B';
            if(now.token == 'O'){
                token = '.';
            }

            for(int d=0;d<4;d++){
                int y = now.i;
                int x = now.j;

                y+= ni[d];
                x+= nj[d];
                while(!(x < 0 || y<0 || x>= N || y>= N)){

                    if(map[y].charAt(x) == '.'){
                        result[y][x] = token;
                    }else{
                        break;
                    }

                    y+= ni[d];
                    x+= nj[d];


                }
            }


        }

        StringBuilder sb = new StringBuilder();

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                sb.append(result[i][j]);
            }
            sb.append('\n');
        }

        System.out.print(sb);


    }
}
