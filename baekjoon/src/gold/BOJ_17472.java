package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_17472 {
	static class Point {
		int x, y, num;

		public Point(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}

	static class Edge {
		int v, weight;

		public Edge(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}
	}

	static int N, M; // 맵 세로, 가로 크기
	static ArrayList<Point> land; // 섬 리스트
	static ArrayList<Edge>[] adjList; // 섬과 섬의 연결된 간선을 담은 인접리스트
	static int[][] map; // 전체 섬을 표시한 배열
	static boolean[][] visited; // visit체크 배열
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M]; // 맵
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		land = new ArrayList<>();
		int num = 1; // 섬의 번호
		visited = new boolean[N][M]; // visit체크 배열 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					dfs(i, j, num++); // 각 섬이 번호로 넘버링 된다.
				}
			}
		}

//		print();

		adjList = new ArrayList[num]; // 섬의 갯수는 num-1
		for (int i = 1; i <= num - 1; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < land.size(); i++) { // 섬을 모두 탐색하면서 섬과 섬을 다리로 연결한다.
			Point p = land.get(i);
			int curLandNum = p.num; // 현재 섬의 넘버
			for (int j = 0; j < 4; j++) {
				int dist = 0; // 다리의 길이
				int nx = p.x;
				int ny = p.y;
				while (true) {
					nx += dx[j];
					ny += dy[j];

					if (nx < 0 || ny < 0 || nx >= N || ny >= M)
						break;

					if (map[nx][ny] == curLandNum) // 도착한 곳이 섬인데 기존의 섬과 같다면
						break;

					if (map[nx][ny] == 0) { // 도착한 곳이 바다라면
						dist++;
						continue;
					}

					if (map[nx][ny] != curLandNum && dist <= 1) // 도착한 곳이 기존과 다른 섬이지만 거리가 1이하이면 break
						break;

					if (map[nx][ny] != curLandNum && dist >= 2) { // 도착한 곳이 기존과 다른 섬이고 거리가 2이상이면서 기존의 다리보다 짧다면 다리 연결하고
																	// break

						boolean check = false;
						for (int k = 0; k < adjList[curLandNum].size(); k++) {
							if (adjList[curLandNum].get(k).v == map[nx][ny]) {
								check = true;
								if (adjList[curLandNum].get(k).weight > dist)
									adjList[curLandNum].set(k, new Edge(map[nx][ny], dist));
							}
						}
						if (!check)
							adjList[curLandNum].add(new Edge(map[nx][ny], dist));
						break;
					}
				}
			}
		}

		// 모든 섬을 연결하는 다리 길이의 최솟갑을 구하기 위해 prim알고리즘으로 구현
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> {
			return o1.weight - o2.weight;
		});

		pq.add(new Edge(1, 0)); // 섬의 개수가 2개 이상이므로 number가 1인 섬은 무조건 존재

		boolean[] visitedLand = new boolean[num];
		int landCnt = 0;
		int ans = 0;
		while (!pq.isEmpty()) {
			Edge minE = pq.poll();

			if (visitedLand[minE.v]) // 이미 방문한 섬이면 continue
				continue;

			ans += minE.weight;
			visitedLand[minE.v] = true;
			landCnt++;
			
			if (landCnt == num - 1) // 전체 섬을 다 탐색했으면 break
				break;

			for (Edge e : adjList[minE.v]) {
				if (!visitedLand[e.v])
					pq.add(e);
			}
		}

		boolean allVisited = true;
		for (int i = 1; i <= num - 1; i++) {
			if (!visitedLand[i]) {
				allVisited = false;
				break;
			}
		}
		
		if (allVisited)
			System.out.println(ans);
		else
			System.out.println(-1);
	}

	// 맵을 탐색하여 섬을 구하기 위한 dfs 알고리즘
	static void dfs(int x, int y, int num) {
		land.add(new Point(x, y, num));
		map[x][y] = num;
		visited[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= N || ny >= M)
				continue;

			if (visited[nx][ny])
				continue;

			if (map[nx][ny] == 0)
				continue;

			dfs(nx, ny, num);
		}
	}

	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
