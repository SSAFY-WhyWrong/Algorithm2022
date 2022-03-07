import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Backjoon_1477 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N,M,L;
        List<Integer> list = new ArrayList<>();


        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());


        st = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            list.add(Integer.parseInt(st.nextToken()));
        }
        list.add(0);
        list.add(L);
        Collections.sort(list);

        int low = 1;
        int high = L-1;
        int mid;
        while(low <= high){
            mid = (low + high) / 2;
            int count = 0;
            for(int i=1;i<list.size();i++){
                if(list.get(i) - list.get(i-1) > mid){
                    count += (list.get(i) - list.get(i-1))  / mid;

                    if((list.get(i) - list.get(i-1))%mid == 0) count--;

                }


            }

            if(count > M) low = mid+1;
            else high = mid-1;

        }

        System.out.println(low);


    }
}
