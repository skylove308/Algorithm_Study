package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16234 {
	static class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int N, L, R, cnt, sum;
	static int[][] map;
	static boolean[][] visited;
	static Queue<Point> q;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int day = 0;
		while (true) {
			visited = new boolean[N][N];
			boolean check = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					cnt = 1;
					sum = map[i][j];

					if (visited[i][j])
						continue;

					visited[i][j] = true;
					q.add(new Point(i, j));

					dfs(i, j);

					if (cnt != 1) {
						check = true;
						while(!q.isEmpty()) {
							Point pos = q.poll();
							map[pos.x][pos.y] = sum/cnt;
						}
					}
					q.clear();
				}
			}
			
			if (!check)
				break;
			
			// print();
			
			day++;
		}
		System.out.println(day);

	}

	static void dfs(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= N || ny >= N)
				continue;

			if (visited[nx][ny])
				continue;

			int diff = Math.abs(map[x][y] - map[nx][ny]);

			if (diff >= L && diff <= R) {
				visited[nx][ny] = true;
				cnt++;
				sum += map[nx][ny];
				q.add(new Point(nx, ny));
				dfs(nx, ny);
			}
		}
	}

	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
