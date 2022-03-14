import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Backjoon_1826 {

    static class Node implements Comparable<Node>{
        int pos, num;

        public Node(int pos, int num){
            this.pos = pos;
            this.num = num;
        }

        @Override
        public int compareTo(Node o) {
            return o.num - this.num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N,L,P;

        N = Integer.parseInt(br.readLine());

        Node[] gasStation = new Node[N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int pos,num;
            pos = Integer.parseInt(st.nextToken());
            num = Integer.parseInt(st.nextToken());
            gasStation[i] = new Node(pos,num);


        }

        Arrays.sort(gasStation,(o1,o2)->{
            return o1.pos - o2.pos;
        });

        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        int now = 0;
        int idx = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();

        now = P;
        P = 0;
        int answer = 0;


        while(now < L && idx < N){
            while(now < gasStation[idx].pos && !pq.isEmpty()){
                Node node = pq.poll();
                now += node.num;
                answer++;
            }

            if(now < gasStation[idx].pos){
                answer = -1;
                break;
            }

            pq.add(gasStation[idx]);
            idx++;

        }

        while(now < L && !pq.isEmpty()){
            Node node = pq.poll();
            now += node.num;
            answer++;
        }


        if(now < L){
            answer = -1;
        }

        System.out.println(answer);


    }

}
