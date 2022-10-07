package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17471_2 {
	static int N, min = Integer.MAX_VALUE;
	static int[] popularity;
	static ArrayList<Integer>[] adjList;
	static boolean[] b;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		popularity = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			popularity[i] = Integer.parseInt(st.nextToken());
		}
		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < n; j++) {
				adjList[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		b = new boolean[N];

		subset(0);
		if (min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);
	}

	static void subset(int idx) {
		if (idx == N) {
			ArrayList<Integer> arr1 = new ArrayList<>();
			ArrayList<Integer> arr2 = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				if (b[i])
					arr1.add(i + 1);
				else
					arr2.add(i + 1);
			}

			// 비어있는 선거구는 없게 함
			if (arr1.isEmpty() || arr2.isEmpty())
				return;

			
			// arr2를 모두 방문 처리해서 arr1이 방문하지 못하도록 만듬
			boolean[] visited = new boolean[N + 1];
			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < arr2.size(); j++) {
					if (i == arr2.get(j))
						visited[i] = true;
				}
			}
			
			// bfs로 연결되어있는 것 모두 방문체크
			Queue<Integer> q = new LinkedList<>();
			q.add(arr1.get(0));
			visited[arr1.get(0)] = true;
			while (!q.isEmpty()) {
				int v = q.poll();
				for (int i : adjList[v]) {
					if (visited[i])
						continue;
					q.add(i);
					visited[i] = true;
				}
			}

			// 방문 체크 되지 않은 곳이 있으면 return
			for (int i = 1; i <= N; i++) {
				if (!visited[i])
					return;
			}

			// arr2를 체크해주기 위해 arr2 선거구만 visited체크  false로 돌림
			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < arr2.size(); j++) {
					if (i == arr2.get(j))
						visited[i] = false;
				}
			}

			q.add(arr2.get(0));
			visited[arr2.get(0)] = true;
			while (!q.isEmpty()) {
				int v = q.poll();
				for (int i : adjList[v]) {
					if (visited[i])
						continue;
					q.add(i);
					visited[i] = true;
				}
			}
			
			// 방문 체크 되지 않은 곳이 있으면 return
			for (int i = 1; i <= N; i++) {
				if (!visited[i])
					return;
			}
			
			
			// 두 구역으로 분리가 가능하다면 인구수 차와 최솟값 비교
			int p1 = 0;
			int p2 = 0;

			for (int i = 0; i < arr1.size(); i++) {
				p1 += popularity[arr1.get(i)];
			}
			for (int i = 0; i < arr2.size(); i++) {
				p2 += popularity[arr2.get(i)];
			}

			min = Math.min(min, Math.abs(p1 - p2));

			return;
		}

		b[idx] = true;
		subset(idx + 1);
		b[idx] = false;
		subset(idx + 1);

	}

}
