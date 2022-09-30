package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_17471 {
	static boolean[] b;
	static boolean[] visited;
	static int N, min = Integer.MAX_VALUE;
	static int[] popularity;
	static int[] area;
	static ArrayList<Integer>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 구역의 갯수
		b = new boolean[N + 1]; // 부분집합을 구하기 위한 boolean배열 초기화
		StringTokenizer st = new StringTokenizer(br.readLine());
		popularity = new int[N + 1]; // 각 구역의 인구수 배열 초기화
		for (int i = 1; i <= N; i++) {
			popularity[i] = Integer.parseInt(st.nextToken());
		}

		adjList = new ArrayList[N + 1]; // 각 도시별로 연결되어 있는 정보를 담은 인접리스트
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < n; j++) {
				adjList[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		powerSet(1);

		if (min == Integer.MAX_VALUE) // 두 선거구로 나눌 수 없는 경우
			System.out.println(-1);
		else
			System.out.println(min);
	}

	// area의 첫번째 원소로부터 dfs를 시작해서 연결되어 있는 모든 정점을 구하고 그 정점이 area에 존재하면 0으로 마킹해준다.
	static void dfs(int x, int areaNum, int[] area) {
		area[x] = 0;
		visited[x] = true;
		for (int i = 0; i < adjList[x].size(); i++) {
			if (!visited[adjList[x].get(i)] && area[adjList[x].get(i)] == areaNum)
				dfs(adjList[x].get(i), areaNum, area);
		}
	}

	static void powerSet(int idx) {
		if (idx == N + 1) {
			area = new int[N + 1]; // 새로운 선거구 조합이 나올 때마다 area초기화

			int area1 = 0; // 선거구1의 마지막 지역번호
			int area2 = 0; // 선거구2의 마지막 지역번호
			for (int i = 1; i <= N; i++) {
				if (b[i]) {
					area[i] = 1;
					area1 = i;
				}

				else {
					area[i] = 2;
					area2 = i;
				}

			}
//			System.out.println("area1: "+area1+" "+"area2: "+area2);

			if (area1 == 0 || area2 == 0) // 두 선거구 중 하나라도 비어있으면 안된다.
				return;

//			System.out.println("dfs전 area");
//			for(int i = 1; i <= N; i++) {
//				System.out.print(area[i] + " ");
//			}
//			System.out.println();

			int popularity1 = 0; // area1의 인구수
			int popularity2 = 0; // area2의 인구수

			for (int i = 1; i <= N; i++) {
				if (area[i] == 1)
					popularity1 += popularity[i]; // 인구수 더하기
				else
					popularity2 += popularity[i];
			}

			visited = new boolean[N + 1]; // dfs를 위한 visited배열 초기화
			dfs(area1, 1, area);
			visited = new boolean[N + 1]; // dfs를 위한 visited배열 초기화
			dfs(area2, 2, area);

//			System.out.println("dfs후 area");
//			for(int i = 1; i <= N; i++) {
//				System.out.print(area[i] + " ");
//			}
//			System.out.println();

			// area1과 area2가 각자 서로 모두 연결되어 있다면 올바른 선거구 조합이므로 인구 차이를 구한다.
			boolean check = true;
			for (int i = 1; i <= N; i++) {
				if (area[i] != 0) { // 0이 아니라는 것은 연결되지 못한 지역구가 있다는 의미
					check = false;
					break;
				}
			}
			if (check)
				min = Math.min(min, Math.abs(popularity1 - popularity2)); // 전체 선거구 조합에서 인구 차이의 최솟값 구하기

			return;
		}

		// 부분집합 구하기
		b[idx] = true;
		powerSet(idx + 1);
		b[idx] = false;
		powerSet(idx + 1);
	}
}
