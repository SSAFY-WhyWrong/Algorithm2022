package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon2550_전구 {
    static Find[] pairs;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int[] switches = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i=0; i<N; i++) {
        	switches[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine()," ");
        int[] bulb = new int[N];
        for(int i=0; i<N; i++) {
        	switches[i] = Integer.parseInt(st.nextToken());
        }
        
        pairs = new Find[N];
        dp = new int[N];
        Find[] tracking = new Find[N];
        int idx = 0;
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int target = switches[i];
            for (int j = 0; j < N; j++) {
                if (bulb[j] == target) {
                    pairs[i] = new Find(j, target);
                    break;
                }
            }
        }

        dp[0] = pairs[0].first;
        tracking[0] = new Find(idx, pairs[0].second);

        for (int i = 1; i < N; i++) {
            if (dp[idx] < pairs[i].first) {
                dp[++idx] = pairs[i].first;
                tracking[i] = new Find(idx, pairs[i].second);
            } else {
                int target = binarySearch(idx, pairs[i].first);
                dp[target] = pairs[i].first;
                tracking[i] = new Find(target, pairs[i].second);
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o1.compareTo(o2));

        for (int i = N-1; i>=0; i--) {
            if (tracking[i].first == idx) {
                pq.add(tracking[i].second);
                idx--;
            }
        }
        answer.append(pq.size());
        answer.append("\n");

        while (!pq.isEmpty()) {
            answer.append(pq.poll());
            answer.append(" ");
        }
        System.out.println(answer);
    }

    static int binarySearch(int high, int target) {
        int low = 0;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (dp[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return low;
    }

    static class Find {
        int first;
        int second;
        public Find(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}