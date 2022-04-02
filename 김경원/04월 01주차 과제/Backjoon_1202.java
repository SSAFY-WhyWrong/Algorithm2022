import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Backjoon_1202 {
    static class Node implements Comparable<Node>{
        int weight, cost;

        Node(int weight, int cost){
            this.weight = weight;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return o.cost - this.cost;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        int N,K;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        List<Node> list = new ArrayList<>();

        int weight, cost;
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            weight = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());

            list.add(new Node(weight,cost));

        }

        Collections.sort(list, (o1,o2)->{
            if(o1.weight == o2.weight){
                return o2.cost - o1.cost;
            }
            return o1.weight - o2.weight;
        });

        int C;
        long answer = 0;
        List<Integer> bags = new ArrayList<>();
        for(int i=0;i<K;i++){
            C = Integer.parseInt(br.readLine());
            bags.add(C);


        }

        Collections.sort(bags);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        int idx=0;
        for(int num : bags){

            while(idx< N && list.get(idx).weight <= num){
                pq.add(list.get(idx++));
            }

            if(!pq.isEmpty()){
                answer += pq.poll().cost;
            }

        }

        System.out.println(answer);

    }
}
