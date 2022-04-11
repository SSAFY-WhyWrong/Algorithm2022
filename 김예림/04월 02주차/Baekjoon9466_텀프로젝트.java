package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon9466_텀프로젝트 {

	static int N, tot, cnt;
	static int[] hope;
	static boolean[] selected, team;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(br.readLine());
			hope = new int[N+1];
			selected = new boolean[N+1];
			team = new boolean[N+1];
			tot = N;

			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int i=1; i<=N; i++) {
				hope[i] = Integer.parseInt(st.nextToken());
			}
			for(int i=1; i<=N; i++) {
				if(!team[i]) {
					dfs(i);
				}
			}
			sb.append(tot+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	private static void dfs(int member) {
		
		//이미 방문한 애면 싸이클이 돈 거니까 팀이 만들어졌다는 의미
		if(selected[member]) {
			team[member] = true; //팀이 만들어졌네유
			tot--; //팀이 만들어진 애가 추가됨
		}
		//아직 싸이클이 안 돌았으면 일단 방문 처리
		selected[member] = true;
		//다음 학생의 팀 결성 여부 확인
		if(!team[hope[member]]) dfs(hope[member]); //다음 연결된 애가 팀을 안 이뤘으면 또 팀 이뤄보자
		//다음 애가 팀을 이뤘으면 지금 친구는 팀을 결성 할 수 없음 그러므로 싸이클도 안 생김ㅔ
		selected[member] = false;
		//다음 애가 팀을 이미 이뤘으면 얘는 이제 어느 누가 방문해도 팀을 이룰 수 없는 애임
		team[member] = true;

	}
}
/*
1
5
3 3 1 2 1

1
6
2 3 4 5 6 2

1

7
6
2 3 4 5 6 2
5
2 5 4 5 2
6
1 3 4 3 2 6
13
2 4 5 2 4 1 8 9 10 11 9 10 10
10
2 5 7 1 6 8 8 3 5 10
10
2 7 10 5 3 3 9 10 6 3
6
2 1 1 2 6 3

1
3
2
8
6
8
4
 */
