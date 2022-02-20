import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon_2650 {

    static class Node{
        int p1, p2;

        Node(int n1, int n2, int n3, int n4){
            this.p1 = Math.min(getPos(n1,n2), getPos(n3,n4));
            this.p2 = Math.max(getPos(n1,n2), getPos(n3,n4));
        }

        int getPos(int type, int pos){
            switch(type){
                case 1:
                    return pos;

                case 2:
                    return 50 * 3 - pos;

                case 3:
                    return 50 * 4 - pos;

                case 4:
                    return 50 + pos;

            }
            return -1;
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N;
        int crossMax = 0;

        N = Integer.parseInt(br.readLine()) / 2;

        Node[] lines = new Node[N];
        int[] cross = new int[N];


        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int n1,n2,n3,n4;
            n1 = Integer.parseInt(st.nextToken(" "));
            n2 = Integer.parseInt(st.nextToken(" "));
            n3 = Integer.parseInt(st.nextToken(" "));
            n4 = Integer.parseInt(st.nextToken(" "));

            lines[i] = new Node(n1,n2,n3,n4);

        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(lines[i].p1 < lines[j].p1 && lines[i].p2 < lines[j].p2 && lines[i].p2 > lines[j].p1){
                    cross[i]++;
                    cross[j]++;
                }
            }
        }
        int sum = 0;
        for(int i=0;i<N;i++){
            sum += cross[i];
            crossMax = Math.max(crossMax, cross[i]);
        }
        System.out.println(sum/2);
        System.out.println(crossMax);


    }
}

