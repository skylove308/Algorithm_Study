package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502 {
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, M, count, max;
	static int[][] map, temp_map;
	static Point[] result;
	static ArrayList<Point> empty, virus;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		temp_map = new int[N][M];
		empty = new ArrayList<>();
		virus = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0)
					empty.add(new Point(i, j));
				else if (map[i][j] == 2)
					virus.add(new Point(i, j));
			}
		}

		result = new Point[3];
		comb(0, 0);

		System.out.println(max);
	}

	static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		visited[x][y] = true;
		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;

				if (visited[nx][ny])
					continue;

				if (temp_map[nx][ny] == 1)
					continue;

				if (temp_map[nx][ny] != 2)
					count++;
				
				q.add(new Point(nx, ny));
				temp_map[nx][ny] = 2;
				visited[nx][ny] = true;

			}
		}
	}

	static void comb(int idx, int cnt) {
		if (cnt == 3) {
			deepCopy();
			for (int i = 0; i < 3; i++) {
				temp_map[result[i].x][result[i].y] = 1;
			}
			visited = new boolean[N][M];
			count = 0;
			for (int i = 0; i < virus.size(); i++) {
				bfs(virus.get(i).x, virus.get(i).y);
			}
			//print();
			max = Math.max(max, empty.size() - 3 - count);

			return;
		}

		for (int i = idx; i < empty.size(); i++) {
			result[cnt] = empty.get(i);
			comb(i + 1, cnt + 1);
		}
	}

	static void deepCopy() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp_map[i][j] = map[i][j];
			}
		}
	}
	
	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(temp_map[i][j]);			
			}
			System.out.println();
		}
		System.out.println();
	}
}
