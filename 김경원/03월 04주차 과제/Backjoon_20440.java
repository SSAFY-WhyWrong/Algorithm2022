import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Backjoon_20440 {

    static class Node implements Comparable<Node>{

        int time;
        int value;

        public Node(int time, int value){
            this.time = time;
            this.value = value;
        }


        @Override
        public int compareTo(Node o) {

            if(this.time == o.time){
                return this.value;
            }

            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N;

        N = Integer.parseInt(br.readLine());

        PriorityQueue<Node> pq = new PriorityQueue<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int start,  end;

            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            pq.add(new Node(start,1));
            pq.add(new Node(end,-1));


        }

        int now = 0;
        int max = 0;
        int s=0,e=0;

        boolean isFirst = false;

        while(!pq.isEmpty()){
            Node node = pq.poll();
            now += node.value;
            while(!pq.isEmpty() && pq.peek().time == node.time){
                now += pq.poll().value;
            }


            if(now > max){
                max = now;
                s = node.time;
                isFirst = true;
            }

            if(now != max){
                if(isFirst){
                    e = node.time;
                }
                isFirst = false;
            }



        }

        System.out.println(max);
        System.out.println(s+" "+e);


    }

}
