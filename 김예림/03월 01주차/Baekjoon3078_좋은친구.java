package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon3078_좋은친구 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Friend[] friends = new Friend[N];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			
			friends[i] = new Friend(i, str.length());
		}
		Arrays.sort(friends);
		
		long cnt = 0;
		Queue<Friend> q = new LinkedList<>();
		for(int i=0; i<N; i++) {
			Friend cur = q.peek();
			
			if(q.isEmpty()) { //친구를 추가
				q.offer(friends[i]);
			}else if(cur.name==friends[i].name && friends[i].rank-cur.rank<=K){//큐에 있는 친구랑 다음 친구가 좋은 친구면 추가
				cnt += q.size();
				q.offer(friends[i]); 
			}else {
				q.poll(); //좋은 친구사이가 아닐 경우
				i--;
			}
		}
		System.out.println(cnt);
	}
	static class Friend implements Comparable<Friend>{
		int rank;
		int name;
		public Friend(int rank, int name) {
			this.rank = rank;
			this.name = name;
		}
		@Override
		public int compareTo(Friend o) {
			if(this.name == o.name) return this.rank-o.rank; //그 다음 등수순
			else return this.name-o.name; //이름 길이순으로 정렬
		}
	}
}
/*
4 2
IVA
IVO
ANA
TOM
5

6 3
CYNTHIA
LLOYD
STEVIE
KEVIN
MALCOLM
DABNEY
2


*/
