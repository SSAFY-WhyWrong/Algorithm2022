import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

//14370과 사실상 같은 문제
public class Backjoon_14369 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T;

        T = Integer.parseInt(br.readLine());

        String[] number = {"ZERO","TWO","FOUR","SIX","EIGHT","ONE" ,"THREE","FIVE","SEVEN","NINE" };
        int[] arab_num = {0,2,4,6,8,1,3,5,7,9};
        char[] key = {'Z','W','U','X','G','O','T','F','V','I'};

        ArrayList<Integer> list;

        StringBuilder sb = new StringBuilder();
        int[] cnt;
        for(int t=1;t<=T;t++){
            list = new ArrayList<>();
            sb.append("Case #").append(t).append(": ");

            String str = br.readLine();
            cnt = new int[26];
            for(int i=0;i<str.length();i++){
                cnt[str.charAt(i) -'A']++;
            }

            for(int i=0;i<10;i++){
                int temp = cnt[key[i]-'A'];
                for(int j=0;j<number[i].length();j++){
                    cnt[number[i].charAt(j) - 'A'] -= temp;
                }
                for(int j=0;j<temp;j++){
                    list.add(arab_num[i]);
                }
            }
            Collections.sort(list);
            for(int n : list){
                sb.append(n);
            }


            sb.append('\n');

        }

        System.out.println(sb);

    }
}
