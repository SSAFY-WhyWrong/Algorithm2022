import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Backjoon_2026 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int K,N,F;

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());

        Set<Integer> set[] = new HashSet[N+1];

        for(int i=1;i<=N;i++){
            set[i] = new HashSet<>();
            set[i].add(i);
        }

        for(int i=0;i<F;i++){
            st = new StringTokenizer(br.readLine());
            int a,b;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            set[a].add(b);
            set[b].add(a);

        }

        StringBuilder sb = new StringBuilder();

        List<Integer> list = new ArrayList<>();
        DFS(K,N,1,new HashSet<Integer>(), set, list);
        if(list.size() == 0){
            list.add(-1);
        }
        Collections.sort(list);
        for(int num : list){
            System.out.println(num);
        }


    }

    static void DFS(int K,int N, int idx, Set<Integer> choice, Set<Integer>[] set, List<Integer> list){

        if(list.size() > 0){
            return;
        }

        if(choice.size() == K){
            for(int num : choice){
                for(int next : choice){
                    if(!set[num].contains(next)){
                        return;
                    }
                }
            }

            for(int num : choice){
                list.add(num);
            }


        }

        if(idx > N){
            return;
        }

        if(set[idx].size() >= K) {
            choice.add(idx);
            DFS(K, N, idx + 1, choice, set, list);
            choice.remove(idx);
        }
        DFS(K,N,idx+1,choice,set,list);





    }
}
