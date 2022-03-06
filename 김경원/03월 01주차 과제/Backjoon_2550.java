import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Backjoon_2550 {

    static class Node implements Comparable<Node> {
        int num, idx;
        public Node(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }

        @Override
        public int compareTo(Node o) {
            return this.num - o.num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr1 = new int[N];
        int[] arr2 = new int[N];
        int[] idx = new int[N];
        Map<Integer, Integer> map = new HashMap<>();
        List<Node> list = new ArrayList<>();

        st = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            arr1[i] = Integer.parseInt(st.nextToken());
            map.put(arr1[i],i);
        }

        st = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            arr2[i] = Integer.parseInt(st.nextToken());
            idx[i] = map.get(arr2[i]);
        }

        int answer = 0;
        Node lis[] = new Node[N];
        for(int i=0;i<N;i++){
            if(answer == 0 || list.get(answer-1).idx < idx[i]){
                list.add(new Node(arr2[i],idx[i]));
                lis[i] = new Node(arr2[i],answer);
                answer++;
            }else{
                int lisIdx =getIdx(idx[i],list);
                list.set(lisIdx, new Node(arr2[i],idx[i]));
                lis[i] = new Node(arr2[i],lisIdx);
            }
        }

        int now = answer-1;
        for(int i = N-1; i>=0; i--){
            if(lis[i].idx == now){
                list.set(now,lis[i]);
                now--;
            }
        }
        Collections.sort(list);
        System.out.println(answer);
        for(Node node : list){
            System.out.print(node.num+" ");
        }

    }
    static int getIdx(int num, List<Node> list){
        int low = 0;
        int high = list.size();

        while(low <= high){
            int mid = (low+high)/2;
            if(list.get(mid).idx < num){
                low = mid+1;
            }else{
                high = mid-1;
            }


        }
        return low;
    }

}
