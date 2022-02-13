import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Backjoon_2668 {
	static int answer;
	static boolean[] select;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N + 1];
		select = new boolean[N+1];
		
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		
		for(int i=1;i<=N;i++) {
			DFS(i,N, arr);
		}
		
		List<Integer> list = new ArrayList<>();
		
		for(int i=1; i<=N; i++) {
			if(select[i]) {
				list.add(i);
			}
		}

		System.out.println(list.size());
		for(int num : list) {
			System.out.println(num);
		}



	}

	static void DFS(int start, int N, int[] arr) {
		
		if(select[start]) {
			return;
		}
		Set<Integer> set = new HashSet<>();
		
		int now = start;
		
		while(true) {
			
			now = arr[now];
			
			if(set.contains(now)) {
				return;
			}
			
			set.add(now);
				
			if(start == now) {
				
				for(int num : set) {
					select[num] = true;					
				}
				
				break;
			}
			
			
			
		}
		
		


	}

}
