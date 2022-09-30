package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1260 {
	static int n, m, start;
	static boolean[] visited;
	static ArrayList<Integer>[] arr;
	static StringBuilder answer_dfs, answer_bfs;
	public static void main(String[] args) throws IOException {
		// 문제 입력 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		arr = new ArrayList[n+1];

		for(int i=1; i<=n; i++) {
			arr[i] = new ArrayList<>();
		}
		
		for(int i=0; i<m; i++) { // arr 배열에 좌표에 해당하는 정점끼리 연결되어있으면 1 삽입
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st2.nextToken());
			int y = Integer.parseInt(st2.nextToken());
			arr[x].add(y);
			arr[y].add(x);
		}
		
		for(int i=1; i<=n; i++) {
			arr[i].sort(Comparator.naturalOrder());
		}
		
		answer_dfs = new StringBuilder();
		answer_bfs = new StringBuilder();
		// 문제 입력 끝
		visited = new boolean[n+1]; // visited 초기화
		dfs(start);
		visited = new boolean[n+1]; // visited 초기화
		bfs(start);
		System.out.println(answer_dfs);
		System.out.println(answer_bfs);
		
	}
	
	static void dfs(int x) {
		answer_dfs.append(x+" ");
		visited[x] = true;
		for(int i=0; i<arr[x].size(); i++) {
			if(visited[arr[x].get(i)]) continue;
			dfs(arr[x].get(i));
		}
	}
	
	static void bfs(int x) {
		Queue<Integer> q = new LinkedList<>();
		q.add(x);
		visited[x] = true;
		answer_bfs.append(x+" ");
		while(!q.isEmpty()) {
			int t = q.poll();
			for(int i=0; i<arr[t].size(); i++) {
				if(visited[arr[t].get(i)]) continue;
				q.add(arr[t].get(i));
				visited[arr[t].get(i)] = true;
				answer_bfs.append(arr[t].get(i)+" ");
			}
		}
		
	}
}
