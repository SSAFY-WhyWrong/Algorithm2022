import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Backjoon_20666 {

    static class Node implements Comparable<Node>{
        int idx;
        long weight;
        Node(int idx,long weight){
            this.idx = idx;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {

            if(this.weight < o.weight){
                return -1;
            }

            return 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        st = new StringTokenizer(br.readLine());

        int N,M,P;
        long answer = 0;


        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        long[] difficult = new long[N];


        st = new StringTokenizer(br.readLine());
        List<Node>[] tip = new ArrayList[N];

        for(int i=0;i<N;i++){
            difficult[i] = Long.parseLong(st.nextToken());
            tip[i] = new ArrayList<>();
        }

        P = Integer.parseInt(br.readLine());



        for(int i=0;i<P;i++){
            st = new StringTokenizer(br.readLine());
            int a,b;
            long t;
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            t = Long.parseLong(st.nextToken());

            tip[a].add(new Node(b,t));
            difficult[b] += t;

        }



        PriorityQueue<Node> pq = new PriorityQueue<>();


        int cnt = 0;

        for(int i=0;i<N;i++){
            pq.add(new Node(i,difficult[i]));
        }

        boolean[] isClear = new boolean[N];

        while(!pq.isEmpty() && cnt < M){
            Node now = pq.poll();
            if(isClear[now.idx]){
               continue;
            }

            isClear[now.idx] = true;
            answer = Math.max(answer,now.weight);
            cnt++;

            for(Node next : tip[now.idx]){
                difficult[next.idx] -= next.weight;
                pq.add(new Node(next.idx, difficult[next.idx]));
            }



        }

        System.out.println(answer);




    }
}
