package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baekjoon1644_소수의연속합 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		if(N==1) System.out.println(0);
		else {
			//에라토스테네스의 체 -> 소수 구하기
			boolean[] prime = new boolean[N+1];
			
			prime[0] = prime[1] = true;
			
			for(int i=2; i*i<=N; i++) {
				if(!prime[i]) {
					for(int j=i*i; j<=N; j+=i) prime[j] = true;
				}
			}
			int tot = 0;
			int start = 0, end = 0, cnt = 0;
			ArrayList<Integer> list = new ArrayList<>();
			for(int i=0; i<=N; i++) {
				if(!prime[i]) list.add(i);
			}
			
			while(true) {
				if(cnt==N) tot++;
				if(end==list.size() && cnt<N) break;
				if(end==list.size() && start==list.size()) break;
				if(end==list.size()) {
					cnt -= list.get(start);
					start++;
				}else {
					if(cnt<=N) {
						cnt += list.get(end);
						end++;
					}else if(cnt>N) {
						cnt -= list.get(start);
						start++;
					}
				}
			}
			
			System.out.println(tot);
			
		}
	}
}
