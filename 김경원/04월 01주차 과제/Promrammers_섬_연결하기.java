₩import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Programmers_섬_연결하기 {
    public static void main(String[] args) {
        int n = 4;
        int[][] costs = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};

        int answer = solution(n,costs);
        System.out.println(answer);


    }

    static class Node implements Comparable<Node>{
        int from, to, w;

        Node(int from, int to, int w){
            this.from = from;
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }


    static public int solution(int n, int[][] costs){
        int answer = 0;


        List<Node> edge = new ArrayList<>();
        for(int i=0;i<costs.length;i++){
            edge.add(new Node(costs[i][0],costs[i][1],costs[i][2]));
        }

        answer = getKruskal(n,edge);

        return answer;
    }


    static int getKruskal(int n, List<Node> edge){
        int answer = 0;

        Collections.sort(edge);
        int[] parent = new int[n];

        for(int i=0;i<n;i++){
            parent[i] = i;
        }

        int cnt = 0;

        for(int i=0;i<edge.size();i++){

            Node now = edge.get(i);

            if(!isUnion(now.from,now.to,parent)){
                answer += now.w;
                cnt++;
                if(cnt == n){
                    break;
                }
            }

        }



        return answer;
    }

    static boolean isUnion(int a, int b, int[] parent){
        int aParent = getParent(a, parent);
        int bParent = getParent(b, parent);

        if(aParent == bParent){
            return true;
        }

        parent[aParent] = Math.min(aParent,bParent);
        parent[bParent] = Math.min(aParent,bParent);

        return false;

    }

    static int getParent(int num, int[] parent){
        if(parent[num] == num){
            return num;
        }

        return parent[num] = getParent(parent[num],parent);

    }
}
