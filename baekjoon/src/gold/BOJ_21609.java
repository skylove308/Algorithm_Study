package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_21609 {
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, M, max, max_rainbow_cnt, ans;
	static Point max_standard_block;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<Point> max_group;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			// 초기화
			max = 0;
			max_rainbow_cnt = 0;
			max_standard_block = new Point(0, 0);
			max_group = new ArrayList<>();
			// 1. 크기가 가장 큰 블록 그룹을 찾는다. 그러한 블록 그룹이 여러개라면 포함된 무지개 블록의 수가 가장 많은 블록 그룹,
			// 그러한 블록도 여러개라면 기준 블록의 행이 가장 큰 것을, 그것도 여러개 이면 열이 가장 큰 것을 찾는다.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					visited = new boolean[N][N];
					if (!visited[i][j] && map[i][j] > 0)
						bfs(i, j);
				}
			}

			// 종료 조건
			if (max <= 1)
				break;

			// 2. 1에서 찾은 블록 그룹의 모든 블록을 제거한다.
			for (int i = 0; i < max_group.size(); i++) {
				Point p = max_group.get(i);
//				System.out.println(p.x+" "+p.y);
				map[p.x][p.y] = -2;
			}

			// 2. 블록 그룹에 포함된 블록의 수를 B라고 했을 때 B^2점을 획득한다.
			ans += max * max;
//			print();
			// 3. 격자에 중력이 작용한다.
			gravity();
//			print();
			// 4. 격자가 90도 반시계 방향으로 회전한다.
			rotate();
//			print();
			// 5. 다시 격자에 중력이 작용한다.
			gravity();
//			print();

		}
		
		System.out.println(ans);

	}

	static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		Queue<Point> group = new LinkedList<>();
		group.add(new Point(x, y));
		visited[x][y] = true;
		int color = map[x][y];
		int total_cnt = 1;
		int rainbow_cnt = 0;
		Point standard_block = new Point(x, y);
		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny])
					continue;
				if (map[nx][ny] == color || map[nx][ny] == 0) {
					q.add(new Point(nx, ny));
					group.add(new Point(nx, ny));
					visited[nx][ny] = true;
					total_cnt++;
					if (map[nx][ny] == 0)
						rainbow_cnt++;
					else {
						if (nx < standard_block.x)
							standard_block = new Point(nx, ny);
						else if (nx == standard_block.x) {
							if (ny < standard_block.y)
								standard_block = new Point(nx, ny);
						}
					}
				}
			}
		}
		if (total_cnt > 1) {
			if (max < total_cnt || (max == total_cnt && max_rainbow_cnt < rainbow_cnt)
					|| (max == total_cnt && max_rainbow_cnt == rainbow_cnt && max_standard_block.x < standard_block.x)
					|| (max == total_cnt && max_rainbow_cnt == rainbow_cnt && max_standard_block.x == standard_block.x
							&& max_standard_block.y < standard_block.y)) {

				max = total_cnt;
				max_rainbow_cnt = rainbow_cnt;
				max_standard_block = new Point(standard_block.x, standard_block.y);
				max_group.clear();
				while (!group.isEmpty()) {
					max_group.add(group.poll());
				}
			}
		}
	}

	static void gravity() {
		for (int i = N - 1; i >= 0; i--) {
			for (int j = N - 1; j >= 0; j--) {
				if (map[i][j] < 0)
					continue;
				int ni = i + 1;
				while (true) {
					if (ni >= N || map[ni][j] != -2)
						break;

					map[ni][j] = map[ni - 1][j];
					map[ni - 1][j] = -2;
					ni++;

//					System.out.println(i + " " + j);
				}

			}
		}
	}

	static void rotate() {
		int[][] temp = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				temp[N-1-j][i] = map[i][j]; 
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = temp[i][j]; 
			}
		}
	}

	static void print() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
