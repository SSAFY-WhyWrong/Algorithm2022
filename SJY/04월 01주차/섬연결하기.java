import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        int answer = 0;
        ArrayList<Info>[] info = new ArrayList[n];
        
        for(int i=0;i<n;i++) {
            info[i] = new ArrayList<>();
        }
        
        for(int i=0;i<costs.length;i++) {
            int a = costs[i][0];
            int b = costs[i][1];
            int c = costs[i][2];
            
            info[a].add(new Info(b, c));
            info[b].add(new Info(a, c));
        }
        
        PriorityQueue<Info> pq = new PriorityQueue<>();
        boolean[] visit = new boolean[n];
        pq.add(new Info(0, 0));
        
        while(!pq.isEmpty()) {
            Info now = pq.poll();
            
            if(visit[now.node]) continue;
            answer += now.cost;
            visit[now.node] = true;
            
            for(int i=0;i<info[now.node].size();i++) {
                Info next = info[now.node].get(i);
                
                if(visit[next.node]) continue;
                pq.add(next);
            }
        }
        return answer;
    }
}

class Info implements Comparable<Info> {
    int node, cost;
    
    public Info(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }
    
    @Override
    public int compareTo(Info o) {
        return this.cost - o.cost;
    }
}